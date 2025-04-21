package com.example.demo.service.impl;

import com.example.demo.entities.Customer;
import com.example.demo.entities.CustomerOrder;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.CustomerOrderRepository;
import com.example.demo.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerOrderRepository customerOrderRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerOrderRepository customerOrderRepository) {
        this.customerRepository = customerRepository;
        this.customerOrderRepository = customerOrderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findByLastName(String name) {
        return customerRepository.findByLNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findByFirstName(String name) {
        return customerRepository.findByFNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findByBirthDateBefore(LocalDate date) {
        return customerRepository.findByBirthDateBefore(date);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findByBirthDateRange(LocalDate startDate, LocalDate endDate) {
        return customerRepository.findByBirthDateBetween(startDate, endDate);
    }
} 
