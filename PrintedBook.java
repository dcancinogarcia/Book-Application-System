import java.util.ArrayList;
import java.util.List;

class PrintedBook extends Book 
{
    //Encapsulation: Fields are private, accessed by methods
    //Inheritance: Inherits from Book
    //Polymorphism: Overrides getCost, toString, toFileString
    private int totalPages;
    private static List<PrintedBook> printedBooks = new ArrayList<>();
    //for average calculation
    private static int totalPagesAll = 0;

    public PrintedBook(String title, String author, String genre, double cost, int totalPages)
    {
        super(title, author, genre, cost);
        this.totalPages = totalPages;
        printedBooks.add(this);
        totalPagesAll += totalPages; //Upgrade total pages
    }

    @Override
    public double getCost() 
    {
        return totalPages * 10.0; // Cost calculation based on pages
    }

    public static double computeAveragePages() 
    {
        if (printedBooks.isEmpty()) return 0.0;
        return (double) totalPagesAll / printedBooks.size();
    }

    public static void displayLastThreePrintedBooks() 
    {
        System.out.println("=== LAST THREE PRINTED BOOKS ===");
        int start = Math.max(0, printedBooks.size() - 3);
        for (int i = start; i < printedBooks.size(); i++)
        {
            System.out.println(printedBooks.get(i).toString());
        }
    }

    //update counter
    public static void updateTotalPages(PrintedBook book, boolean remove) 
    {
        if (remove) 
        {
            totalPagesAll -= book.totalPages;
        }
        else 
        {
            totalPagesAll += book.totalPages;
        }
    }

    //clear list use the file
    public static void clearPrintedBooks() 
    {
        printedBooks.clear();
        totalPagesAll = 0;
    }

    //getter for list
    public static List<PrintedBook> getPrintedBooksList()
    {
        return printedBooks;
    }

    public int getTotalPages() 
    {
        return totalPages;
    }

    @Override
    public String toString() 
    {
        return super.toString() + ", Total Pages: " + totalPages + ", Calculated Cost: " + getCost();
    }

    @Override
    protected String toFileString() 
    {
        return "PrintedBook," + title + "," + author + "," + genre + "," + getBaseCost() + "," + totalPages;
    }
}