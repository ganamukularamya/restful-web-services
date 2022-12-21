package com.in28minutes.rest.webservices.restfulwebservices.filteringcontroller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class Filtering {
	
//Static Filtering	
//	@GetMapping("/filtering")
//	public SomeBean filter() {
//		return new SomeBean("value1", "value 2", "value3");
//	}
	
	@GetMapping("/filtering")
	public MappingJacksonValue filter() {
		SomeBean someBean = new SomeBean("value1", "value 2", "value3");
		MappingJacksonValue mappingJacksonValue= new MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("filed1","filed3");
		FilterProvider filters= new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> filterList() {
		return Arrays.asList(new SomeBean("value1", "value 2", "value3"), new SomeBean("value4","value5","value6"));
	}

}
