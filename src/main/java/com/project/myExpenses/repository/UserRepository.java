package com.project.myExpenses.repository;


import com.project.myExpenses.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);


	User save(User user);
}
