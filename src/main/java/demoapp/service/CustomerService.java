package demoapp.service;

import demoapp.model.entities.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    Customer save(Customer customer);
    Customer update(Customer customer);
    void deleteById(Long id);
    void delete(Customer customer);
}
