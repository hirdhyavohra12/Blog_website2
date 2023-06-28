package com.blog.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.model.Blogs;

public interface BlogRepository extends JpaRepository<Blogs,Integer>{
	List<Blogs> findAllByCategory_Id(int id);

}
