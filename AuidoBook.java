import java.util.ArrayList;
import java.util.List;

class AudioBook extends Book 
{
    //Encapsulation: Fields are private and accessed via methods
    //Inheritance: Inherits from Book
    //Polymorphism: overrides getCost, toString, toFileString
    private int totalMinutes;
    private static List<AudioBook> audioBooks = new ArrayList<>();
    //static field to total length of audio books
    private static int totalMinutesAll = 0;

    public AudioBook(String title, String author, String genre, double cost, int totalMinutes) 
    {
        super(title, author, genre, cost);
        this.totalMinutes = totalMinutes;
        audioBooks.add(this);
        totalMinutesAll += totalMinutes;//update total minutes
    }

    @Override
    public double getCost() 
    {
        return totalMinutes * 5.0; // Cost calculation based on minutes
    }

    public static double computeAverageLength() 
    {
        if(audioBooks.isEmpty()) return 0.0;
        return (double) totalMinutesAll / audioBooks.size();
    }

    public static void displayLastThreeAudioBooks() 
    {
        System.out.println("=== LAST THREE AUDIO BOOKS ===");
        int start = Math.max(0, audioBooks.size() - 3);
        for(int i = start; i < audioBooks.size(); i++) 
        {
            System.out.println(audioBooks.get(i).toString());
        }
    }

    //update counter
    public static void updateTotalMinutes(AudioBook book, boolean remove) 
    {
        if(remove)
        {
            totalMinutesAll -= book.totalMinutes;
        } 
        else 
        {
            totalMinutesAll += book.totalMinutes;
        }
    }

    //clear list use the file
    public static void clearAudioBooks() 
    {
        audioBooks.clear();
        totalMinutesAll = 0;
    }

    //getter for list
    public static List<AudioBook> getAudioBooksList() 
    {
        return audioBooks;
    }

    public int getTotalMinutes() 
    {
        return totalMinutes;
    }

    @Override
    public String toString() 
    {
        return super.toString() + ", Total Minutes: " + totalMinutes + ", Calculated Cost: " + getCost();
    }

    @Override
    protected String toFileString() 
    {
        return "AudioBook," + title + "," + author + "," + genre + "," + getBaseCost() + "," + totalMinutes;
    }
}