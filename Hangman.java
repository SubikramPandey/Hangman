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
    // issue: reset gcanvas so restarts game properly
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
    	println("the word is: " + secretWord);
    	while(guessesLeft > 0 ) {
    		guess = getGuess();    		
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
    		if (secretWord.equalsIgnoreCase(knownWord) == true) {
    			break;
    		}
    	}
    	endGame(guessesLeft);
    }
    
    private void endGame(int guessesLeft){
    	if(guessesLeft == 0) {
    		println("you lost :(");
    	}
    	else {
    		println("you win!");
    	}
    	askForRematch();
    }

	private void askForRematch() {
		String playAgain = readLine("do you want to play again? \n yes or no");
    	if (playAgain.startsWith("yes")) {
    		setupGame();
    	}
    	else if(playAgain.startsWith("no")) {
    		println("ok end of game");
    	}
    	else{
			println("sorry responce need to be either yes or no");
			askForRematch();
		}
    }
    
    private String getGuess() {
    	guess = readLine( 
				"The word now looks like this: " + knownWord 
				+ "\n Please guess a letter here -> ").toUpperCase();
    	if (guess.length() != 1){
    		println("  That was not a valid guess try again");
    		getGuess();
    	}
    	return (guess);
    }
    
    private void correctGuess() {
    	updateKnownWord();
		println("Awesome " + guess + " is in the word");
		println("Here is what the word looks like now: " + knownWord);
    }
    
    
    /*
     * goes though each letter of the secretWord
     * replaces dashed in knownWord with guess
     * 		 untill reach the last copy of guess in the string
     */
    private void updateKnownWord() {
    	println("\n the word is " + secretWord);    	
    	// go from last letter to end looking for next letter
    	// call replace if found one
    	// end when at last letter
    	int replaceLocation = 0;
    	while(replaceLocation < secretWord.length()) {
    		println("i = " + replaceLocation);
    		println("current KnownWord " + knownWord);
    		replaceLocation = secretWord.indexOf(guess, replaceLocation);
    		// quit if guess is not in the rest of the word
    		if (replaceLocation == -1) {break;}
    		replaceLocation = updateKnownWord(replaceLocation);
    	}
    }
    
    private int updateKnownWord(int replaceLocation) {
    	String start;
    	String end;
    	// handle when its the first or last char in string
    	if (replaceLocation == 0) {
    		start = "";
    		end = knownWord.substring(1);
    	}
    	// handles when last letter
    	else if (replaceLocation == knownWord.length()) {
    		start = knownWord.substring(0, knownWord.length() -1);
    		end = "";
    	}
    	else { 
    		start = knownWord.substring(0, replaceLocation);
    		end = knownWord.substring(replaceLocation + 1);
    	}
    	knownWord = start + guess + end;
    	return (replaceLocation += 1);	
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
