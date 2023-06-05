package mvcBookStore;

public class Book {
    private int id;
    private String nameBook;
    private String nameAuthor;
    private double price;
    private int quantity;
    private String imageUrl;

    public Book(int id, String nameBook, String nameAuthor, double price, int quantity, String imageUrl) {
        super();
        this.id = id;
        this.nameBook = nameBook;
        this.nameAuthor = nameAuthor;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

