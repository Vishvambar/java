/*
 * ============================================================
 *  ECLIPSE SETUP:
 *  1. Create a Standard Java Project
 *  2. Paste ALL THREE files (A6_Book, A6_BookView, A6_BookController)
 *     into the src/ folder of the SAME project
 *  3. Run A6_BookController.java → Run As → Java Application
 *  (No external JARs needed)
 * ============================================================
 */

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
