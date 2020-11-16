package tests;

import java.util.Scanner;

public class TicTacToe {

	private static final int QTTY_ROWS = 3;
	private static final int QTTY_COLS = 3;

	public static void main(String[] args) {
		showMessage("tic-tac-toe");

		play();

	}

	private static void play() {
		char[][] board = initializeArray(QTTY_ROWS, QTTY_COLS);

		visualize(board);

		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want to play?");
		System.out.print("Of course! (1) / No, i don't want :( (0) -> ");
		int instr = scan.nextInt();
		System.out.println();

		while (instr != 0) {

			board = initializeArray(QTTY_ROWS, QTTY_COLS);

			boolean winner = false;
			int acc = 0;
			int player = 1;
			while (!winner && acc < 9) {
				acc++;
				player = acc % 2;
				winner = runGame(player, board);
			}

			if (acc == 9) {
				showMessage("it was a tie");
			}

			System.out.println("Play again?");
			System.out.print("Of course! (1) / No, i don't want :( (0) -> ");
			instr = scan.nextInt();
			System.out.println();
		}

		if (instr == 0) {
			System.out.println("Te veré volver...");
			showMessage("see you later aligator!");
		}

	}

	/**
	 * @param player
	 * @param board
	 * @return
	 */
	private static boolean runGame(int player, char[][] board) {

		changeTurn(player);

		int row = choosePosition("Row");
		int col = choosePosition("Column");

		boolean winner = false;

		while (board[row - 1][col - 1] == 'X' || board[row - 1][col - 1] == 'O') {
			showError("Position occupied");
			System.out.println("Try again!");
			System.out.println();
			row = choosePosition("Row");
			col = choosePosition("Column");
		}

		if (player == 1) {
			board[row - 1][col - 1] = 'X';
		} else {
			board[row - 1][col - 1] = 'O';
		}

		winner = checkWinner(board);

		printBoard(board);

		if (winner) {
			chooseWinner(player);
		}

		return winner;
	}

	/**
	 * @param player
	 */
	private static void chooseWinner(int player) {
		if (player == 0) {
			showMessage("*.* You won P" + (player + 2) + "! *.*");
		} else {
			showMessage("*.* You won P" + player + "! *.*");
		}
	}

	/**
	 * @param type
	 * @return
	 */
	private static int choosePosition(String type) {
		Scanner scan = new Scanner(System.in);

		System.out.print("Enter " + type + " where you want to be (1-3): ");
		int pos = scan.nextInt();
		while (pos <= 0 || pos > 3) {
			showError(type + " wrong");
			System.out.print("Enter " + type + " where you want to be (1-3): ");
			pos = scan.nextInt();
		}

		System.out.println();

		return pos;
	}

	/**
	 * @param player
	 */
	private static void changeTurn(int player) {
		if (player == 0) {
			System.out.println("Turn for P" + (player + 2) + " (O)");
		} else {
			System.out.println("Turn for P" + player + " (X)");
		}
	}

	/**
	 * @param array
	 * @return
	 */
	private static boolean checkWinner(char[][] array) {
		boolean row1 = (array[0][0] == array[0][1]) && (array[0][0] == array[0][2])
				&& (array[0][0] == 'X' || array[0][0] == 'O');
		boolean row2 = (array[1][0] == array[1][1]) && (array[1][0] == array[1][2])
				&& (array[1][0] == 'X' || array[1][0] == 'O');
		boolean row3 = (array[2][0] == array[2][1]) && (array[2][0] == array[2][2])
				&& (array[2][0] == 'X' || array[2][0] == 'O');

		boolean col1 = (array[0][0] == array[1][0]) && (array[0][0] == array[2][0])
				&& (array[0][0] == 'X' || array[0][0] == 'O');
		boolean col2 = (array[0][1] == array[1][1]) && (array[0][1] == array[2][1])
				&& (array[0][1] == 'X' || array[0][1] == 'O');
		boolean col3 = (array[0][2] == array[1][2]) && (array[0][2] == array[2][2])
				&& (array[0][2] == 'X' || array[0][2] == 'O');

		boolean diag1 = (array[0][0] == array[1][1]) && (array[0][0] == array[2][2])
				&& (array[0][0] == 'X' || array[0][0] == 'O');
		boolean diag2 = (array[0][2] == array[1][1]) && (array[0][2] == array[2][0])
				&& (array[0][2] == 'X' || array[0][2] == 'O');

		return row1 || row2 || row3 || col1 || col2 || col3 || diag1 || diag2;
	}

	private static void visualize(char[][] array) {
		System.out.println("Let's play!");
		System.out.println();
		System.out.println("Columns:  1   2   3");
		for (int i = 0; i < QTTY_ROWS; i++) {
			System.out.print("Rows: " + (i + 1));
			for (int j = 0; j < QTTY_COLS; j++) {
				System.out.print(" | ");
				System.out.print(array[i][j]);
			}
			System.out.println(" | ");
		}

		System.out.println();
	}

	private static char[][] printBoard(char[][] array) {
		for (int i = 0; i < QTTY_ROWS; i++) {
			for (int j = 0; j < QTTY_COLS; j++) {
				System.out.print(" | ");
				System.out.print(array[i][j]);
			}
			System.out.println(" | ");
		}

		System.out.println();

		return array;
	}

	private static char[][] initializeArray(int row, int col) {
		char[][] array = new char[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				array[i][j] = ' ';
			}
		}

		return array;
	}

	private static void showMessage(String text) {
		drawLine(text.length(), "-");
		System.out.println(text.toUpperCase());
		drawLine(text.length(), "-");
		System.out.println();
	}

	private static void drawLine(int lenght, String symbol) {
		for (int i = 0; i < lenght + 1; i++) {
			System.out.print(symbol);
		}
		System.out.println();
	}

	private static void showError(String text) {
		System.out.println("*** " + text.toUpperCase() + " ***");
		System.out.println();
	}
}
