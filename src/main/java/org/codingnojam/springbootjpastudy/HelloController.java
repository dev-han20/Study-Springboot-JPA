package org.codingnojam.springbootjpastudy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping(value = "/")
    public String gotohello(Model model) {
        System.out.println("hello");

        return "hello";
    }
}
