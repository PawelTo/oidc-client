package app.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.web.reactive.function.client.ServletBearerExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
@Slf4j
public class WebClientConfig {

    @PostConstruct
    public void postConstruct(){
        log.info("Configuring web client");
    }

    @Bean
    public WebClient webClient(){
        log.info("Creating WebClient");
        return WebClient.builder()
                        .filter(new ServletBearerExchangeFilterFunction())
                        .build();
    }
}
