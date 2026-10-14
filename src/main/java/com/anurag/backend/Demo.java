package com.anurag.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {// API
    @GetMapping("/") // endpoint
    public String m1() {
        return "hello";
    }

    @GetMapping("/name")
    public String m2() {
        return "RAMAVATH ARUN";

    }

    @GetMapping("/roll")
    public String m3() {
        return "25EG504H01";
    }

    @GetMapping("/dept")
    public String m4() {
        return "ECE";
    }
}
