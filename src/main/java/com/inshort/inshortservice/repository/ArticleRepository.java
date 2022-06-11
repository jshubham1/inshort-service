package com.inshort.inshortservice.repository;

import com.inshort.inshortservice.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article, Long> {
}
