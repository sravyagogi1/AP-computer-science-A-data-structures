//uses Palindrome

public class Palindrome_Driver
{
   public static void main(String[] args)
   {
      System.out.println("PALINDROME TESTER");
      Palindrome s = null;
      
      s = new Palindrome( "\"Hello there!\" she said." );
      System.out.println( s.getSentence() );
      System.out.println( "Remove blanks:       " +s.removeBlanks() );
      System.out.println( "in lower case:       " +s.lowerCase() );
      System.out.println( "Remove punctuation:  " +s.removePunctuation() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
      s = new Palindrome( "A Santa lived      as a devil at NASA.   " );
      System.out.println( s.getSentence() );
      System.out.println( "Remove blanks:       " +s.removeBlanks() );
      System.out.println( "in lower case:       " +s.lowerCase() );
      System.out.println( "Remove punctuation:  " +s.removePunctuation() );
      System.out.println( s.isPalindrome() );
      System.out.println();
     
   
      s = new Palindrome( "Flo, gin is a sin! I golf." );
      System.out.println( s.getSentence() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
   
      s = new Palindrome( "Eva, can I stab bats in a cave?" );
      System.out.println( s.getSentence() );
      System.out.println( s.isPalindrome() );
      System.out.println();
   
      s = new Palindrome( "Madam, I'm Adam." );
      System.out.println( s.getSentence() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
   // Lots more test cases.  Test every line of code.  Test
   // the extremes, test the boundaries.  How many test cases do you need?
      s = new Palindrome( "123 321" );
      System.out.println( s.getSentence() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
      s = new Palindrome( " " );  // is 1 space a palindrome?
      System.out.println( s.getSentence() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
      s = new Palindrome( "a b c x b a" );
      System.out.println( s.getSentence() );
      System.out.println( s.isPalindrome() );
      System.out.println();
     
      s = new Palindrome( "!a ?" );
      System.out.println( s.getSentence() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
   }
}


 /*****************************************
   
    PALINDROME TESTER
 "Hello there!" she said.
 Remove blanks:       "Hellothere!"shesaid.
 in lower case:       "hello there!" she said.
 Remove punctuation:  Hello there she said
 cleaned up sentence: hellothereshesaid
 false
 
 A Santa lived      as a devil at NASA.
 Remove blanks:       ASantalivedasadevilatNASA.
 in lower case:       a santa lived      as a devil at nasa.
 Remove punctuation:  A Santa lived      as a devil at NASA
 cleaned up sentence: asantalivedasadevilatnasa
 true
 
 Flo, gin is a sin! I golf.
 cleaned up sentence: floginisasinigolf
 true
 
 Eva, can I stab bats in a cave?
 cleaned up sentence: evacanistabbatsinacave
 true
 
 Madam, I'm Adam.
 cleaned up sentence: madamimadam
 true
 
 123 321
 cleaned up sentence: 123321
 true
 
 
 cleaned up sentence: 
 true
 
 a b c x b a
 cleaned up sentence: abcxba
 false
 
 !a ?
 cleaned up sentence: a
 true    
   **********************************************/
