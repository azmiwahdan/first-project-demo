package com.exalt.demo.rest.models;

import lombok.*;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Course {
    @Id
    private Long id;
    private String name;
    private String description;
    private Category category;
}
