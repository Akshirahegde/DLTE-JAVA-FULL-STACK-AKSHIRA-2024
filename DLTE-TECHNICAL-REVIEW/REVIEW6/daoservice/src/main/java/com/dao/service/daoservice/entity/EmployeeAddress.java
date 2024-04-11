package com.dao.service.daoservice.entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmployeeAddress {
    @NotNull(message = "{address.null}")
    @Pattern(regexp = "^\\d+\\s[a-zA-Z]+\\s[a-zA-Z]+", message = "{customer.street}")
    private String street;

    @NotNull(message = "{address.houseNumber.null}")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{address.houseName.invalid}")
    private String houseName;

    @NotNull(message = "{address.state.null}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{address.state.invalid}")
    private String state;

    @NotNull(message = "{address.city.null}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{address.city.invalid}")
    private String city;

    @NotNull(message = "{address.pinCode.null}")
    @Digits(integer = 6, fraction = 0, message = "{address.pinCode.invalid}")
    private Integer pin;

    public EmployeeAddress(@NotNull(message = "{street.null}") @Pattern(regexp = "^\\d+\\s[a-zA-Z]+\\s[a-zA-Z]+", message = "{customer.street}") String street, @NotNull(message = "{address.houseName.null}") @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{address.houseName.invalid}") String houseName, @NotNull(message = "{address.state.null}") @Pattern(regexp = "^[a-zA-Z ]+$", message = "{address.state.invalid}") String state, @NotNull(message = "{address.city.null}") @Pattern(regexp = "^[a-zA-Z ]+$", message = "{address.city.invalid}") String city, @NotNull(message = "{address.pin.null}") @Digits(integer = 6, fraction = 0, message = "{address.pin.invalid}") Integer pin) {
        this.street = street;
        this.houseName = houseName;
        this.state = state;
        this.city = city;
        this.pin = pin;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public EmployeeAddress() {
    }
}

