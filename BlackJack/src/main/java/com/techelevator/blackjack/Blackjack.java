package com.techelevator.blackjack;

import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {

        System.out.println("Eternal BlackJack: ");

        Scanner userInput = new Scanner(System.in);

        boolean done = false;
        while (!done) {

            Deck playingDeck = new Deck();
            playingDeck.createDeck();
            playingDeck.shuffle();

            Deck playerDeck = new Deck();

            Deck dealerDeck = new Deck();

            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            boolean finished = false;

            while (!finished) {
                System.out.print("Your hand: ");
                System.out.print(playerDeck.toString());
                System.out.println("\n Total: " + playerDeck.cardsValue());

                System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() + " and [Hidden].");

                System.out.println("Would you like to (1)Hit or (2)Stand?");
                int response = userInput.nextInt();
                if (response == 1) {
                    playerDeck.draw(playingDeck);
                    System.out.println("You drew: " + playerDeck.getCard(playerDeck.deckSize() - 1).toString());
                    if (playerDeck.cardsValue() > 21) {
                        System.out.println("Bust, you lose. Total: " + playerDeck.cardsValue());
                        finished = true;
                        break;
                    }
                }
                if (response == 2) {
                    break;
                }
            }

            System.out.println("Dealer hand: " + dealerDeck.toString());
            if ((dealerDeck.cardsValue() > playerDeck.cardsValue()) && finished == false) {
                System.out.println("Dealer wins!");
                finished = true;
            }
            while ((dealerDeck.cardsValue() < 17) && finished == false) {
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer drew: " + dealerDeck.getCard(dealerDeck.deckSize() - 1).toString());
            }
            System.out.println("Dealer total: " + dealerDeck.cardsValue());
            if ((dealerDeck.cardsValue() > 21) && finished == false) {
                System.out.println("Dealer busts, you win!!!");
                finished = true;
            }

            if ((playerDeck.cardsValue() == dealerDeck.cardsValue()) && finished == false) {
                System.out.println("You tied with the dealer");
                finished = true;
            }

            if ((playerDeck.cardsValue() > dealerDeck.cardsValue()) && finished == false) {
                System.out.println("You won!!");
                finished = true;
            } else if (finished == false) {
                System.out.println("You lose.");
                finished = true;
            }

            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);

            System.out.println("Hand is over");

            System.out.println("\n");

        }


        }

    }
