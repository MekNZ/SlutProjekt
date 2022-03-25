package com.mekn.slutprojekt.service;

import com.mekn.slutprojekt.dao.IngredientRepository;
import com.mekn.slutprojekt.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public void createIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredientById(Integer id) {
        Ingredient ingredientToUpdate = ingredientRepository.findById(id).get();
        return ingredientRepository.save(ingredientToUpdate);
    }

    public Ingredient findIngredientByNameInService(String name) {
        return ingredientRepository.findIngredientByName(name);
    }

}
