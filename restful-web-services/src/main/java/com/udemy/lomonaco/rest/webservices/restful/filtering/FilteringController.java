package com.udemy.lomonaco.rest.webservices.restful.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue someBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");

		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filter("field1", "field2"));

		return mapping;
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue someBeanList() {
		List<SomeBean> beans = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value11", "value22", "value32"));

		MappingJacksonValue mapping = new MappingJacksonValue(beans);
		mapping.setFilters(filter("field2", "field3"));

		return mapping;
	}

	private FilterProvider filter(String... fields) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		return filters;
	}
}
