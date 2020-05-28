package entities;

import java.util.Date;

public class Order extends Entity {

    private int orderID;
    private float price;
    private Date orderCreateDate;
    private Date orderCheckedDate;
    private boolean isCanceled;
    private int librarianID;
    private String bookName;
    private String authorName;
    private int quantity;

    public Order(){}

    public Order(float price, Date orderCreateDate, Date orderCheckedDate, boolean isCanceled, int librarianID, String bookName, String authorName, int quantity) {
        this.orderID = Entity.id;
        this.price = price;
        this.orderCreateDate = orderCreateDate;
        this.orderCheckedDate = orderCheckedDate;
        this.isCanceled = isCanceled;
        this.librarianID = librarianID;
        this.bookName = bookName;
        this.authorName = authorName;
        this.quantity = quantity;
        Entity.id++;
    }

    public Order(int orderID, float price, Date orderCreateDate, Date orderCheckedDate, boolean isCanceled, int librarianID, String bookName, String authorName, int quantity) {
        this.orderID = orderID;
        this.price = price;
        this.orderCreateDate = orderCreateDate;
        this.orderCheckedDate = orderCheckedDate;
        this.isCanceled = isCanceled;
        this.librarianID = librarianID;
        this.bookName = bookName;
        this.authorName = authorName;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", price=" + price +
                ", orderCreateDate=" + orderCreateDate +
                ", orderCheckedDate=" + orderCheckedDate +
                ", isCanceled=" + isCanceled +
                ", librarianID=" + librarianID +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getOrderCreateDate() {
        return orderCreateDate;
    }

    public void setOrderCreateDate(Date orderCreateDate) {
        this.orderCreateDate = orderCreateDate;
    }

    public Date getOrderCheckedDate() {
        return orderCheckedDate;
    }

    public void setOrderCheckedDate(Date orderCheckedDate) {
        this.orderCheckedDate = orderCheckedDate;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public int getLibrarianID() {
        return librarianID;
    }

    public void setLibrarianID(int librarianID) {
        this.librarianID = librarianID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
