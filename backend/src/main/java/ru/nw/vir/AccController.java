package ru.nw.vir;

import ru.nw.vir.dao.AccRepository;
import ru.nw.vir.model.Acc;
import ru.nw.vir.model.Account;
import ru.nw.vir.model.Files;
import ru.nw.vir.excel.ExcelParser;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import java.util.List;


import java.util.concurrent.atomic.AtomicLong;
import java.util.Collection;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import org.springframework.dao.EmptyResultDataAccessException;
 

//https://spring.io/guides/gs/uploading-files/
import ru.nw.vir.storage.StorageFileNotFoundException;
import ru.nw.vir.storage.StorageService;
import ru.nw.vir.dao.AccountRepository; // в примере import не было
import ru.nw.vir.dao.FilesRepository; // в примере import не было
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

//https://spring.io/guides/gs/rest-service/
//https://spring.io/blog/2015/06/08/cors-support-in-spring-framework

//Класс помечен как @RestController, означая, что он готов к использованию Spring MVC для обработки запросов. 
//RequestMapping соответствует / метода index(). При вызове из браузера или использовании curl в командной строке, метод возвращает чистый текст.
// Т.к. @RestController сочетает в себе @Controller и @ResponseBody, две аннотации в результате web запросов возвращают данные, а не представление. 

//@CrossOrigin(origins = "http://91.151.189.38:8080", maxAge = 3600) // иначе была ошибка: No 'Access-Control-Allow-Origin' header is present on the requested resource
@CrossOrigin(origins = {"https://db.vir.nw.ru:443", "http://db.vir.nw.ru:443", "http://91.151.189.38:443", "http://91.151.189.38:8080", "http://localhost:8080"}, maxAge = 3600) 
// CrossOrigin не дает данные для браузера снаружи вирт. машины
//@CrossOrigin(origins = "*", maxAge = 3600) 
@RestController


//@RequestMapping("/acc") // можно вынести сюда
public class AccController {
	private final StorageService storageService;
	private final AccountRepository accountRepository;
	private final FilesRepository filesRepository;

	@Autowired
    DataSource dataSource;

    @Autowired
    private AccRepository accRepository;
	
    @Autowired
    public AccController(StorageService storageService, AccountRepository accountRepository, FilesRepository filesRepository) {
        this.storageService = storageService;
		this.accountRepository = accountRepository; // инитится в Application.java ???
		this.filesRepository = filesRepository;
    }


    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
	
    @RequestMapping("/acc") //вынести перед классом?
    public Collection<Acc> Accs(@RequestParam(value="name", defaultValue="World") String name) {
		//ArrayList<Greeting> states = new ArrayList<Greeting>();
		//states.add(new Greeting(counter.incrementAndGet(), "Кукуруза", String.format(template, name)));
		//states.add(new Greeting(counter.incrementAndGet(), "Пшеница", String.format(template, name)));
		//return states;
		
		List<Acc> list = accRepository.findAll();
	return list;
        
    }
	//@PathVariable - для строки
	//@RequestParam - для параметров из строки, идущих после символа ?
	// http://db.vir.nw.ru:8080/acc/226?userId=serhl
	@RequestMapping("/acc/{id}")
    public Acc accId(@RequestParam(value="userId") String userId, @PathVariable("id") String id) throws SQLException {
		this.validateUser(userId);
		Acc virAcc = accRepository.getAcc(Long.parseLong(id));	
	return virAcc;
	}
	
	// Первые n записей
	// http://db.vir.nw.ru:8080/acc/limit/200?userId=serhl
	@RequestMapping("/acc/limit/{n}")
    public Collection<Acc> accsLimit(@RequestParam(value="userId") String userId, @PathVariable("n") String n) throws SQLException {
		this.validateUser(userId);
		List<Acc> list = accRepository.findAllLimit(Long.parseLong(n));
	return list;
	}
	
	// Получить список загруженных файлов
	
	// http://db.vir.nw.ru:8080/acc/files/serhl
	@RequestMapping("/acc/files/{userId}")
    public Collection<Files> filesByOwner(@PathVariable("userId") String userId) throws SQLException {
		//this.validateUser(userId);
		List<Files> files = filesRepository.findByOwner(userId);	
	return files;
	}	
	
	// ---- Авторизация -----
	//@PathVariable - для строки
	// http://db.vir.nw.ru:8080/acc/auth/serhl
	@RequestMapping(value = "/acc/auth/{userId}", method = { RequestMethod.GET })
    public boolean authUser(@PathVariable("userId") String userId) throws SQLException {
		try {
			this.validateUser(userId);
			return true;
		}
		catch(UserNotFoundException e) { return false; }
		catch(EmptyResultDataAccessException e) { return false; }
		
	}	
	
	//@RequestMapping(value = "/upload",method = RequestMethod.POST, consumes = "multipart/form-data")
	//@ResponseBody
	//public ResponseEntity<String> addUser(@RequestPart("uploadFile") MultipartFile file){return ResponseEntity.ok(null);} //, @RequestPart("info") ExampleModel model
	
	
	// ---- Загрузка файла -----
	// параметры передаем через FormData
	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("uploadFile") MultipartFile file, @RequestParam("userId") String userId,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
		// добавим информацию о загрузке файла
		accRepository.addRecFile(file.getOriginalFilename(), userId);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }
	
	// ---- Парсинг файла -----
	@RequestMapping("/acc/parse/{ownerName}")
	public String parserFile(@PathVariable("ownerName") String ownerName) throws SQLException {
		String result;
        ExcelParser parser  = new ExcelParser(accRepository);
		//result = parser.parse("C:\\upload\\"+ownerName+".xls");
		result = parser.parse("C:\\upload\\"+filesRepository.getLastFile(ownerName));
		
        return result;
    }	

	// ---- Удалить записи -----
	//@RequestMapping(value = "/acc/del/{ownerName}", method = RequestMethod.DELETE)
	// не заработало. Angular не научил посылать DELETE
	@RequestMapping("/acc/del/{ownerName}")
	public String deleteRecords(@PathVariable("ownerName") String ownerName) throws SQLException {
		String result;
        result = accRepository.deleteAcc(ownerName);
		System.out.println("Deleting All Users");
        return "Удалено: "+result;
    }
	
	
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
	
	// ---- Проверка доступа -----
	private void validateUser(String userId) {
	// функция findByUsername возвращает тип Optional<Account>, удобный для работы с null
		this.accountRepository
		.findByUsername(userId)
		.orElseThrow(() -> new UserNotFoundException(userId));
	}	
	
	//@RequestMapping("/greeting")
    //public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
	//	return new Greeting(counter.incrementAndGet(), name, name);
    //}

	//@RequestMapping("/greeting/{id}")
    //public Greeting greetingId(@PathVariable("id") String id) {
	//	return new Greeting(Long.parseLong(id), "Огурец", "Новый сорт");
    //}


/*
@RequestMapping({"/abcd", "/employees/{value}/{id}"})
public String getEmployees(
    @PathVariable("value") Optional<String> val, 
    @PathVariable("id") Optional<String> id,
    @RequestParam("param") Optional<String> value
) {
    // ********
} */	
}