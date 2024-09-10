package com.uade.tpo.demo.controllers;


import com.uade.tpo.demo.entity.Article;
import com.uade.tpo.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("Article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public ArrayList<Article> getCategories() {
        return articleService.getAllArticles();
    }
    @GetMapping("/{id}")
    public Optional getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        Article createdArticle = articleService.addArticle(article).getBody();
        return ResponseEntity.ok(createdArticle);
    }

    @PutMapping("/EditArticle/{id}")
    public ResponseEntity<Article> editArticle(@PathVariable Long id, @RequestBody Article article) {
        Optional<Article> articleOptional = articleService.getArticleById(id);
        if (articleOptional.isPresent()) {
            Article articleToEdit = articleOptional.get();

            articleToEdit.setName(article.getName());
            articleToEdit.setCategory(article.getCategory());
            articleToEdit.setContent(article.getContent());

            Article articleEdited = articleService.updateArticle(articleToEdit).getBody();
            return ResponseEntity.ok(articleEdited);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


    @DeleteMapping("/DeleteArticle/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id) {
        return articleService.deleteArticleById(id);

    }
}
