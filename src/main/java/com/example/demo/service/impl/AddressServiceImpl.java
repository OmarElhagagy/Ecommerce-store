package com.example.demo.service.impl;

import com.example.demo.entities.Address;
import com.example.demo.entities.Customer;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AddressServiceImpl (AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }
}
