package com.lm2a.sb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Getter;

@Controller
public class HomeController {

	//Lo lanzare cuando navege a la raiz
	@GetMapping("/")
	public String home() {
		return "home";
	}
}
