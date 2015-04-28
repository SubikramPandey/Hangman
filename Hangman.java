/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 * 
 * 
 * handles the user interaction component of the game
 * Ñeverything except the graphical display.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	
	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;
	
	
	
	// setup then starts a game
    public void run() {
    	setupGame();
    	playGame();
	}
    
    private void setupGame() {
    	setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
    	secretWord = getSecretWord();
    	getKnownWord();
    	guessesLeft = 8;
    	
    }
    
    // currently gets random word from the HangmanLexicon class
    private String getSecretWord() {
    	// get random word, assign it to variable, return that
    	int wordIndex = rgen.nextInt(lex.getWordCount());
    	String secretWord = lex.getWord(wordIndex);
    	return (secretWord);
    }
    
    private void getKnownWord() {
    	for (int i = 0; i < secretWord.length(); i++) {
    		knownWord += "-";
    	}
    }
    
    // while there are still free guesses
    // takes user guess
    // check if its in the secret word
    // updates knownWord and wrongGuesses
    private void playGame() {
    	println("Welcome to a round of Hangman");
    	while(guessesLeft > 0) {
    		String guess = readLine( 
    				"The word now looks like this: " + knownWord 
    				+ "\n Please guess a letter here -> ").toUpperCase();    		
    		/*
    		 * can change and change bool if there is a change
    		 * return false otherwise
    		 */
    		// if guess is in the secret word
    		if (secretWord.contains(guess)) {
    			correctGuess(guess);
    		}
    		else {
    			wrongGuess(guess);
    		}
    	}
    }
    
    private void correctGuess(String guess) {
    	updateKnownWord(guess);
		println("Awesome " + guess + " is in the word");
		println("Here is what the word looks like now: " + knownWord);
    }
    
    
    /*
     * goes though each letter of the secretWord
     * 
     */
    private void updateKnownWord(String guess) {
    	// go though all the letters in the secret word
    	println("\n the word is " + secretWord);
    	int i = 0;
    	// go though word while letter still in what is left of word 
    	while(i < secretWord.length() & secretWord.indexOf(guess, i) != -1) {	
    		int letterLocation = getLetterLocation();
    		replaceLetterInKnownWord();
    		
    		
    		
    		
    		// gives letter index
    		println("i in update loop " + i);
    		int guessIndex = secretWord.indexOf(guess, i);	
    		// issue: once past the letter needs to stop 
    		i = replaceChar(guessIndex, guess);
    		i += 1;
    		
    	}
    }
    
    
    
    
    
//    Checks if it matches the guess
//    if it does replaces dash in that location with the letter in knownWord
    /*
     * 
     */
    private int replaceChar(int guessIndex, String guess) {
    	int z;
    	if (guessIndex < 1) {
    		z = 0;
    	}
    	else {
    		z = guessIndex -1;
    	}
    	println("z in replace " + z);
    	String start = knownWord.substring(0, z);
    	String end = knownWord.substring(z + 1);
    	knownWord = start + guess + end;
    	println("knownWord is "+ knownWord);
    	return(z + 1);
    }
    
    private void wrongGuess(String guess) {
    	guessesLeft -= 1;
    	println(guess + " is not in the secret word"
    			+ "\n You have " + guessesLeft + " guesses left!");
    }
    
    /** Instance Variables */
    String secretWord;
    String lettersInSecretString = ""; // used to spead search, list unique char
    String knownWord = "";
    String wrongGuesses = "";
    int guessesLeft;
    HangmanLexicon lex = new HangmanLexicon();
    RandomGenerator rgen = new RandomGenerator();	

}
