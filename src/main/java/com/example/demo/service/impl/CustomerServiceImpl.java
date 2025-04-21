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

