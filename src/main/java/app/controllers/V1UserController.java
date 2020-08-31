package app.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(V1UserController.API_PATH)
@RequiredArgsConstructor
@RestController
@Tag(name = V1UserController.API_PATH)
public class V1UserController {

    static final String API_PATH = "/api/v1/users";

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public String getAllUsers(){
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthorizedClient client = oAuth2AuthorizedClientService.loadAuthorizedClient(token.getAuthorizedClientRegistrationId(),token.getName());
        String accessToken = client.getAccessToken().getTokenValue();
        return "All Users";
    }
}
