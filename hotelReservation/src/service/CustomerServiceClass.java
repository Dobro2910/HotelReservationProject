package service;

import model.CustomerClass;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class CustomerServiceClass {
    private static HashMap<String, CustomerClass> customerMap = new HashMap<String, CustomerClass>();

    public static void addCustomer(String email, String firstName, String lastName){
        CustomerClass newCustomer = new CustomerClass(firstName, lastName, email);
        customerMap.put(newCustomer.getEmail(), newCustomer);
    }
// inputting customer detail into the customer map with the key as the customer email

    public static CustomerClass getCustomer(String customerEmail){
        return customerMap.get(customerEmail);
    }
// return user detail(value) by asking the user email(key)
    public static Collection<CustomerClass> getAllCustomer(){
        return customerMap.values();
    }
// return all customer value from the map collection

}
