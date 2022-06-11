package com.inshort.inshortservice.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Data
public class Article {
    @Id
    private long id;
    private String title;
    private String name;
    private String description;
    private String author;
    @CreatedDate
    private Instant createTimestamp = Instant.now();
}
