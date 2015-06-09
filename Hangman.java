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
	public static final int APPLICATION_WIDTH = 800;
	public static final int APPLICATION_HEIGHT = 600;
	
	public void init() {
        canvas = new HangmanCanvas();
        add(canvas);
    }

	
	// setup then starts a game
    public void run() {
    	setupGame();
    	playGame();
	}

    private void setupGame() {
    	secretWord = getSecretWord();
    	initalizeKnownWord();
    	println(knownWord + " knownWord "  + " secret " + secretWord);
    	setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
    	canvas.reset(knownWord, secretWord);
    	guessesLeft = 11;
    }
    
    // currently gets random word from the HangmanLexicon class
    private String getSecretWord() {  	
    	int wordIndex = rgen.nextInt(lex.getWordCount());
    	String secretWord = lex.getWord(wordIndex);
    	return (secretWord);
    }
     
    private void initalizeKnownWord() {
    	knownWord = "";
    	for (int i = 0; i < secretWord.length(); i++) {
    		knownWord += "-";
    	}
    }

    // while there are still free guesses
    // takes user guess
    // check if its in the secret word
    // updates knownWord 
    private void playGame() {
    	println("Welcome to a round of Hangman");
    	println("the word is: " + secretWord);
    	canvas.displayWord(knownWord);
    	int testingItt = 0;
    	while(guessesLeft > 0 ) {
    		if(testing) {
    			println(testingItt);
    			testRun(testingItt);
    			testingItt += 1;
    			println(secretWord);
    		}
    		else {
    			println("user guess");
    			guess = getGuess(); 
    			println("got your guess it is: " + guess);
    			println("got your guess it is: " + guess);
    		}
    		// secretWord.
    		if (secretWord.indexOf(guess) != -1) {
    			correctGuess();
    		}
    		else if(canvas.incorrectGuesses.indexOf(guess) != -1) {
    			println("you alredy guessed that and it is not in the word");
    		}
    		else {
    			wrongGuess();
    		}
    		if (secretWord.equalsIgnoreCase(knownWord) == true) {
    			break;
    		}
    		println(secretWord);
    	}
    	endGame(guessesLeft);
    }
    
    private void testRun(int testingItt) {
    	String guesses;
    	secretWord = "AAAAA";
    	if (testWrongGuesses) {
    		guesses = "qwertyuiopl";
    	}
    	else {
    		guesses = "AAAAA";
    	}
		guess = guesses.charAt(testingItt);
    }
    
    // fix so not double checking for game lost
    // then display full word to show user if won or lost
    
    private void endGame(int guessesLeft){
    	if(guessesLeft == 0) {
    		println("\n you lost :( \n the word was: " + secretWord);
    	}
    	else {
    		println("\n You figured it out! \n the word was: "
    				+ secretWord + "\n You won!");
    	}
    	askForRematch();
    }

	private void askForRematch() {
		String playAgain = readLine(
				"\n do you want to play again? \n yes or no: ");
    	if (playAgain.startsWith("yes")) {
    		run();
    	}
    	else if(playAgain.startsWith("no")) {
    		println("Okay thanks for playing");
    	}
    	else{
			println("\n sorry responce needs to be either yes or no");
			askForRematch();
		}
    }
    
    private char getGuess() {
    	String rawGuess = readLine( 
				"\n The word now looks like this: " + knownWord 
				+ "\n Please guess a letter here -> ").toUpperCase();
    	if (rawGuess.length() != 1){
    		println("  That was not a valid guess try again");
    		getGuess();
    	}
    	guess = rawGuess.charAt(0);
    	return (guess);
    }
    
    private void correctGuess() {
    	updateKnownWord();
    	canvas.displayWord(knownWord);
    }
    
    
    /*
     * goes though each letter of the secretWord
     * replaces dashed in knownWord with guess
     * 		 untill reach the last copy of guess in the string
     */
    private void updateKnownWord() {
    	int replaceLocation = 0;
    	while(replaceLocation < secretWord.length()) {
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
    	canvas.noteIncorrectGuess(guess);
    	//println("incorrectGuesses: " + canvas.incorrectGuesses);
    	println(guess + " is not in the secret word"
    			+ "\n You have " + guessesLeft + " guesses left!");
    }
    
    /** Instance Variables */
    char guess;
    String secretWord;
    int guessesLeft;
    String knownWord;
    
    HangmanLexicon lex = new HangmanLexicon();
    RandomGenerator rgen = new RandomGenerator();
    private HangmanCanvas canvas;
    boolean testing = false;
    boolean testWrongGuesses = false;	// are auto guesses right or wrong
}
