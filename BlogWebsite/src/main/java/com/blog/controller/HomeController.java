package com.blog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.blog.model.Blogs;
import com.blog.service.BlogService;
import com.blog.service.CategoryService;

@Controller
public class HomeController {
	@Autowired
	CategoryService categoryService;

	@Autowired
	BlogService blogService;

	@GetMapping("/blogs")
	public String viewBlogs(Model model) {
		model.addAttribute("blogs", blogService.getAllBlogs());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "showBlog";

	}

	@GetMapping("/blogs/category/{id}")
	public String viewBlogsByCategory(@PathVariable int id, Model model) {

		List<Blogs> blogs = blogService.getAllBlogsByCategory(id);
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("blogs", blogs);

		return "showBlog";

	}
	
	@GetMapping("/blog/viewblog/{id}")
	public String viewParicularBlog(@PathVariable int id , Model model) {
		
		model.addAttribute(blogService.getBlogById(id).get());
		return "viewBlog";
	}

}
