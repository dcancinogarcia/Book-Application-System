import java.util.Scanner;

public class Test
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        try 
        {
            while (true)
            {
                System.out.println("===========================================");
                System.out.println("Book Application Menu:");
                System.out.println("1.- Add Printed Book");
                System.out.println("2.- Add Audio Book");
                System.out.println("3.- Display Last Six Books");
                System.out.println("4.- Get Number of Books by Genre");
                System.out.println("5.- Get Total Cost of ALL Books");
                System.out.println("6.- Display Last Three Printed Books");
                System.out.println("7.- Display Last Three Audio Books");
                System.out.println("8.- Compute Average Pages of Printed Books");
                System.out.println("9.- Compute Average Length of Audio Books");
                System.out.println("10.- Save Books to File");
                System.out.println("11.- Load Books from File");
                System.out.println("12.- Delete a Book");
                System.out.println("13.- Display All Books");
                System.out.println("14.- Exit");
                System.out.println("===========================================");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) 
                {
                    case 1:
                        System.out.print("Insert title: ");
                        String title = scanner.nextLine();
                        System.out.print("Insert author: ");
                        String author = scanner.nextLine();
                        System.out.print("Insert genre: ");
                        String genre = scanner.nextLine();
                        System.out.print("Insert cost: ");
                        double cost = scanner.nextDouble();
                        System.out.print("Insert Pages: ");
                        int totalPages = scanner.nextInt();
                        scanner.nextLine();
                        new PrintedBook(title, author, genre, cost, totalPages);
                        System.out.println("Printed Book added successfully!");
                        break;
                    
                    case 2:
                        System.out.print("Insert title: ");
                        title = scanner.nextLine();
                        System.out.print("Insert author: ");
                        author = scanner.nextLine();
                        System.out.print("Insert genre: ");
                        genre = scanner.nextLine();
                        System.out.print("Insert cost: ");
                        cost = scanner.nextDouble();
                        System.out.print("Insert the Minutes: ");
                        int totalMinutes = scanner.nextInt();
                        scanner.nextLine();
                        new AudioBook(title, author, genre, cost, totalMinutes);
                        System.out.println("Audio Book added successfully!");
                        break;
                        
                    case 3:
                        if (!Book.books.isEmpty())
                        {
                            Book tempBook = Book.books.get(0);
                            tempBook.displayLastSix();
                        } 
                        else 
                        {
                            System.out.println("No books available.");
                        }
                        break;

                    case 4:
                        System.out.print("Insert genre: ");
                        String genreInput = scanner.nextLine();
                        System.out.println("Number of books in genre " + genreInput + ": " + Book.getNumberofBooksByGenreStatic(genreInput));
                        break;

                    case 5:
                        System.out.println("Total cost of ALL Books: " + Book.getTotalCostStatic());
                        break;

                    case 6:
                        PrintedBook.displayLastThreePrintedBooks();
                        break;

                    case 7:
                        AudioBook.displayLastThreeAudioBooks();
                        break;

                    case 8:
                        System.out.println("Average pages of printed books: " + PrintedBook.computeAveragePages());
                        break;

                    case 9:
                        System.out.println("Average length of audio books: " + AudioBook.computeAverageLength());
                        break;

                    case 10:
                        System.out.print("Insert file path: ");
                        String filePath = scanner.nextLine();
                        Book.writeToFile(filePath);
                        break;

                    case 11:
                        System.out.print("Insert file path: ");
                        filePath = scanner.nextLine();
                        Book.readFromFile(filePath);
                        break;

                    case 12:
                        deleteBookMenu(scanner);
                        break;

                    case 13:
                        Book.displayAllBooks();
                        break;

                    case 14:
                        System.out.println("Exiting application....");
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } 
        finally 
        {
            scanner.close();
        }
    }

    public static void deleteBookMenu(Scanner scanner) 
    {
        System.out.println("=== DELETE BOOK ===");
        System.out.println("1.- Delete by index");
        System.out.println("2.- Delete by title");
        System.out.print("Choose an option: ");
        int method = scanner.nextInt();
        scanner.nextLine();

        switch (method) 
        {
            case 1:
                Book.displayAllBooks();
                System.out.print("Insert the index of the book to delete: ");
                int index = scanner.nextInt();
                scanner.nextLine();
                if(Book.removeBook(index)) 
                {
                    System.out.println("Book deleted successfully!");
                } 
                else
                {
                    System.out.println("Invalid index. No book deleted.");
                }
                break;
        
            case 2:
                System.out.print("Insert the title of the book to delete: ");
                String title = scanner.nextLine();
                if(Book.removeBookByTitle(title)) 
                {
                    System.out.println("Book deleted successfully!");
                } 
                else
                {
                    System.out.println("Book not found. No book deleted.");
                }
                break;

            default:
                System.out.println("Invalid option.");
                break;
        }
    }
}