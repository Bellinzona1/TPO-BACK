package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.Article;
import com.uade.tpo.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public ArrayList<Article> getAllArticles() {
        return (ArrayList<Article>) articleRepository.findAll();
    }

    @Override
    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public ResponseEntity<Article> addArticle(Article article) {
        Article createdArticle = articleRepository.save(article);
        return ResponseEntity.ok(createdArticle);
    }
}
