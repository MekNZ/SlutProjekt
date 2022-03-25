package com.mekn.slutprojekt.dao;

import com.mekn.slutprojekt.model.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    @Query("SELECT i FROM Ingredient i WHERE i.name =:name")
    Ingredient findIngredientByName(String name);
}
