public class TestCases 
{
    public static void main(String[] args) 
    {
        // Clear any existing books
        Book.books.clear();
        PrintedBook.clearPrintedBooks();
        AudioBook.clearAudioBooks();

        // Test cases for Book methods
        System.out.println("Testing Book methods:");
        new PrintedBook("Test Book", "Author", "Fiction", 100.0, 100);
        new AudioBook("Test Audio", "Author2", "Non-Fiction", 250.0, 50);
        
        System.out.println("Total Cost: " + Book.getTotalCostStatic());
        System.out.println("Books in Fiction: " + Book.getNumberofBooksByGenreStatic("Fiction"));
        
        if (!Book.books.isEmpty()) 
        {
            Book.books.get(0).displayLastSix();
        }

        // Test cases for PrintedBook
        System.out.println("\nTesting PrintedBook:");
        new PrintedBook("Book2", "Author3", "Fiction", 200.0, 200);
        System.out.println("Average Pages: " + PrintedBook.computeAveragePages());
        PrintedBook.displayLastThreePrintedBooks();

        // Test cases for AudioBook
        System.out.println("\nTesting AudioBook:");
        new AudioBook("Audio2", "Author4", "Non-Fiction", 500.0, 100);
        System.out.println("Average Length: " + AudioBook.computeAverageLength());
        AudioBook.displayLastThreeAudioBooks();

        // Test file I/O
        System.out.println("\nTesting File I/O:");
        Book.writeToFile("test_books.txt");
        Book.readFromFile("test_books.txt");
        System.out.println("Books reloaded from file.");
        
        // Verify after reload
        System.out.println("Total Cost after reload: " + Book.getTotalCostStatic());
        System.out.println("Average Pages after reload: " + PrintedBook.computeAveragePages());
        System.out.println("Average Length after reload: " + AudioBook.computeAverageLength());
    }
}