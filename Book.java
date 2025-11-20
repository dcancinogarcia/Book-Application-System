import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

abstract class Book implements BookInterface 
{
    //Encapsulation: fields are protected/private to accessed via method
    //Inheritance: This abstract class is inherited by PrintedBook and AudioBook
    //Abstraction: Abstract methods and class hide implementation details.
    protected String title;
    protected String author;
    protected String genre;
    protected double cost;
    protected static List<Book> books = new ArrayList<>();

    // Constructor with the information of the book as requirements
    public Book(String title, String author, String genre, double cost) 
    {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.cost = cost;
        books.add(this);
    }

    // Abstract method: Polymorphism overriden in subclasses to compute cost differently
    public abstract double getCost();

    @Override
    public double getTotalCost() 
    {
        //compute the cost of both Printed and Audio Books
        double totalCost = 0.0;
        for(Book book : books) 
        {
            totalCost += book.getCost();
        }
        return totalCost;
    }

    @Override
    public int getNumberofBooksByGenre(String genre) 
    {
        int count = 0;
        for(Book book : books) 
        {
            if(book.genre.equalsIgnoreCase(genre)) 
            {
                count++;
            }
        }
        return count;
    }

    public void displayLastSix() 
    {
        displayLastSix(books);
    }

    @Override
    public String toString() 
    {
        return "Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Cost: " + cost;
    }

    //Display all book with index
    public static void displayAllBooks() 
    {
        if(books.isEmpty()) 
        {
            System.out.println("No books available.");
            return;
        }
        System.out.println("=== ALL BOOKS ===");
        for (int i = 0; i < books.size(); i++) 
        {
            System.out.println("[" + i + "]" + books.get(i).toString());
        }
    }

    //delete book by index
    public static boolean removeBook(int index) 
    {
        if(index >= 0 && index < books.size())
        {
            Book bookToRemove = books.get(index);

            //delete all
            if (bookToRemove instanceof PrintedBook) 
            {
                PrintedBook.getPrintedBooksList().remove(bookToRemove);
                //upgrade counters
                PrintedBook.updateTotalPages((PrintedBook) bookToRemove, true);
            }
            else if(bookToRemove instanceof AudioBook)
            {
                AudioBook.getAudioBooksList().remove(bookToRemove);
                //upgrade minutes
                AudioBook.updateTotalMinutes((AudioBook) bookToRemove, true);
            }
            books.remove(index);
            return true;
        }
        return false;
    }

    //delete book by title
    public static boolean removeBookByTitle(String title) 
    {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext())
        {
            Book book = iterator.next();
            if(book.title.equalsIgnoreCase(title))
            {
                //delete all data
                if(book instanceof PrintedBook) 
                {
                    PrintedBook.getPrintedBooksList().remove(book);
                    PrintedBook.updateTotalPages((PrintedBook) book, true);
                } 
                else if(book instanceof AudioBook) 
                {
                    AudioBook.getAudioBooksList().remove(book);
                    AudioBook.updateTotalMinutes((AudioBook) book, true);
                }
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public static void writeToFile(String filePath) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) 
        {
            for (Book book : books) 
            {
                writer.write(book.toFileString());
                writer.newLine();
            }
            System.out.println("Books saved successfully to: " + filePath);
        } 
        catch (IOException e) 
        {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    public static void readFromFile(String filePath) 
    {
        books.clear();
        PrintedBook.clearPrintedBooks();
        AudioBook.clearAudioBooks();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) 
        {
            String line;
            int printedBooksLoaded = 0;
            int audioBooksLoaded = 0;

            while((line = reader.readLine()) != null) 
            {
                line = line.trim();
                if(line.isEmpty()) continue;
                
                String[] parts = line.split(",");
                if(parts.length >= 6)
                {
                    String type = parts[0].trim();
                    String title = parts[1].trim();
                    String author = parts[2].trim();
                    String genre = parts[3].trim();
                    
                    try
                    {
                        double cost = Double.parseDouble(parts[4].trim());
                        int extra = Integer.parseInt(parts[5].trim());

                        if(type.equalsIgnoreCase("PrintedBook")) 
                        {
                            new PrintedBook(title, author, genre, cost, extra);
                            printedBooksLoaded++;
                            System.out.println("Loaded Printed Book: " + title);
                        } 
                        else if(type.equalsIgnoreCase("AudioBook")) 
                        {
                            new AudioBook(title, author, genre, cost, extra);
                            audioBooksLoaded++;
                            System.out.println("Loaded Audio Book: " + title);
                        } 
                        else 
                        {
                            System.out.println("Unknown book type: " + type);
                        }
                    } 
                    catch (NumberFormatException e)
                    {
                        System.err.println("Error parsing numbers in line: " + line);
                    }
                } 
                else 
                {
                    System.out.println("Invalid line format (expected 6 parts, got " + parts.length + "): " + line);
                }
            }
            
            System.out.println("File loading completed:");
            System.out.println("  - Printed Books loaded: " + printedBooksLoaded);
            System.out.println("  - Audio Books loaded: " + audioBooksLoaded);
            System.out.println("  - Total books in system: " + books.size());
        } 
        catch (IOException e) 
        {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    //abstract method for file String representation polymorphism - overridden in subclasses
    protected abstract String toFileString();

    //static method to get total cost
    public static double getTotalCostStatic() 
    {
        double totalCost = 0.0;
        for(Book book : books)
        {
            totalCost += book.getCost();
        }
        return totalCost;
    }

    //static method to get number of books by genre 
    public static int getNumberofBooksByGenreStatic(String genre) 
    {
        int count = 0;
        for (Book book : books) 
            {
            if(book.genre.equalsIgnoreCase(genre)) 
            {
                count++;
            }
        }
        return count;
    }

    //getters for access
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public double getBaseCost() { return cost; }
}