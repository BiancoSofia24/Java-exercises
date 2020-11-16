package tests;

import java.util.Scanner;

public class Hangman {

	private static final int MIN_WORD = 4;
	private static final int MAX_WORD = 10;
	private static final int MAX_ERR = 10;

	public static void main(String[] args) {

		showMessage("hangman");

		play();

	}

	private static void play() {

		Scanner scan = new Scanner(System.in);

		System.out.println("Start to play? (1 - Go ahead! / 0 - Not even as joke...)");
		int play = scan.nextInt();

		// Still missing levels. Enter a difficulty: easy 10 normal 6 hard 3

		while (play != 0) {

			if (play == 1) {
				String word = generateWord();

				int errors = guess(word);

				showPoints(errors);

				showMessage("thanks for playing!");
			} else {
				System.out.println("You has entered the wrong number");
			}

			System.out.println();
			System.out.println("Start to play? (1 - Go ahead! / 0 - Not even as joke...)");
			play = scan.nextInt();
		}

		showMessage("coward!");
		System.out.println("You miss it...");

	}

	/**
	 * @param errores
	 */
	private static void draw(int errors) {
		switch (errors) {
		case 1:
			System.out.println("Ups...");
			System.out.println("   _______");
			System.out.println();
			break;
		case 2:
			System.out.println("Building...");
			System.out.println("   _______");
			System.out.println("         |");
			System.out.println("         |");
			System.out.println("         |");
			System.out.println("         |");
			System.out.println("         |");
			System.out.println();
			break;
		case 3:
			System.out.println("Building...");
			System.out.println("   _______");
			System.out.println("         |");
			System.out.println("         |");
			System.out.println("         |");
			System.out.println("         |");
			System.out.println("         |");
			System.out.println("   ______|___");
			System.out.println();
			break;
		case 4:
			System.out.println("What we have here?");
			System.out.println("   _______");
			System.out.println("     |   |");
			System.out.println("         |");
			System.out.println("         |");
			System.out.println("         |");
			System.out.println("         |");
			System.out.println("   ______|___");
			System.out.println();
			break;
		case 5:
			System.out.println("I'll have your head :)");
			System.out.println("   _______");
			System.out.println("     |   |");
			System.out.println("     O   |");
			System.out.println("         |");
			System.out.println("         |");
			System.out.println("         |");
			System.out.println("   ______|___");
			System.out.println();
			break;
		case 6:
			System.out.println("It seems to me or are you very thin?");
			System.out.println("   _______");
			System.out.println("     |   |");
			System.out.println("     O   |");
			System.out.println("     |   |");
			System.out.println("         |");
			System.out.println("         |");
			System.out.println("   ______|___");
			System.out.println();
			break;
		case 7:
			System.out.println("Tan, tan...");
			System.out.println("   _______");
			System.out.println("     |   |");
			System.out.println("     O   |");
			System.out.println("    (|   |");
			System.out.println("         |");
			System.out.println("         |");
			System.out.println("   ______|___");
			System.out.println();
			break;
		case 8:
			System.out.println("You're running out of opportunities!");
			System.out.println("   _______");
			System.out.println("     |   |");
			System.out.println("     O   |");
			System.out.println("    (|)  |");
			System.out.println("         |");
			System.out.println("         |");
			System.out.println("   ______|___");
			System.out.println();
			break;
		case 9:
			System.out.println("You still have one chance to waste... :D");
			System.out.println("   _______");
			System.out.println("     |   |");
			System.out.println("     O   |");
			System.out.println("    (|)  |");
			System.out.println("    |    |");
			System.out.println("         |");
			System.out.println("   ______|___");
			System.out.println();
			break;
		case 10:
			System.out.println("¡Sorry! Seek for the revenge");
			System.out.println("   _______");
			System.out.println("     |   |");
			System.out.println("     O   |");
			System.out.println("    (|)  |");
			System.out.println("    | |  |");
			System.out.println("         |");
			System.out.println("   ______|___");
			System.out.println();
			break;
		default:
			System.out.println("You're doing just fine!");
			System.out.println();
		}
	}

	/**
	 * @param tries
	 */
	private static void showPoints(int tries) {
		int ptos = MAX_WORD - tries;
		System.out.println("Your wonderfull score is: " + ptos);
		System.out.println();
	}

	/**
	 * @param word
	 * @return cantErrores
	 */
	private static int guess(String word) {
		Scanner scan = new Scanner(System.in);

		System.out.println();
		System.out.println("Write a letter (with your keybpard) to try to guess.");
		System.out.print("Letter: ");
		String letter = scan.next();
		System.out.println();

		// lettersOk[false, false, false, ...] dimension = longWord
		boolean letterssOk[] = new boolean[word.length()];
		int qttyHits = 0;
		int qttyErrors = 0;
		while (qttyHits < word.length() && qttyErrors != MAX_ERR) {
			// Find a letter in the game word
			int posLetter = word.indexOf(letter.toUpperCase()); // If letter !E = -1

			if (posLetter != -1) {
				letterssOk[posLetter] = true;
				qttyHits++;
			} else {
				qttyErrors++;
			}

			for (int i = 0; i < word.length(); i++) {
				// If lettersOK[true] -> show right letter
				if (letterssOk[i]) {
					System.out.print(" " + word.charAt(i) + " ");
				} else {
					System.out.print(" _ ");
				}
			}

			System.out.println("    You have " + qttyErrors + " errors");
			System.out.println();
			draw(qttyErrors);

			if (qttyHits < word.length() && qttyErrors != MAX_ERR) {
				System.out.println("Start to play? (1 - Go ahead! / 0 - Not even as joke...)");
				System.out.print("Letter: ");
				letter = scan.next();
				System.out.println();
			}
		}

		if (qttyErrors == MAX_ERR) {
			System.out.println("You have lost!");
			System.out.println("Try again");
			System.out.println();
		} else {
			drawWinning();
		}

		return qttyErrors;
	}

	private static void drawWinning() {
		System.out.println("      _______");
		System.out.println("        |   |");
		System.out.println("            |");
		System.out.println("            |");
		System.out.println("   O        |");
		System.out.println("  (|)       |");
		System.out.println("  | | ______|___");
		System.out.println();
		System.out.println("You won!");
		System.out.println("Your price is this message...");
		System.out.println();
	}

	/**
	 * @return palabra
	 */
	private static String generateWord() {
		Scanner scan = new Scanner(System.in);

		System.out.println();
		System.out.println("Ask your friend to enter a word to start playing.");
		System.out.println("¡Can't look!");
		System.out.println();
		System.out.print("Word: ");
		// Only words without repeated letters
		String word = scan.next();

		while (word.length() < MIN_WORD || word.length() > MAX_WORD) {
			System.out.println();
			System.out.println("That word is too long or short for this simple game!");
			System.out.println("Enter a word between " + MIN_WORD + " and " + MAX_WORD + " letters.");
			System.out.println();
			System.out.print("Word: ");
			word = scan.next();
		}

		return word.toUpperCase();
	}

	private static void showMessage(String text) {
		System.out.println("-----------------------------------");
		System.out.println(text.toUpperCase());
		System.out.println("-----------------------------------");
		System.out.println();
	}
}
