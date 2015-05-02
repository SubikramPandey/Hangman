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
    		guess = readLine( 
    				"The word now looks like this: " + knownWord 
    				+ "\n Please guess a letter here -> ").toUpperCase();    		
    		/*
    		 * can change and change bool if there is a change
    		 * return false otherwise
    		 */
    		// if guess is in the secret word
    		if (secretWord.contains(guess)) {
    			correctGuess();
    		}
    		else {
    			wrongGuess();
    		}
    	}
    }
    
    private void correctGuess() {
    	updateKnownWord();
		println("Awesome " + guess + " is in the word");
		println("Here is what the word looks like now: " + knownWord);
    }
    
    
    /*
     * goes though each letter of the secretWord
     * 
     */
    private void updateKnownWord() {
    	println("\n the word is " + secretWord);    	
    	// go from last letter to end looking for next letter
    	// call replace if found one
    	// end when at last letter
    	
		int letterLocation = 1;
		// char replacement if it is first letter in the word
		replaceFirstLetterInKnownWord();
		while (letterLocation < (knownWord.length() - 1)) {
			replaceLetterInKnownWord(letterLocation);
			letterLocation = getNextLetterLocation(letterLocation);
		}
		println("out of update loop");
    }
    
    private void replaceFirstLetterInKnownWord() {
    	if (getNextLetterLocation(0) == 1){
			replaceLetterInKnownWord(0);
		}
    }
    
    
    // starting with lastLetterLocation + 1, searches for letter
    private int getNextLetterLocation(int lastLetterLocation) {
    	int currentLetterLocation = 
    			secretWord.indexOf(guess, lastLetterLocation);
    	// Signaling when the search has reached the end of the word
    	if(currentLetterLocation == -1) {
    		return (currentLetterLocation = knownWord.length());
    	}
    	else {
    		return(currentLetterLocation + 1);
    	}
    }
    
    // why would letter location be 0 on first run
    private void replaceLetterInKnownWord(int letterLocation) {
    	String start;
    	if (letterLocation == 0) {
    		start = "";
    	}
    	else {
    		start = knownWord.substring(0, letterLocation -1);
    	}
    	String end = knownWord.substring(letterLocation);
    	println("start: " + start + " guess: " + guess + " end: " + end );
    	knownWord = start + guess + end;
    	
    	println("knownWord in replace: " + knownWord);
    	println("letterLocation: " + letterLocation);
    	println("end of replace \n");
    	
    }
    
    private void wrongGuess() {
    	guessesLeft -= 1;
    	println(guess + " is not in the secret word"
    			+ "\n You have " + guessesLeft + " guesses left!");
    }
    
    /** Instance Variables */
    String guess;
    String secretWord;
    String lettersInSecretString = ""; // used to spead search, list unique char
    String knownWord = "";
    String wrongGuesses = "";
    int guessesLeft;
    HangmanLexicon lex = new HangmanLexicon();
    RandomGenerator rgen = new RandomGenerator();	

}
