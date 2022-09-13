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

    /**
     * function to get all courses.
     *
     * @return list of all courses.
     */

    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    /**
     * function to get all course sorted.
     *
     * @return list of sorted courses.
     */
    public List<Course> getSortedCourses() {
        return getAllCourses().stream()
                .sorted(Comparator.comparing(Course::getName))
                .collect(Collectors.toList());
    }

    /**
     * function to add course in a specific category.
     *
     * @param categoryId category's id.
     * @param course     course to add.
     * @return the added course.
     */

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

    /**
     * function to find course by id.
     *
     * @param courseId course id .
     * @return
     */
    public Optional<Course> findCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    /**
     * function to update a specific course.
     *
     * @param courseId course id.
     * @param course   course to update.
     * @return
     */
    public Course updateCourse(Long courseId, Course course) {
        if (findCourseById(courseId).isPresent()) {
            Course existingCourse = findCourseById(courseId).get();
            existingCourse.setName(course.getName());
            existingCourse.setDescription(course.getDescription());
            return courseRepository.save(existingCourse);
        }
        return null;
    }

    /**
     * function to delete a specific course.
     *
     * @param courseId
     */
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
