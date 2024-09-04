package com.uade.tpo.demo.controllers;

import com.uade.tpo.demo.entity.Category;
import com.uade.tpo.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/")
    public ArrayList<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public Optional getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> postCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.postCategory(category).getBody();
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);

    }

    @DeleteMapping("/id")
    public Optional deleteCategory(@RequestBody Long id) {
        return categoryService.deleteCategoryById(id);
    }

}
