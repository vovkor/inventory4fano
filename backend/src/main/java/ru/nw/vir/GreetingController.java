package ru.nw.vir;

import ru.nw.vir.dao.CustomerRepository;
import ru.nw.vir.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import java.util.List;


import java.util.concurrent.atomic.AtomicLong;
import java.util.Collection;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

//https://spring.io/guides/gs/rest-service/
//https://spring.io/blog/2015/06/08/cors-support-in-spring-framework

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600) // иначе была ошибка: No 'Access-Control-Allow-Origin' header is present on the requested resource
@RestController

//@RequestMapping("/greeting") // можно вынести сюда
public class GreetingController {

    @Autowired
    DataSource dataSource;

    @Autowired
    private CustomerRepository customerRepository;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
	
    @RequestMapping("/greeting") //вынести перед классом?
    public Collection<Greeting> greetings(@RequestParam(value="name", defaultValue="World") String name) {
		ArrayList<Greeting> states = new ArrayList<Greeting>();
		states.add(new Greeting(counter.incrementAndGet(), "Кукуруза", String.format(template, name)));
		states.add(new Greeting(counter.incrementAndGet(), "Пшеница", String.format(template, name)));
		List<Customer> list = customerRepository.findAll();
        return states;
    }
	
	//@RequestMapping("/greeting")
    //public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
	//	return new Greeting(counter.incrementAndGet(), name, name);
    //}

	@RequestMapping("/greeting/{id}")
    public Greeting greetingId(@PathVariable("id") String id) {
		return new Greeting(Long.parseLong(id), "Огурец", "Новый сорт");
    }


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