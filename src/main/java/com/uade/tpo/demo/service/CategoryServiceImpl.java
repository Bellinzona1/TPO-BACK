package com.uade.tpo.demo.service;


import com.uade.tpo.demo.entity.Category;
import com.uade.tpo.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ArrayList<Category> getCategories(){
        return (ArrayList<Category>) categoryRepository.findAll();


    }


    public Optional getCategoryById(Long id){
        return categoryRepository.findById(id);
    }


    public ResponseEntity<Category> postCategory(@RequestBody Category category){
        Category createdCategory = categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);

    }

    public ResponseEntity<String> deleteCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            return ResponseEntity.ok("Category deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
    }


    @Override
    public ResponseEntity<Category> updateCategory(Category category) {
        categoryRepository.save(category);
        return ResponseEntity.ok(category);
    }
}
