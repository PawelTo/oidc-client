package app.controllers;

import app.domain.ExternalRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(V1ExternalController.API_PATH)
@RequiredArgsConstructor
@RestController
@Tag(name =  V1ExternalController.API_PATH)
public class V1ExternalController {
    
    static final String API_PATH= "/api/v1/external";
    
    private final ExternalRepository externalRepository;

    @GetMapping(path = "/users")
    public String getUsers(){
        return externalRepository.getUsers();
    }
}
