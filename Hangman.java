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
     * checks if it matches the guess
     * if it does replaces dash in that location with the letter in knownWord
     */
    private void updateKnownWord(String guess) {
    	// go though all the letters in the secret word
    	for (int i = 0; i < secretWord.length(); i++) {
    		// gives letter index
    		int guessIndex = secretWord.indexOf(guess, i);
    		
    		
//    		println("SecetWord: " + secretWord + "\n");
//    		println("i testing " + i + " letter" + secretWord.substring(0, i));
//    		
//    		// printing testing letter
//    		println("letter is: " + secretWord.charAt(i) 
//    				+ " L shoud be" + (secretWord.substring(i, i) + "\n"));
//    		// guess
//    		println("guess is: " + guess.toUpperCase().charAt(0) + " G should be "
//    				+ (guess) + " guess \n");
//    		
//    		// print result of if statement
//    		println(secretWord.substring(i, i).equals(guess.charAt(0)));
//    		
//    		if (secretWord.substring(i, i).equals(guess.substring(0))) {
//    			String temp = knownWord.substring(0, (i - 1)) + "guess"
//    					+ knownWord.substring(i + 1);
//    			knownWord = temp;
//    			println("replaced string " + knownWord);
//    		}
    	}
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
