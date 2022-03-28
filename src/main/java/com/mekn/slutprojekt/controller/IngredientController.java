package com.mekn.slutprojekt.controller;

import com.mekn.slutprojekt.model.Course;
import com.mekn.slutprojekt.model.Ingredient;
import com.mekn.slutprojekt.service.CourseService;
import com.mekn.slutprojekt.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IngredientController {

    @Autowired
    CourseService courseService;

    @Autowired
    IngredientService ingredientService;

    @GetMapping
    public String addCourseWithIngredients(Course course, Ingredient ingredient){
        ingredientService.connectNewIngredientToNewCourse(ingredientService.saveIngredient(ingredient),
                courseService.saveCourse(course));
        return "redirect:/course_form";
    }
    @GetMapping
    public String addIngredientToCourse(Ingredient ingredient){
        ingredientService.saveIngredient(ingredient);
        return "redirect:/course_form";
    }
}
