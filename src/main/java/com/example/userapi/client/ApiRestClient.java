package com.example.userapi.client;

import com.example.userapi.common.Constants;
import com.example.userapi.config.ApiConfig;
import com.example.userapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class ApiRestClient {

  private RestTemplate restTemplate;

  private ApiConfig apiConfig;

  @Autowired
  public ApiRestClient(RestTemplate restTemplate, ApiConfig apiConfig) {
    this.restTemplate = restTemplate;
    this.apiConfig = apiConfig;
  }

  public List<User> getAllUsers() {
    ResponseEntity<User[]> entity = restTemplate.getForEntity(apiConfig.getUrl() + apiConfig.getFetchAllUsersUri(),
                                                                User[].class);
    return entity.getBody() != null? Arrays.asList(entity.getBody()) : Collections.emptyList();
  }

  public List<User> getAllUsersBasedOnCity(String city) {
    String requestUri = apiConfig.getUrl() + apiConfig.getFetchCityUsersUri();

    Map<String, String> urlParameters = new HashMap<>();
    urlParameters.put(Constants.CITY, city);

    ResponseEntity<User[]> entity = restTemplate.getForEntity(requestUri,
                                                                  User[].class,
                                                                  urlParameters);

    return entity.getBody() != null? Arrays.asList(entity.getBody()) : Collections.emptyList();
  }

}
