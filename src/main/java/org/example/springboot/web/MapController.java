package org.example.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @GetMapping("/maps")
    public String mapsHome() {
        return "maps.html";
    }
}
