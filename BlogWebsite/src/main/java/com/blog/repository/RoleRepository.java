package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.model.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer>{

}
