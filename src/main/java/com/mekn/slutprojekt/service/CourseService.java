package com.mekn.slutprojekt.service;

import com.mekn.slutprojekt.dao.CourseRepository;
import com.mekn.slutprojekt.model.Course;
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

    public List<Course> randomCourseList(){

        List<Course> courses = findAllCourse();
        List<Course> randomCourseList = new ArrayList<>();

        ThreadLocalRandom.current().ints(0, courses.size()).
                distinct().limit(7).forEach(c -> randomCourseList.add(courses.get(c)));

        return randomCourseList;
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
