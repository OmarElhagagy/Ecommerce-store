package com.example.demo.service.impl;

import com.example.demo.entities.Customer;
import com.example.demo.entities.CustomerOrder;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.CustomerOrderRepository;
import com.example.demo.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerOrderRepository customerOrderRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerOrderRepository customerOrderRepository) {
        this.customerRepository = customerRepository;
        this.customerOrderRepository = customerOrderRepository;
    }

    // validator method
    private void validateCustomer(Customer customer) {
        List<String> errors = new ArrayList<>();

        if (customer.getFName() == null || customer.getFName().trim().isEmpty()) {
            errors.add("First name is required");
        }

        if(customer.getLName() == null || customer.getLName().trim().isEmpty()) {
            errors.add("Last name is required");
        }

        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            errors.add("Email is required");
        } else if (!isValidEmail(customer.getEmail())) {
            errors.add("Invalid email format");
        }

        if (customer.getGender() == null || customer.getGender().trim().isEmpty()) {
            errors.add("Gender is required");
        }

        if (customer.getBirthDate() == null) {
            errors.add("Birth date is required");
        } else if (customer.getBirthDate().isAfter(LocalDate.now())) {
            errors.add("Birth date cannot be in the future");
        }

        // throw combined exceptions if errors found
        if(!errors.isEmpty()) {
            throw new IllegalArgumentException("Validation errors found" + String.join(", ", errors));
        }
    }

    // validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
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

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findByGender(String gender) {
        return customerRepository.findByGender(gender);
    }

    @Override
    public Customer save(Customer customer) {
        validateCustomer(customer);

        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        // check if customer exists
        if (customer.getId() == null || !customerRepository.existsById(customer.getId())) {
            throw new EntityNotFoundException("Cannot update non-existent customer");
        }

        // validate customer data
        validateCustomer(customer);

        // check for duplicate email (excluding current customer)
        Optional<Customer> existsWithEmail = customerRepository.findByEmail(customer.getEmail());
        if (existsWithEmail.isPresent() && !existsWithEmail.get().getId().equals(customer.getId())) {
            throw new IllegalArgumentException("This email is already used by another customer");
        }
        return customerRepository.save(customer);
    }
}
