package com.loonds.openAIService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
public class OpenAIChatGPTClient {
    @Value("${openai.api.key}")
    String key;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + key);
            return execution.execute(request, body);
        });

        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized");
                }
                super.handleError(response);
            }
        });

        return restTemplate;
    }
}

