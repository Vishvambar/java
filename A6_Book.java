// ===== MODEL =====

public class A6_Book {

    private String isbn;
    private String title;
    private String author;
    private double price;

    public A6_Book(String isbn, String title, String author, double price) {
        this.isbn   = isbn;
        this.title  = title;
        this.author = author;
        this.price  = price;
    }

    // Getters
    public String getIsbn()   { return isbn; }
    public String getTitle()  { return title; }
    public String getAuthor() { return author; }
    public double getPrice()  { return price; }

    // Setters
    public void setTitle(String title)   { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPrice(double price)   { this.price = price; }

    @Override
    public String toString() {
        return String.format("[%s] \"%s\" by %s — ₹%.2f", isbn, title, author, price);
    }
}
