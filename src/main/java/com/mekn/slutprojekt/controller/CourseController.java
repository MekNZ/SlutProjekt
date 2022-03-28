package com.mekn.slutprojekt.controller;

import com.mekn.slutprojekt.model.Course;
import com.mekn.slutprojekt.model.Ingredient;
import com.mekn.slutprojekt.service.CourseService;
import com.mekn.slutprojekt.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {


    private CourseService courseService;


    private IngredientService ingredientService;

    @Autowired
    public CourseController(CourseService courseService, IngredientService ingredientService) {
        this.courseService = courseService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/course")
    public String findAll(Model model) {
        Iterable<Course> courses = courseService.findAllCourse();
        model.addAttribute("courseList", courses);
        return "course";
    }

    @GetMapping("/course/new")
    public String addNewCourse(Model model) {
        model.addAttribute("course", new Course());
        boolean isVegetarian = false;
        model.addAttribute("vegetarian", isVegetarian);
        model.addAttribute("ingredient", new String());
        return "course_form";
    }

    @GetMapping("/course/delete/{id}")
    public String deleteCourse(@PathVariable("id") Integer id) {
        courseService.deleteCourseById(id);
        return "redirect:/course";
    }

    @GetMapping("/course/edit/{id}")
    public String updateCourse(@PathVariable("id") Integer id, Model model) {
        Course course = courseService.findById(id);
        ArrayList<String> ingredients = new ArrayList<>();
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("course", course);
        model.addAttribute("pageTitle", "Edit Course (ID: " + id + ")");
        return "course_form";
    }

    @PostMapping("/course/save")
    public String saveCourse(Course course, ArrayList<String> ingredients) {
        for (String ingredientName : ingredients) {
            course.addIngredient(new Ingredient(ingredientName));
        }
        courseService.saveCourse(course);
        return "redirect:/course";
    }

    /*@PostMapping("/course/ingredient/save")
    public String saveIngredient(Ingredient ingredient) {
        ingredientService.saveIngredient(ingredient);
        return "course_form";
    }*/

    @PostMapping("/course/ingredient/save")
    public String saveIngredient(String ingredientName, ArrayList<String> ingredients) {

        ingredients.add(ingredientName);

        return "course_form";
    }


    public String connectNewCourseToNewIngredient(Ingredient ingredient, Course course) {


        //saveIngredient(ingredient);

        ingredientService.connectNewIngredientToNewCourse(ingredient, course);

        return "";
    }

}


