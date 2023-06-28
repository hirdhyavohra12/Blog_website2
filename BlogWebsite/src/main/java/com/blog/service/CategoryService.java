package com.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.model.Category;
import com.blog.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public void addCategory(Category category) {
		this.categoryRepository.save(category);
	}
	
	public List<Category> getAllCategory(){
		return this.categoryRepository.findAll();
	}
	
	public void deleteCategory(int id) {
		this.categoryRepository.deleteById(id);
	}
	
	public Optional<Category> getCategory(int id) {
		return this.categoryRepository.findById(id);
	}

}
