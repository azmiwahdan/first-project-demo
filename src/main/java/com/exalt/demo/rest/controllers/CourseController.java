package com.exalt.demo.rest.controllers;

import com.exalt.demo.rest.models.Course;
import com.exalt.demo.rest.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> allCourses = courseService.getAllCourses();
        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Course>> getSortedCourses() {
        List<Course> sortedCourses = courseService.getSortedCourses();
        return new ResponseEntity<>(sortedCourses, HttpStatus.OK);
    }

    @GetMapping("/courseById/{courseId}")
    public ResponseEntity<Optional<Course>> findCourseById(@PathVariable Long courseId) {
        Optional<Course> course = courseService.findCourseById(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestParam(name = "category") Long categoryId, @RequestBody Course course) {
        Course addedCourse = courseService.addCourse(categoryId, course);
        return new ResponseEntity<>(addedCourse, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Course> updateCourse(@RequestParam(name = "id") Long courseId, @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(courseId, course);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public void deleteCourseById(@RequestParam(name = "id") Long courseId) {
        courseService.deleteCourse(courseId);
    }
}
