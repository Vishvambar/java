// ===== CONTROLLER =====

import java.util.ArrayList;
import java.util.List;

public class A6_BookController {

    private List<A6_Book> books;
    private A6_BookView   view;

    public A6_BookController(A6_BookView view) {
        this.view  = view;
        this.books = new ArrayList<>();
    }

    public void addBook(String isbn, String title, String author, double price) {
        A6_Book book = new A6_Book(isbn, title, author, price);
        books.add(book);
        view.showMessage("Book added: " + book.getTitle());
    }

    public void displayAllBooks() {
        view.displayAllBooks(books);
    }

    public void displayBookByIsbn(String isbn) {
        for (A6_Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                view.displayBook(b);
                return;
            }
        }
        view.showMessage("Book with ISBN " + isbn + " not found.");
    }

    public void updatePrice(String isbn, double newPrice) {
        for (A6_Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                b.setPrice(newPrice);
                view.showMessage("Price updated for: " + b.getTitle());
                return;
            }
        }
        view.showMessage("Book with ISBN " + isbn + " not found.");
    }

    // --- Demo Entry Point ---
    public static void main(String[] args) {

        A6_BookView       view       = new A6_BookView();
        A6_BookController controller = new A6_BookController(view);

        // Add books
        controller.addBook("978-0-13-468599-1", "Effective Java",     "Joshua Bloch",   599.00);
        controller.addBook("978-0-596-00712-6", "Head First Java",    "Kathy Sierra",   499.00);
        controller.addBook("978-0-13-235088-4", "Clean Code",         "Robert C. Martin", 450.00);

        // Display all
        controller.displayAllBooks();

        // Lookup
        controller.displayBookByIsbn("978-0-596-00712-6");

        // Update price
        controller.updatePrice("978-0-13-468599-1", 649.00);
        controller.displayBookByIsbn("978-0-13-468599-1");
    }
}
