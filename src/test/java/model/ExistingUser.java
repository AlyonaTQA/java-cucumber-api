package model;

public class ExistingUser {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private String status;

    public Long getId() {
        return id;
    }

    public ExistingUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ExistingUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ExistingUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public ExistingUser setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ExistingUser setStatus(String status) {
        this.status = status;
        return this;
    }
}
