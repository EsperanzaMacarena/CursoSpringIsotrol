package com.lm2a.sb.model;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class Order {
	@NotBlank(message="El nombre es obligatorio")
	private String name;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	//@CreditCardNumber(message="No es una tarjeta valida")
	private String ccNumber;
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="CVV no valido")
	private String ccVV;

}
