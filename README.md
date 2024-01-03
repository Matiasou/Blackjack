# Blackjack

## Implemented a program that plays the game of Blackjack. The program consists of the following classes:

ArrayBag: A blueprint for objects that represent a bag of other objects. This class is used to manage collections of items, such as decks of cards.
Card: Represents a single playing card, with a suit and a rank. This class is essential for creating the deck of cards used in the game and provides methods to get the card's suit, rank, and value according to Blackjack rules.
Player: Creates the player and allows interaction with the program to acquire and change their hand. This class manages the player's hand and includes methods for adding cards, calculating the hand's value, and determining if the player has Blackjack.
Dealer: Takes the role of the dealer in the game. It extends the Player class and includes additional logic specific to the dealer's actions, such as hiding the first card and deciding when to take hits.
Blackjack: The main class that plays the game. This class contains the game's main loop and logic, orchestrating the interactions between players, the dealer, and the deck.
This Blackjack implementation follows the standard rules of the game, allowing players to hit, stand, and automatically handling the values of aces, face cards, and numbered cards. The dealer plays according to the standard casino rules: hitting on 16 or below and standing on 17 or above.
