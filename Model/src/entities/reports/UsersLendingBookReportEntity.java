package entities.reports;

import java.util.List;

public class UsersLendingBookReportEntity extends ReportEntity{
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private int gender;
    private String address;
    private String email;
    private String phone;
    private List<String> booksName;
    private int booksInLending;

    public UsersLendingBookReportEntity(String id, String userName, String firstName, String lastName, int gender, String address, String email, String phone, List<String> booksName) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.booksName = booksName;
        this.booksInLending = booksName.size();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getBooksName() {
        return booksName;
    }

    public void setBooksName(List<String> booksName) {
        this.booksName = booksName;
    }

    public int getBooksInLending() {
        return booksInLending;
    }

    public void setBooksInLending(int booksInLending) {
        this.booksInLending = booksInLending;
    }

    @Override
    public List<String> GetProperties() {
        return null;
    }
}
