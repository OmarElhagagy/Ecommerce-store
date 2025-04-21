package com.example.demo.repositories;

import com.example.demo.entities.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);
    List<Customer> findByFNameContainingIgnoreCase(String name);
    List<Customer> findByLNameContainingIgnoreCase(String name);
    List<Customer> findByBirthDateBefore(LocalDate date);
    List<Customer> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);
    boolean existsByEmail(String email);
    List<Customer> findByGender(String gender);
    // Find only active customers
    @Query("SELECT c FROM Customer c WHERE c.active = true")
    List<Customer> findAll();
    
    @Query("SELECT c FROM Customer c WHERE c.id = :id AND c.active = true")
    Optional<Customer> findById(@Param("id") Integer id);
    
    @Query("SELECT COUNT(c) FROM Customer c WHERE c.active = true")
    long count();
}
