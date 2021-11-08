package com.lm2a.sb.data;

import java.util.List;

import com.lm2a.sb.model.Ingredient;


public interface IngredientRepository {

    List<Ingredient> findAll();

    Ingredient findById(String id);

    Ingredient save(Ingredient ingredient);
}
