package com.temal.chakib.contacts;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String age;
    private boolean female;

    public Contact(String firstName, String lastName, String phoneNumber, String age, boolean female) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.female = female;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    @Override
    public String toString() {
        return firstName + "." + lastName + "." +  phoneNumber + '\'' +
                age + "." +
                female +
                '}';
    }


}
