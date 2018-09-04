package com.example.product.app.predicate;


public class TestEmployee {
    private Integer id;
    private Integer age;
    private String gender;
    private String firstName;
    private String lastName;

    public TestEmployee(Integer id, int age, String gender, String firstName, String lastName) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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






    @Override
    public String toString() {

        return this.id.toString()+"::"+this.age.toString()+"::"+this.firstName.toString()+"::"+this.lastName.toString(); //To change body of generated methods, choose Tools | Templates.
    }






}
