//  Name: Sravya Gogineni     Date: 1/14/25

import java.io.*;
import java.util.*;

public class AuthorsNovels
{
   public static void main(String[] args) throws IOException
   {
      /*   test the AuthorEntry object  */
      AuthorEntry a = new AuthorEntry("Aaa");
      System.out.println("name: " + a.getName());
      System.out.println("novels: " + a.getNovels());
      System.out.println("toString(): " + a);
      AuthorEntry b = new AuthorEntry("bbb", "y");
      System.out.println("name: " + b.getName());
      b.addNovel("z");
      b.addNovel("y");
      b.addNovel("x");
      System.out.println("novels: " + b.getNovels());
      System.out.println("toString(): " + b);
      System.out.println(b.compareTo(a));   // 1
      System.out.println(a.compareTo(b));   // -1
      System.out.println("AAA".compareTo(a.getName())); // 0
      
      
      /*  start the lab  */
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim()+".txt";
      Scanner inputFile = new Scanner(new File(inFileName));
      //System.out.print("\nEnter output file name: ");
      //String outFileName = keyboard.nextLine().trim();
      AuthorList authors = readAndMakeTheList(inputFile);
      String outFileName = "authorsNovelsOut.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
      outputFile.println( authors.toString() );
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
   
   public static AuthorList readAndMakeTheList(Scanner inputFile)
   {
      AuthorList theList = new AuthorList();
      while(inputFile.hasNextLine())
      {
         theList.readOneLine(inputFile.nextLine());
      }
      return theList;
   }
}

class AuthorList extends ArrayList<AuthorEntry>
{
    /**   you get an ArrayList for free   **/
   public AuthorList()
   {
      super();
   }
     /** extracts the author and book from oneLine.
         calls addAuthorEntry      
      */
   public void readOneLine(String oneLine) 
   {  
      String[] array = oneLine.split(", ");
      String author = array[0];
      String book = array[1];
      
      addAuthorEntry(author,book);
   }
   
    /** use a listIterator.  Needs to call .previous() 
          either inserts a new AuthorEntry object, or 
          adds a novel to a previous AuthorEntry object,
          in alphabetic order
    */
   public void addAuthorEntry(String name, String book)
   {
      String UpName = name.toUpperCase();
      
      ListIterator<AuthorEntry> iterator = listIterator();
      while(iterator.hasNext()){
         AuthorEntry newEntry = iterator.next();
         if(newEntry.getName().equals(UpName)){
            newEntry.addNovel(book);
            return; //for void compile
         }
         if(newEntry.getName().compareTo(UpName)>0){
            iterator.previous();
            iterator.add(new AuthorEntry(UpName,book));
            return; //for void compile
         }
      }
      add(new AuthorEntry(UpName,book));
   }
   
   public String toString()
   {  
      String str = "";
      int count = 0;
      while(size()>count){
         str += get(count).toString();
         count++;
      }
      return str.trim();
   }
}

class AuthorEntry implements Comparable<AuthorEntry>
{
   //fields
   private String name;
   private ArrayList<String> novels;
   
   //two constructors. argument n may be in lowercase. 
   public AuthorEntry(String n)
   {
      name = n.toUpperCase();
      novels = new ArrayList<String>();
   }
   public AuthorEntry(String n, String book)
   {
      name = n.toUpperCase();
      novels = new ArrayList<String>();
      addNovel(book);
   }
   
   /**  appends book to novels, but only if it is not already in that list.    
       */
   public void addNovel(String book)
   {
      if(!novels.contains(book))
         novels.add(0,book);
   }
   
   /** two standard accessor methods  */
   
   public String getName(){
      return name;
   }
   
   public LinkedList<String> getNovels(){
      return new LinkedList<>(novels);
   }
        
   /**  pre:  name is not an empty string.  novels might be an empty ArrayList.
       uses:  either a for-each loop or an iterator
       post:  returns a string representation of this AuthorEntry in the format as 
              shown on each line of the output file.  
     */
   public String toString()
   {
      String str = name + ": ";
      str += novels.toString() + "\n";
      return str;
   }
   
   public int compareTo(AuthorEntry object){
      int result = name.compareTo(object.name);
      return result;
   }
}

/***************************************
     Extension    
     use this header to implement AuthorEntry a different way
***************************************************/
/*
   class AuthorEntryExt extends ArrayList<String> implements Comparable<AuthorEntryExt>
   {
      private String author;
      private ArrayList<String> novels;
   
      public void AuthorEntryExt(String a, String book){
         author = a;
         novels = new ArrayList<String>();
         novels.add(book);
      }
   
      public String getAuthor(){
         return author;
      }

      public ArrayList<String> getNovels(){
         return novels;
      }
      
      public void addNovel(String book){
         if(!novels.contains(book))
            novels.add(book);
      }
      
      public int compareTo(AuthorEntryExt object) {
         return author.compareTo(object.author);
      }
   
   }
   
*/

/**********************  SAMPLE RUN  ********************************
 name: AAA
 novels: []
 toString(): AAA
 name: BBB
 novels: [y, z, x]
 toString(): BBB: y, z, x
 1
 -1
 0
 
 Enter input file name: infile2
 Done.
 
 **********************************************************/
   /******** Output file for infile2:
   
   DOSTOEVSKI: Crime and Punishment, The Possessed, The Brothers Karamazov, The Grand Inquisitor
   FLAUBERT: Madame Bovary, A Simple Heart, Memoirs of a Madman, Sentimental Education
   STENDHAL: The Red and the Black
   TOLSTOY: Anna Karenina, War and Peace, The Death of Ivan Illyich, The Kreutzer Sonata
   
    */