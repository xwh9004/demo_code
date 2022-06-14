package com.demo.elasticsearch.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
@Data
@Document(indexName = "student_1")
public class Student {
    @Id
    @Field(type = FieldType.Keyword)
    private String studentId;

    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Keyword)
    private List<String> courses;
}
