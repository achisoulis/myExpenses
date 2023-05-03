package com.project.myExpenses.repository;


import com.project.myExpenses.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

////	User findByEmail(String email);


	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);

}
