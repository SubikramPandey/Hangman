/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset(String knownWord, String secretWord) {
		/* You fill this in */
		removeAll();
		addScaffold();
		addKnownWord(knownWord);
		addWrongGuesses();
	}
	
	private void addKnownWord(String knownWord) {
		knownDisplay = new GLabel(knownWord);
		double xWord = xDrawCenter - knownDisplay.getWidth()/2;
		double yWord = bottomScaffold + wordSpacer;
		knownDisplay.setLocation(xWord, yWord);
		add (knownDisplay);
	}
	
	// change to wrong guess
	private void addWrongGuesses() {
		
	}
	
	private void addScaffold() {
		// all lines are horizontialy relative to the center of the screen
		GLine rope = new GLine(xDrawCenter, yScaffold,
				xDrawCenter, yScaffold + ROPE_LENGTH);
		add(rope);
		GLine beam = new GLine(xScaffold,
				yScaffold, xDrawCenter, yScaffold);
		add(beam);
		GLine scaffold = new GLine(xScaffold, yScaffold,
				xScaffold, bottomScaffold);
		add(scaffold);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */
		remove(knownDisplay);
		addKnownWord(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		/* You fill this in */
		
	}

	// Instance variables 		// ISSUE more logical nameing convention
	int xDrawCenter = Hangman.APPLICATION_WIDTH / 4;
	int yScaffold = Hangman.APPLICATION_HEIGHT / 8;
	int xScaffold = xDrawCenter - BEAM_LENGTH;
	int bottomScaffold = yScaffold + SCAFFOLD_HEIGHT;
	int wordSpacer = 50;		// currently arbitrary
	GLabel knownDisplay;
	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
