package com.dailycodebuffer.departmentservice.config;

import com.dailycodebuffer.departmentservice.client.EmployeeClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    private final LoadBalancedExchangeFilterFunction filterFunction;

    public WebClientConfig(LoadBalancedExchangeFilterFunction filterFunction) {
        this.filterFunction = filterFunction;
    }

    @Bean
    public WebClient employeeWebClient() {
        return WebClient.builder()
                .baseUrl("http://employee-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public EmployeeClient employeeClient() {
        return HttpServiceProxyFactory
                .builder()
                .exchangeAdapter(WebClientAdapter.create(employeeWebClient()))
                .build()
                .createClient(EmployeeClient.class);
    }
}
