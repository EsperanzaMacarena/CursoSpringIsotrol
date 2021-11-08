package com.lm2a.sb.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lm2a.sb.model.Order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

	 @GetMapping("/current")
	  public String orderForm(Model model) {
		 model.addAttribute("order", new Order());
		 return "orderForm";
	 }
	 
	 @PostMapping
	 public String processOrder(Model model, @Valid Order order, Errors errors) {
		 if(errors.hasErrors()) {
			 model.addAttribute("order ", order);
			 log.info("Va a entrar otra orden rechazada: "+order);
		 }
		 log.info("Orden entrada: "+order);
		 return "redirect:/";
	 }
}
