// name: Sravya Gogineni
// date: 3/6/25

import java.io.*;
import java.util.*;

public class AuthorsNovelsMap
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim()+".txt";
      Scanner inputFile = new Scanner(new File(inFileName));
      //System.out.print("\nEnter output file name: ");
      //String outFileName = keyboard.nextLine().trim();
   
      AuthorsMap authors = readAndMakeTheList(inputFile);
      PrintWriter outputFile = new PrintWriter(new FileWriter("authorsNovelsOut.txt"));
      outputFile.println( authors.toString() );
      inputFile.close(); 						
      outputFile.close();
      System.out.println("File created.");
   }
   
   public static AuthorsMap readAndMakeTheList(Scanner inputFile)
   {
      AuthorsMap theAuthors = new AuthorsMap();
      while(inputFile.hasNextLine())
      {
         theAuthors.readOneLine(inputFile.nextLine());
      }
      return theAuthors;
   }
}

class AuthorsMap extends TreeMap<String, Set<String>>
{
  /**   when you extend a class, the constructor is optional  **/
 
   /** extracts the author and book from oneLine.
       calls addAuthorOrNovel      
      */
   public void readOneLine(String oneLine) 
   { 
      String[] line = oneLine.split(",");
      addAuthorOrNovel(line[0].trim(), line[1].trim());
   }
   
   /**  either inserts a new Author mapping, or updates a previous Author mapping
        */
   public void addAuthorOrNovel(String name, String book)
   {
      name = name.toUpperCase();
      if(containsKey(name))
            get(name).add(book);
      else {
         Set<String> books = new HashSet<>();
         books.add(book);
         put(name,books);
      }     
   }
   
   public String toString()
   {
      String toRet = "";
      for(String author:keySet()){
         toRet += author + ": ";
         toRet += String.join(", ",get(author));
         toRet+= "\n"; //new Line
      }
      return toRet;
   }
}
   

/**********************  SAMPLE RUN  ********************************
   /******** Output file for infile2:
   
   DOSTOEVSKI: Crime and Punishment, The Possessed, The Brothers Karamazov, The Grand Inquisitor
   FLAUBERT: Madame Bovary, A Simple Heart, Memoirs of a Madman, Sentimental Education
   STENDHAL: The Red and the Black
   TOLSTOY: Anna Karenina, War and Peace, The Death of Ivan Illyich, The Kreutzer Sonata
   
**********************************/