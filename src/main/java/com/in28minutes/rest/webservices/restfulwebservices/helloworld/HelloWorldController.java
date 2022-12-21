package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	private MessageSource messageSource;
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource= messageSource;
	}
	@GetMapping(path= "/hello-world")
	public String helloWorld() {
		return "hello world";
	}
	
	@GetMapping(path="/hello-world-bean")
	public helloWorldBean helloWorldBean() {
		return new helloWorldBean("helloWorldBean"); 
	}
	
	@GetMapping(path="/hello-world/path-variable/{name}")
	public helloWorldBean helloWorldName(@PathVariable String name) {
		return new helloWorldBean(String.format("hello world, %s", name));
	}
	
	@GetMapping(path= "/hello-world-Internationalized")
	public String helloWorldInternationalized() {
		Locale locale =LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "default message", locale);
		
	}
	

}
