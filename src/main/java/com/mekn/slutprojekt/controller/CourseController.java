package com.mekn.slutprojekt.controller;

import com.mekn.slutprojekt.model.Course;
import com.mekn.slutprojekt.model.Ingredient;
import com.mekn.slutprojekt.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

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
        model.addAttribute("ingredient", new Ingredient());
        return "course_form";
    }

    @GetMapping("/course/delete/{id}")
    public String deleteCourse(@PathVariable("id") Integer id) {
        courseService.deleteCourseById(id);
        return "redirect:/course";
    }

    @GetMapping("/course/edit/{id}")
    public String updateCourse(@PathVariable("id") Integer id, Model model) {
        Course course = courseService.updateCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("pageTitle", "Edit Course (ID: " + id + ")");
        return "course_form";
    }

    @PostMapping("/course/save")
    public String saveCourse(Course course){
            courseService.saveCourse(course);
            return "redirect:/course";
    }


}


