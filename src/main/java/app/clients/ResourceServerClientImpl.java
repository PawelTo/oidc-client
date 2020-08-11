package app.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class ResourceServerClientImpl implements ResourceServerClient {

    private final WebClient webClient;

    @Override
    public String fetchUser() {
        return webClient.get()
                .uri("http://localhost:8082/api/v1/users")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
