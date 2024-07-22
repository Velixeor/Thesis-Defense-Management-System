package ru.tubryansk.tdms.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class TestController {
    @GetMapping
    public String root() {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                <title>Page Title</title>
                </head>
                <body>
                <h1>My First Heading</h1>
                </body>
                </html>
                """;
    }
}
