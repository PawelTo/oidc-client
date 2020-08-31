package app.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class ResourceServerClientImpl implements ResourceServerClient {

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
    private final WebClient webClient;

    @Override
    public String fetchUser() {
        return webClient.get()
                .uri("http://localhost:8082/api/v1/users")
                //.headers(h ->h.setBearerAuth(extractAccessToken()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private String extractAccessToken() {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthorizedClient client = oAuth2AuthorizedClientService.loadAuthorizedClient(token.getAuthorizedClientRegistrationId(),token.getName());
        String accessToken = client.getAccessToken().getTokenValue();
        return accessToken;
    }
}
