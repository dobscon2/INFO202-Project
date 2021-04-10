/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import domain.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author dobco402
 */
public class CustomerDAO {

    private static final Map<String, Customer> customers = new TreeMap<String, Customer>();

    static {
        if (customers.isEmpty()) {
            customers.put("CUSTOMER1", new Customer("CUSTOMER1", "testy@tester.com", "testy", "Testy", "Tester", "Test Group"));
        }
    }
    
    public List<Customer> getCustomers() {
        return new ArrayList<Customer>(customers.values());
    }
    
    public void createCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }
    
    public Customer getCustomerById(String id) {
        return customers.get(id);
    }
    
    public void deleteCustomer(String id) {
        customers.remove(id);
    }
    
    public void updateCustomer(String id, Customer updatedCustomer) {
        customers.put(id, updatedCustomer);
    }
    
    public boolean exists(String id) {
        System.out.println(customers.containsKey(id));
        return customers.containsKey(id);
    }
}
