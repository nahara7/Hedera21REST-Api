/*package com.nahara.toka.repository;

import com.nahara.toka.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Qualifier("user")
@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//entity class and id
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}*/
