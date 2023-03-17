package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        //변수 등록
        model.addAttribute("username", "hanbi");
        return "greetings"; //templates안에 greetings.mustache -> 브라우저로 전송
    }
    @GetMapping("/bye")
    public String seeYouuNext(Model model){
        model.addAttribute("nickname", "hamba");
        return "goodbye";
    }
}
