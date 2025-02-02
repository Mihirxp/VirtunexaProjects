import java.util.*;

class Book {
    String title;
    boolean isAvailable;

    Book(String title) {
        this.title = title;
        this.isAvailable = true;
    }
}

public class LibrarySystem {
    static List<Book> books = new ArrayList<>();
    static List<Book> borrowedBooks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Library System");
            System.out.println("1. Add Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. View Available Books");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    viewAvailableBooks();
                    break;
                case 5:
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        books.add(new Book(title));
        System.out.println("Book added.");
    }

    public static void borrowBook() {
        System.out.print("Enter book title to borrow: ");
        String title = scanner.nextLine();
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title) && book.isAvailable) {
                book.isAvailable = false;
                borrowedBooks.add(book);
                System.out.println("Book borrowed.");
                return;
            }
        }
        System.out.println("Book is either not available or doesn't exist.");
    }

    public static void returnBook() {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();
        for (Book book : borrowedBooks) {
            if (book.title.equalsIgnoreCase(title)) {
                book.isAvailable = true;
                borrowedBooks.remove(book);
                System.out.println("Book returned.");
                return;
            }
        }
        System.out.println("This book was not borrowed.");
    }

    public static void viewAvailableBooks() {
        System.out.println("Available books:");
        for (Book book : books) {
            if (book.isAvailable) {
                System.out.println(book.title);
            }
        }
    }
}
