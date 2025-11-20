import java.util.List;

public interface BookInterface 
{
    //Abstraction: The interface define abstract methods and a default method
    //Polymorphism: Methods can be overridden in implementing class
    default void displayLastSix(List<Book> books)
    {
        System.out.println("=== LAST SIX BOOKS ===");
        int start = Math.max(0, books.size() - 6);
        for (int i = start; i < books.size(); i++) 
        {
            System.out.println(books.get(i).toString());
        }
    }
    int getNumberofBooksByGenre(String genre);
    double getTotalCost();
}