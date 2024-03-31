package entities;

public class EmployeeAddress {
    private String address;
    private String houseName;
    private String state;
    private String city;
    private Integer pinCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public EmployeeAddress(String address, String houseName, String state, String city, Integer pinCode) {
        this.address = address;
        this.houseName = houseName;
        this.state = state;
        this.city = city;
        this.pinCode = pinCode;
    }

    public EmployeeAddress() {
    }
}

