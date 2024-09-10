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


    @PutMapping("/editCategory/{id}")
    public ResponseEntity<Category> EditCategory(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> categoryOptional = categoryService.getCategoryById(id);
        if (categoryOptional.isPresent()) {
            Category categoryEdit = categoryOptional.get();

            categoryEdit.setName(category.getName());


            Category categoryEdited = categoryService.updateCategory(categoryEdit).getBody();
            return ResponseEntity.ok(categoryEdited);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategoryById(id);
    }

}
