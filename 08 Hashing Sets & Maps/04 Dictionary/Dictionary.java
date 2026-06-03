// Name: Sravya Gogineni
// Date: 3/11/25

import java.io.*;
import java.util.*;

public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      PrintWriter outputFile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         outputFile = new PrintWriter(new FileWriter("dictionaryOutput.txt"));
      }
      catch(Exception e)
      {
         System.out.println( e );
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      outputFile.println("ENGLISH TO SPANISH");
      outputFile.println(display(eng2spn));
   
      Map<String, Set<String>>spn2eng = reverse(eng2spn);
      outputFile.println("SPANISH TO ENGLISH");
      outputFile.println(display(spn2eng));
      outputFile.close();
      
      System.out.println("File created.");
   }
   
   // Creates a TreeMap (sorted map).
   // Iterates through the input file two words at a time:
   //   - The first word is treated as the English word.
   //   - The second word is treated as the Spanish translation.
   // Adds the pair to the dictionary by calling the add() method.

   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      Map<String,Set<String>> var = new TreeMap<>(); // Creates a sorted map
      while(infile.hasNext())
         add(var,infile.next(),infile.next()); // Reads pairs of words (Eng-Spn)
      return var;
   }
   
   // Adds a word and its translation to the dictionary.
   // If the word is not already in the dictionary:
   //   - Creates a new HashSet to store its translations.
   // Adds the translation to the Set.
   // Puts the updated pair into the dictionary.
   
   public static void add(Map<String, Set<String>> dictionary, String word, String translation)
   {
      Set temp = dictionary.get(word);  // Retrieves the set of translations
      if(temp==null)			         // If the word doesn't exist, create a new Set
         temp = new HashSet<String>();
      temp.add(translation);		   // Add the translation
      dictionary.put(word,temp);		// Put the word-translation pair into the map
   }
   
   // Iterates over the dictionary.
      // Appends each key-value pair to the rstring (result string).
      // Formats the output as: word [translation1, translation2, ...]

   
   public static String display(Map<String, Set<String>> m)
   {
      String rstring = "";
      for(Map.Entry<String,Set<String>> var:m.entrySet())
         rstring += var.getKey() + " " + var.getValue();
      return rstring;
   }
   
   // Iterates over the English to Spanish dictionary.
      // For each Spanish translation, calls add() with the parameters reversed:
      //   - Spanish word becomes the key.
      //   - English word becomes the value.
      // Builds and returns the reversed dictionary.

   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      Map<String,Set<String>> var = new TreeMap<>(); // Creates a new reversed map
      for(Map.Entry<String,Set<String>> var2:dictionary.entrySet()){
         for(String t:var2.getValue())    // Iterate over each translation
            add(var,t,var2.getKey());     // Swap key-value pairs
      }
      return var;
   }
}

   /********************
	FILE INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	FILE OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/