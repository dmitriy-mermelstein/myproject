package com.f5.exersice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dmitriy Mermelstein
 *
 */
//@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class WebServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WebServerApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebServerApplication.class);
    }

}
