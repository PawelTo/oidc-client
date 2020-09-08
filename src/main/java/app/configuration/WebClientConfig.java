package app.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.server.resource.web.reactive.function.client.ServerBearerExchangeFilterFunction;
import org.springframework.security.oauth2.server.resource.web.reactive.function.client.ServletBearerExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
@Slf4j
public class WebClientConfig {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;

    @PostConstruct
    public void postConstruct(){
        log.info("Configuring web client");
    }

    @Bean
    public WebClient webClient(){
        log.info("Creating WebClient");
        //ServletOAuth2AuthorizedClientExchangeFilterFunction oAuth2AuthorizedClientExchangeFilterFunction =
        //        new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, oAuth2AuthorizedClientRepository);
        //oAuth2AuthorizedClientExchangeFilterFunction.setDefaultOAuth2AuthorizedClient(true);
        //oAuth2AuthorizedClientExchangeFilterFunction.setDefaultClientRegistrationId("my-oidc");
        return WebClient.builder()
                        //.filter(oAuth2AuthorizedClientExchangeFilterFunction)
                        .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            StringBuilder sb = new StringBuilder("Request: \n");
            //append clientRequest method and url
            clientRequest
                    .headers()
                    .forEach((name, values) -> values.forEach(value -> sb.append(value)/* append header key/value */));
            log.debug(sb.toString());
            return Mono.just(clientRequest);
        });
    }
}
