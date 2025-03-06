package com.bridgelabz.addressbookapp.dto;

public class ContactDTO {
    private String name;
    private String email;
    private String phone;

    public ContactDTO() {}

    public ContactDTO(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getter and Setter
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setName(String name) {
        this.name = name;
    }
}
