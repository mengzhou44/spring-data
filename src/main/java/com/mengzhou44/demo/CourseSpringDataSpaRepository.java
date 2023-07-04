package com.mengzhou44.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSpringDataSpaRepository extends JpaRepository<Course, Long> {
    
}
