package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{

}
