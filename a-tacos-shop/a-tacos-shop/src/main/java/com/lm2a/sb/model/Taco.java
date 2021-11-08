package com.lm2a.sb.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {
    @NotNull
    private Long id;

	@NotNull
	@Size(min=5, message="El nombre debe tener al menos 5 caractéres")
	private String name;
	
    private List<Ingredient> ingredients;

    @NotNull
    private Date createdAt;
}
