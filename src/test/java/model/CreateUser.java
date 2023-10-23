package model;

import org.apache.commons.lang3.RandomStringUtils;

public class CreateUser {
    private String name;
    private String email;
    private String gender;
    private String status;

    public String getName() {
        return name;
    }

    public CreateUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateUser setEmail() {
        this.email = RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomAlphabetic(5) + ".com";
        return this;
    }

    public String getGender() {
        return gender;
    }

    public CreateUser setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public CreateUser setStatus(String status) {
        this.status = status;
        return this;
    }
}
