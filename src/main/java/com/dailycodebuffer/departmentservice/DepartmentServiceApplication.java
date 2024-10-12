package com.dailycodebuffer.departmentservice;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@ComponentScan(excludeFilters = {
		@ComponentScan.Filter(
				type = FilterType.ANNOTATION,
				value = Transactional.class
		)
})
public class DepartmentServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext obj = SpringApplication.run(DepartmentServiceApplication.class, args);
		ConfigurableListableBeanFactory val =  obj.getBeanFactory();
	}

}
