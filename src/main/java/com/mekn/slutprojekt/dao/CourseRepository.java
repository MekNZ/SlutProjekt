package com.mekn.slutprojekt.dao;

import com.mekn.slutprojekt.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer> {
}
