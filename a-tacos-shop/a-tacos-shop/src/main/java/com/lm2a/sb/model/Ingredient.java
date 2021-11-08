package com.lm2a.sb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//en clase pojo, el allargs y el noargs
//@AllArgsConstructor
//@NoArgsConstructor
//en servicios, y controller el q usa el ahora

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Ingredient {
	private String id;
	private String name;
	private Type type;
	
	public static enum Type{
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}

}
