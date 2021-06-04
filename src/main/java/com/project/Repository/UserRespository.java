package com.project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.User;


@Repository
public interface UserRespository extends JpaRepository<User,Long>{

	
	User  findByUsername(String username);
}
