package app.domain;

import app.clients.ResourceServerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExternalRepository {

    private final ResourceServerClient resourceServerClient;

    public String getUsers(){
        return resourceServerClient.fetchUser();
    }
}
