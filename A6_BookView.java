// ===== VIEW =====

import java.util.List;

public class A6_BookView {

    public void displayBook(A6_Book book) {
        System.out.println("--- Book Details ---");
        System.out.println("ISBN   : " + book.getIsbn());
        System.out.println("Title  : " + book.getTitle());
        System.out.println("Author : " + book.getAuthor());
        System.out.printf("Price  : ₹%.2f%n", book.getPrice());
        System.out.println("--------------------");
    }

    public void displayAllBooks(List<A6_Book> books) {
        System.out.println("======= Bookstore Catalog =======");
        if (books.isEmpty()) {
            System.out.println("  (No books available)");
        } else {
            for (A6_Book b : books) {
                System.out.println("  " + b);
            }
        }
        System.out.println("=================================");
    }

    public void showMessage(String message) {
        System.out.println("[INFO] " + message);
    }
}
