package com.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.model.Blogs;
import com.blog.repository.BlogRepository;

@Service
public class BlogService {

	@Autowired
	BlogRepository blogRepository;
	
	
	public void addBlog(Blogs blog) {
		this.blogRepository.save(blog);
	}
	
	public List<Blogs> getAllBlogs() {
		return this.blogRepository.findAll();
	}
	
	public void deleteBlogById(int id) {
		this.blogRepository.deleteById(id);
	}
	
	public Optional<Blogs> getBlogById(int id) {
		return this.blogRepository.findById(id);		
	}
	
	public List<Blogs> getAllBlogsByCategory(int id){
		return this.blogRepository.findAllByCategory_Id(id); //method we defined in BlogRepository to get all blogs in particular category
	}
	
}
