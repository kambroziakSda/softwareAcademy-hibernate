package hibernate.core;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;

    private String city;

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    Address() {
    }
}
