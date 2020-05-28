package entities;

public class BookStock extends Entity {

    private int id;
    private String bookName;
    private String authorName;
    private int quantity;
    private String barcode;
    private int category;
    private String bookCode;

    public BookStock(){}

    @Override
    public String toString() {
        return "BookStock{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", quantity=" + quantity +
                ", barcode='" + barcode + '\'' +
                ", category=" + category +
                ", bookCode='" + bookCode + '\'' +
                '}';
    }

    public BookStock(String bookName, String authorName, int quantity, String barcode, int category, String bookCode) {
        this.id = Entity.id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.quantity = quantity;
        this.barcode = barcode;
        this.category = category;
        this.bookCode = bookCode;
        Entity.id++;
    }

    public BookStock(int id, String bookName, String authorName, int quantity, String barcode, int category, String bookCode) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.quantity = quantity;
        this.barcode = barcode;
        this.category = category;
        this.bookCode = bookCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }
}
