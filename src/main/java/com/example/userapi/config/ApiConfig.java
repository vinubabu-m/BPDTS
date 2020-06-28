package com.example.userapi.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ApiConfig {

    @Value("${service.api.url}")
    private String url;

    @Value("${service.api.uri.fetchusers}")
    private String fetchAllUsersUri;

    @Value("${service.api.uri.fetchcityusers}")
    private String fetchCityUsersUri;

}
