package com.mekn.slutprojekt.service;

import com.mekn.slutprojekt.dao.CourseRepository;
import com.mekn.slutprojekt.model.Course;
import com.mekn.slutprojekt.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAllCourse() {
        return (List<Course>) courseRepository.findAll();
    }

    public List<Course> randomCourseList() {

        List<Course> courses = findAllCourse();
        List<Course> randomCourseList = new ArrayList<>();

        if (courses.size() < 7) {
            ThreadLocalRandom.current().ints(0, courses.size()).
                    distinct().limit(courses.size()).forEach(c -> randomCourseList.add(courses.get(c)));
        } else {
            ThreadLocalRandom.current().ints(0, courses.size()).
                    distinct().limit(7).forEach(c -> randomCourseList.add(courses.get(c)));
        }

        return randomCourseList;
    }

    public List<Course> vegetarianCourses(){
        List<Course> courses = findAllCourse();
        List<Course> vegetarianCourses = checkIfVegetarian(courses);
        List<Course> randomVegetarianCourses = new ArrayList<>();
        if(vegetarianCourses.size() < 7) {
            ThreadLocalRandom.current().ints(0, vegetarianCourses.size()).
                    distinct().limit(vegetarianCourses.size());
            return vegetarianCourses;
        }else {
            ThreadLocalRandom.current().ints(0, vegetarianCourses.size()).
                    distinct().limit(7).forEach(r -> randomVegetarianCourses.add(vegetarianCourses.get(r)));
            return randomVegetarianCourses;
        }

    }

    public List<Course> checkIfVegetarian(List<Course> courses){
        List<Course> vegetarianCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.isVegetarian() == true){
                vegetarianCourses.add(course);
            }
        }
        return vegetarianCourses;
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourseById(Integer id) {
        courseRepository.deleteById(id);
    }

    public Course findById(Integer id) {

        return courseRepository.findById(id).get();

    }
}
