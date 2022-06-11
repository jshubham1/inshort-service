package com.inshort.inshortservice.service;

import com.inshort.inshortservice.entity.Article;
import com.inshort.inshortservice.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {
    private ArticleRepository articleRepository;

    public List<Article> getArticle() {
        return articleRepository.findAll();
    }

    public void createArticle(Article article) {
        articleRepository.save(article);
    }

    public void updateArticle(Long id, Article article) {
        article.setId(id);
        articleRepository.save(article);
    }

    public void patchArticle(Long id, Map<String, Object> patchRequest) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            patchRequest.forEach((k, v) -> {
                Field field = ReflectionUtils.findField(Article.class, k);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, article, v);
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field with " + k + " name not found");
                }
            });
            articleRepository.save(article);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with id " + id + " not found");
        }

    }

    public void deleteArticle(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            articleRepository.delete(optionalArticle.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with id " + id + " not found");
        }
    }
}
