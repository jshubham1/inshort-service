package com.inshort.inshortservice.controller;

import com.inshort.inshortservice.entity.Article;
import com.inshort.inshortservice.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class ArticleController {
    private ArticleService articleService;

    @GetMapping(value = "/article")
    public List<Article> getArticle() {
        return articleService.getArticle();
    }

    @PostMapping(value = "/article")
    public ResponseEntity<String> createArticle(@RequestBody Article article) {
        articleService.createArticle(article);
        return new ResponseEntity<>("Successfully added new article", HttpStatus.CREATED);
    }

    @PutMapping(value = "/article/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        articleService.updateArticle(id, article);
        return new ResponseEntity<>("Successfully created/updated the article", HttpStatus.OK);

    }

    @PatchMapping(value = "/article/{id}")
    public ResponseEntity<String> patchArticle(@PathVariable Long id, @RequestBody Map<String, Object> patchRequest) {
        articleService.patchArticle(id, patchRequest);
        return new ResponseEntity<>("Successfully patched the article", HttpStatus.OK);
    }

    @DeleteMapping(value = "/article/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>("Successfully deleted the article", HttpStatus.OK);
    }
}
