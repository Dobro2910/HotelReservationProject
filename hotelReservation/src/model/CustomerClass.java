package model;

import service.CustomerServiceClass;

import java.util.Objects;
import java.util.regex.Pattern;

public class CustomerClass {
    String firstName;
    String lastName;
    String email;
    String emailRegex = "^(.+)@(.+).(.+)$";
    Pattern pattern = Pattern.compile(emailRegex);

    public CustomerClass(String firstName, String lastName, String email) {
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Error, invalid email");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public String getFirstName() {

        return this.firstName;
    }

    public String getLastName() {

        return this.lastName;
    }

    public String getEmail() {

        return this.email;
    }

    @Override
    public String toString() {
        return "First Name: " + this.firstName + " Last Name " + this.lastName + " Email: " + this.email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerClass)) return false;
        CustomerClass customer = (CustomerClass) o;
        return firstName.equals(customer.firstName) && lastName.equals(customer.lastName) && email.equals(customer.email);
    }
    // to check if 2 input is the same or not, in this case, check the (firstname, lastname and email) of the user, if they are the same, return the input

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, email);
    }
    // reject the same input when inputting into a collection
}
