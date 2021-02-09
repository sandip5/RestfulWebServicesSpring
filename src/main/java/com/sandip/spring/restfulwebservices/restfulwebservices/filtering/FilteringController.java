package com.sandip.spring.restfulwebservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue someBean() {
        SomeBean someBean = new SomeBean("UserName", "password");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("userName");
        FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filters);
        return mapping;
    }
}
