package app.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(V1UserController.API_PATH)
@RequiredArgsConstructor
@RestController
@Tag(name = V1UserController.API_PATH)
public class V1UserController {

    static final String API_PATH = "/api/v1/users";

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public String getAllUsers(){
        return "All Users";
    }
}
