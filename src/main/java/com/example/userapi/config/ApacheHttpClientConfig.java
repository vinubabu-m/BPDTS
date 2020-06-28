package com.example.userapi.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
public class ApacheHttpClientConfig {

  @Bean
  public CloseableHttpClient httpClient() {
    RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(HttpClientConfigConstants.CONNECTION_TIMEOUT)
            .setConnectionRequestTimeout(HttpClientConfigConstants.REQUEST_TIMEOUT)
            .setSocketTimeout(HttpClientConfigConstants.SOCKET_TIMEOUT)
            .build();

    return HttpClients.custom()
            .setDefaultRequestConfig(requestConfig)
            .build();
  }

}