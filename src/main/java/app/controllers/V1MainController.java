package app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(V1MainController.API_PATH)
@RequiredArgsConstructor
@RestController
public class V1MainController {

    static final String API_PATH = "/";

    @GetMapping
    public String mainMethod(){
        return "main controller";
    }
}
