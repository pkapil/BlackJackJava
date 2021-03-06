package com.decker.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.decker.model.BlackJackHand;
import com.decker.model.Card;
import com.decker.model.Deck;
import com.decker.model.Game;

public class Exec {
	public static void main(String args[]) throws IOException {

		while (true) {
			String input;
			do {
				System.out.println("GAME STARTED  ");
				System.out.println("==============");
				System.out.println("Do you want to play/continue the game: Y/N  ");
				input = new BufferedReader(new InputStreamReader(System.in)).readLine();
				if (input.equalsIgnoreCase("Y")) {
					startGame();
				} else if (input.equalsIgnoreCase("N")) {
					System.exit(0);
				}

			} while (!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N"));
		}
	}

	public static void startGame() throws IOException {
		Game game = new Game();
		Deck deck = game.getDeck();
		BlackJackHand dealer = new BlackJackHand();
		BlackJackHand player = new BlackJackHand();

		dealer.addCard(deck.dealCard());
		dealer.addCard(deck.dealCard());
		player.addCard(deck.dealCard());
		player.addCard(deck.dealCard());

		while (true) {
			if (dealer.isBlackJack()) {
				System.out.println("Dealer Won!!  ");
				System.out.println("---------------");
				getPlayerStats(dealer, player);
				break;
			}
			if (player.isBlackJack()) {
				System.out.println("Player Won!!  ");
				System.out.println("---------------");
				getPlayerStats(dealer, player);
				break;

			}
			if (dealer.isBurst()) {
				System.out.println("Dealer Busted");
				System.out.println("---------------");
				getPlayerStats(dealer, player);
				break;
			}

			getPlayerStats(dealer, player);
			System.out.println("Would you like to play?  H or S ?  ");
			String input = "";

			do {
				input = new BufferedReader(new InputStreamReader(System.in)).readLine();
				if (!input.equalsIgnoreCase("h") && !input.equalsIgnoreCase("s"))
					System.out.println("Please respond H or S:  ");
			} while (!input.equalsIgnoreCase("h") && !input.equalsIgnoreCase("s"));

			if (input.equalsIgnoreCase("H")) {
				player.addCard(deck.dealCard());
				if (player.isBurst()) {
					System.out.println("Player Busted !");
					System.out.println("---------------");
					break;
				}
			}
			if (input.equalsIgnoreCase("S")) {
				dealer.addCard(deck.dealCard());
				if (player.isBurst()) {
					System.out.println("Dealer busted !");
					System.out.println("---------------");
					break;
				}
			}
		}
	}

	private static void getPlayerStats(BlackJackHand dealer, BlackJackHand player) {
		System.out.println("Cards info   ");
		System.out.println("---------------");
		for (Card c : dealer.getCards())
			System.out.println("Dealer card :" + c+"  ");
		for (Card c : player.getCards())
			System.out.println("Player card :" + c+"  ");
		System.out.println("Dealer Count:" + dealer.getCardCount()+"  ");
		System.out.println("Player Count:" + player.getCardCount()+"  ");
	}
}
