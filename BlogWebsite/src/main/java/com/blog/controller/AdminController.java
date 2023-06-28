package com.blog.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.blog.dao.blogDao;
import com.blog.model.Blogs;
import com.blog.model.Category;
import com.blog.service.BlogService;
import com.blog.service.CategoryService;

@Controller
public class AdminController {
	
//	public static String uploadUri=System.getProperty("user.dir")+"/src/main/resources/static/blogImages";
	public static String uploadUri="C:\\Users\\Asus\\Documents\\workspace-spring-tool-suite-4-4.18.1.RELEASE\\BlogWebsite\\src\\main\\resources\\static\\blogImages";

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BlogService blogService;

	@GetMapping("/admin")
	public String homePage() {
		return "home";
	}

	@GetMapping("/admin/categories")
	public String getAdminPage(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String getBlog(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}

	@PostMapping("/admin/categories/add")
	public String postBlog(@ModelAttribute("category") Category category) {
		System.out.println(category.getName());
		System.out.println(category.getId());
		this.categoryService.addCategory(category);
		System.out.println("added successfully");
		return "redirect:/admin/categories";
	}

	@GetMapping("admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		this.categoryService.deleteCategory(id);
		return "redirect:/admin/categories";

	}

	@GetMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable int id, Model model) {
		Optional<Category> category = this.categoryService.getCategory(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}

		return "redirect:/admin/categories";

	}
	
	
	@GetMapping("/admin/blog")
	public String getBlogs(Model model) {
		model.addAttribute("blogs",blogService.getAllBlogs());
		return "blog";
	}
	
	@GetMapping("/admin/blog/add")
	public String getAddBlogs(Model model) {
		model.addAttribute("blogs",new blogDao());
		model.addAttribute("categories",categoryService.getAllCategory());
		return "blogAdd";
	}
	@PostMapping("/admin/blog/add")
	public String postAddBlogs(@ModelAttribute("blogs") blogDao bdao,@RequestParam("blogImage") MultipartFile file, 
			@RequestParam("imgName") String imageName)throws IOException {
		System.out.println(bdao.getName());
		Blogs blog=new Blogs();
		blog.setId(bdao.getId());
		blog.setName(bdao.getName());
		blog.setCategory(categoryService.getCategory(bdao.getCategoryId()).get());
		blog.setDescription(bdao.getDescription());
		String imageId;
		if(!file.isEmpty()) {
			imageId=file.getOriginalFilename();
			Path paths=Paths.get(uploadUri,imageId);
			Files.write(paths,file.getBytes());
			
		}else {
			imageId=imageName;
		}
		
		blog.setImage(imageId);
		blogService.addBlog(blog);
		return "redirect:/admin/blog";
		
	}
	
	@GetMapping("/admin/blog/delete/{id}")
	public String deleteBlogs(@PathVariable int id) {
		this.blogService.deleteBlogById(id);
		return "redirect:/admin/blog";
	}
	@GetMapping("/admin/blog/update/{id}")
	public String updateBlogs(@PathVariable int id, Model model) {
		Blogs blog=this.blogService.getBlogById(id).get();
		blogDao bdao=new blogDao();
		
		bdao.setId(blog.getId());
		bdao.setName(blog.getName());
		bdao.setCategoryId(blog.getCategory().getId());
		bdao.setDescription(blog.getDescription());
		bdao.setImage(blog.getImage());
		
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("blogs",bdao);
		
		return "blogAdd";
	}
}
