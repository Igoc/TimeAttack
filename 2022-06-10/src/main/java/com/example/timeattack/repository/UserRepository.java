package com.example.timeattack.repository;

import com.example.timeattack.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAllByAgeAndGenderNotOrderByCreatedDateAsc(int age, String gender);
}
