package com.uade.tpo.demo.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String content;

    // Relación con User
    @ManyToOne
    @JsonBackReference(value = "user-article")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relación con Category
    @ManyToOne
    @JsonBackReference(value = "category-article")
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}

