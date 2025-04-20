package com.example.demo.repositories;

import com.example.demo.entities.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);
    List<Customer> findByFNameContainingIgnoreCase(String name);
    List<Customer> findByLNameContainingIgnoreCase(String name);
    List<Customer> findByBirthDateBefore(LocalDate date);
    List<Customer> findByBirthDateBetween(LocalDate date);
    boolean existsByEmail(String email);
    List<Customer> findByGender(String gender);
}
