package com.f5.exersice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.f5.exersice.kafka.consumer.Receiver;
import com.f5.exersice.kafka.consumer.ReceiverHandler;

/**
 * @author Dmitriy Mermelstein
 *
 */
@SpringBootApplication
//public class Application implements CommandLineRunner {

//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
	
//	@Override
//	public void run(String... args) throws Exception {
//		receiver.setHandler(publisherHandler);
//	}
	
//	@Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Application.class);
//    }

}
