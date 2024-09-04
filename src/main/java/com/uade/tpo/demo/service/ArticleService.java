package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.Article;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

public interface ArticleService {

    public ArrayList<Article> getAllArticles();

    public Optional getArticleById(Long id);

    public ResponseEntity<Article> addArticle(Article article);
}
