package com.mekn.slutprojekt.service;

import com.mekn.slutprojekt.dao.CourseRepository;
import com.mekn.slutprojekt.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAllCourse() {
        return (List<Course>) courseRepository.findAll();
    }


    public Course findSingleCourse(Integer id) {
        return courseRepository.findById(id).get();
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteCourseById(Integer id) {
        courseRepository.deleteById(id);
    }

    public Course updateCourseById(Integer id) {
        Course updateCourse = courseRepository.findById(id).get();
        return courseRepository.save(updateCourse);
    }

}
