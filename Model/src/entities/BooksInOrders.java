package entities;

public class BooksInOrders extends Entity {
    private int bookInOrderID;
    private int orderID;
    private String bookName;
    private int category;

    public BooksInOrders(){}

    public BooksInOrders(int orderID, String bookName, int category)
    {
        this.bookInOrderID = Entity.id;
        this.orderID = orderID;
        this.bookName = bookName;
        this.category = category;
        Entity.id ++;
    }

    public BooksInOrders(int bookInOrderID, int orderID, String bookName, int category) {
        this.bookInOrderID = bookInOrderID;
        this.orderID = orderID;
        this.bookName = bookName;
        this.category = category;
    }

    @Override
    public String toString() {
        return "BooksInOrders{" +
                "BookInOrderID=" + bookInOrderID +
                ", orderID=" + orderID +
                ", bookName='" + bookName + '\'' +
                ", category=" + category +
                '}';
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getBookInOrderID() {
        return bookInOrderID;
    }

    public void setBookInOrderID(int bookInOrderID) {
        bookInOrderID = bookInOrderID;
    }
}
