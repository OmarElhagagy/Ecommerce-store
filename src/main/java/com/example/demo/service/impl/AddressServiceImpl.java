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

    @Override
    public Address createAddress(Address address) {
        // Ensure customer exists
        if (address.getCustomer() == null || address.getCustomer().getId() == null) {
            throw new IllegalArgumentException("Customer information required when creating an address");
        }

        Customer customer = customerRepository.findById(address.getCustomer().getId())
            .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID " + address.getCustomer().getId()));

        // If this is the customer's first address, make it default
        List<Address> existingAddresses = addressRepository.findByCustomerId(customer.getId());
        if (existingAddresses.isEmpty()) {
            address.setIsDefault(true);
        }

        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress (Address address) {
        // does address exist
        if (address.getId() == null || !addressRepository.existsById(address.getId())) {
            throw new EntityNotFoundException("Cannot update non-existent address");
        }
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Integer id) {
        // check if this is default address
        Optional<Address> addressOpt = addressRepository.findById(id);
        if (addressOpt.isPresent() && addressOpt.get().getIsDefault()) {
            Address address = addressOpt.get();

            // Find another address for this customer to make default
            List<Address> otherAddresses = addressRepository
                .findByCustomerIdAndIdNot(address.getCustomer().getId(), id);
        }
    }
}
