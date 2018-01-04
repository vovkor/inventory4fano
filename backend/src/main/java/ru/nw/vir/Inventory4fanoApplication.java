package ru.nw.vir;

import java.util.HashMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Inventory4fanoApplication {
	
private int maxUploadSizeInMb = 10 * 1024 * 1024; // 10 MB
	
public static HashMap<Long, Student> hmStudent;
	public static void main(String[] args) {
		hmStudent = new HashMap<Long, Student>();
		Student one = new Student("ddd", "Рубли");
		hmStudent.put(new Long(one.getId()), one);
		
		// Запуск
		SpringApplication.run(Inventory4fanoApplication.class, args);
		
		Student two = new Student("Вова", "География");
		hmStudent.put(new Long(one.getId()), two);
		
		

	}
	
    //Tomcat large file upload connection reset
    //http://www.mkyong.com/spring/spring-file-upload-and-connection-reset-issue/
    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbedded() {

        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                //-1 means unlimited
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });

        return tomcat;

    }	
}
