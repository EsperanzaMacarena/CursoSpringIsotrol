package com.lm2a.sb.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lm2a.sb.data.IngredientRepository;
import com.lm2a.sb.data.TacoRepository;
import com.lm2a.sb.model.Ingredient;
import com.lm2a.sb.model.Ingredient.Type;
import com.lm2a.sb.model.Taco;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@RequiredArgsConstructor
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @GetMapping
    public String showDesignForm(Model model) {
        populateIngredients(model);
        model.addAttribute("tktn", new Taco());
        return "design";
    }

    @PostMapping
    // Validación a nivel de controlador
    public String processDesign(@Valid @ModelAttribute(name = "tktn") Taco design, Errors errors) {
        if (errors.hasErrors()) {
            return "design";
        }
        tacoRepository.save(design);

        return "redirect:/orders/current";

    }

    // Filtramos todos los items siempre que coincide con el item de la lista de
    // ingredientes que meta el filtro
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

    private void populateIngredients(Model model) {
        Type[] types = Ingredient.Type.values();
        // Hacemos una iteracion para que aparezcan todos los ingredientes y los metemos
        // en el modelo
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredientRepository.findAll(), type));
        }
    }
}
