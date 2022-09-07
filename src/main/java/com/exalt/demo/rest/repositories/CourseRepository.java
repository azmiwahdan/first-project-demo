package com.exalt.demo.rest.repositories;

import com.exalt.demo.rest.models.Course;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends AerospikeRepository<Course, Long> {
}
