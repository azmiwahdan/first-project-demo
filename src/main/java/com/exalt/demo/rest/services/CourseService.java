package com.exalt.demo.rest.services;

import com.exalt.demo.rest.models.Category;
import com.exalt.demo.rest.models.Course;
import com.exalt.demo.rest.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private List<Category> categories = new ArrayList<>();

    @Autowired
    private final CourseRepository courseRepository;
    private final CategoryService categoryService;

    public CourseService(CourseRepository courseRepository, CategoryService categoryService) {
        this.courseRepository = courseRepository;
        this.categoryService = categoryService;
        Thread thread = new Thread(new CategoryThread());
        thread.start();
    }

    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    public List<Course> getSortedCourses() {
        return getAllCourses().stream()
                .sorted(Comparator.comparing(Course::getName))
                .collect(Collectors.toList());
    }

    public Course addCourse(Long categoryId, Course course) {
        Optional<Category> category = categories.stream()
                .filter(category1 -> category1.getId().equals(categoryId))
                .findAny();

        if (category.isPresent()) {
            category.get().getCourses().add(course);
            categoryService.updateCategory(categoryId, category.get());
            return courseRepository.save(course);
        }
        return null;
    }

    public Optional<Course> findCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    public Course updateCourse(Long courseId, Course course) {
        if (findCourseById(courseId).isPresent()) {
            Course existingCourse = findCourseById(courseId).get();
            existingCourse.setName(course.getName());
            existingCourse.setDescription(course.getDescription());
            return courseRepository.save(existingCourse);
        }
        return null;
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    class CategoryThread implements Runnable {
        @Override
        public void run() {
            categories = categoryService.getAllCategories();
        }
    }
}
