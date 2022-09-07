package com.exalt.demo.rest.repositories;

import com.exalt.demo.rest.models.Category;
import org.springframework.data.aerospike.repository.AerospikeRepository;

public interface CategoryRepository extends AerospikeRepository<Category, Long> {
}
