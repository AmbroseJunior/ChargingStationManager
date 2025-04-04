package vao;

import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;
    private double accountBalance;
    private String carType;

    public User(UUID id, String name, String email, double accountBalance, String carType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.accountBalance = accountBalance;
        this.carType = carType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", Account Balance=" + accountBalance +
                ", carType='" + carType + '\'' +
                '}';
    }
}
