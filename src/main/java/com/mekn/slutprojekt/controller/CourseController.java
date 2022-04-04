package com.mekn.slutprojekt.controller;

import com.mekn.slutprojekt.model.Course;
import com.mekn.slutprojekt.model.Ingredient;
import com.mekn.slutprojekt.service.CourseService;
import com.mekn.slutprojekt.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@SessionAttributes("course")
@Controller
public class CourseController {

    private CourseService courseService;
    private IngredientService ingredientService;
    private boolean isNewCourse = false;
    private Integer id;

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

    @GetMapping("/course/showmenu/groceries")
    public String showGroceriesList(Model model) {
        Set<Ingredient> ingredients = ingredientService.showIngredients();
        model.addAttribute("ingredients", ingredients);

        return "ingredients";
    }

    @GetMapping("/course/new")
    public String addNewCourse(Model model) {
        isNewCourse = true;
        Course course = new Course();
        List<Ingredient> ingredientsList = ingredientService.getAllIngredients();
        model.addAttribute("course", course);
        boolean isVegetarian = false;
        model.addAttribute("vegetarian", isVegetarian);
        model.addAttribute("pageTitle", "Add New Course");
        model.addAttribute("ingredientsList", ingredientsList);
        model.addAttribute("ingredientLabel", "Add new ingredient:");
        model.addAttribute("ingredient", new Ingredient());
        return "course_form";
    }

    @PostMapping("/course/save/ingredient")
    public String saveIngredient(Ingredient ingredient, Model model) {
        ingredientService.saveIngredient(ingredient);

        if (isNewCourse) {
            return addNewCourse(model);
        } else {
            return updateCourse(id, model);
        }
    }


    @GetMapping("/course/delete/{id}")
    public String deleteCourse(@PathVariable("id") Integer id) {
        courseService.deleteCourseById(id);
        return "redirect:/course";
    }

    @GetMapping("/course/edit/{id}")
    public String updateCourse(@PathVariable("id") Integer id, Model model) {
        this.id = id;
        Course course = courseService.findById(id);
        List<Ingredient> ingredientsList = ingredientService.getAllIngredients();
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("course", course);
        model.addAttribute("pageTitle", "Edit Course (ID: " + id + ")");
        model.addAttribute("ingredientsList", ingredientsList);
        model.addAttribute("ingredientLabel", "Edit ingredient:");

        return "course_form";
    }

    @PostMapping("/course/save")
    public String saveCourse(Course course) {
        courseService.saveCourse(course);
        return "redirect:/course";
    }

    @GetMapping("/course/showmenu")
    public String showMenuPage(Model model) {

        List<Course> menu = courseService.randomCourseList();

        model.addAttribute("menu", menu);

        return "random_courses";
    }

    @GetMapping("/course/vegetarian")
    public String showVegetarianCourses(Model model) {

        List<Course> vegetarianCourses = courseService.vegetarianCourses();
        model.addAttribute("vegetarian_courses", vegetarianCourses);
        return "vegetarian_courses";
    }


}


