package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.Article;
import com.uade.tpo.demo.entity.Category;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

public interface CategoryService {

    public ArrayList<Category> getCategories();

    public Optional getCategoryById(Long id);

    public ResponseEntity<Category> postCategory(Category category);

    public ResponseEntity<String> deleteCategoryById(Long id);

    public ResponseEntity<Category> updateCategory(Category category);

}
