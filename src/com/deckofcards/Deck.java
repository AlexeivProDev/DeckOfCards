/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deckofcards;

import com.deckofcards.Deck.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author alexe
 */
// add 2 methods to deck
// one method is going to return how many black cards are in or red cards

public class Deck implements Iterable<Card> {

    List<Card> cards = new ArrayList<>(52);

    //final public Suit suit;
    public Deck() {

        for (Suit suit : Suit.values()) {

            for (Rank rank : Rank.values()) {

                var card = new Card(suit, rank);
                this.cards.add(card);

            }
        }
        Collections.shuffle(this.cards);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.cards);
        return hash;
    }
// so basically we just want to return true if both decks have the same set of cards. Do they have the same cards in them?
    //it has to work for non-full decks as well

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Deck other = (Deck) obj;
        var thisCards = new HashSet<>(this.cards);
        var otherCards = new HashSet<>(other.cards);
        return Objects.equals(thisCards, otherCards);

    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public int blacks() {
        var count = 0;
        for (Card card : this.cards) {
            if (card.color() == Color.BLACK) {
                count++;
            }
        }
        return count;

    }

    public int faceCards() {
        var count = 0;
        for (Card card : this.cards) {
            if (card.face()) {
                count++;
            }
        }
        return count;
    }

    public int reds() {
        var count = 0;
        for (Card card : this.cards) {
            if (card.color() == Color.RED) {
                count++;
            }
        }
        return count;

    }

    public List<Card> sortBlack(Color color) {
        List<Card> hand = new ArrayList<>();

        for (Card card : cards) {
            if (card.color() == Color.BLACK) {
                hand.add(card);
            } else {
                System.out.println("Error");
            }
        }
        for (Card card : hand) {
            cards.remove(card);
        }
        return hand;
    }

    public List<Card> sortRed(Color color) {
        List<Card> hand = new ArrayList<>();

        for (Card card : cards) {
            if (card.color() == Color.RED) {
                hand.add(card);
            } else {
                System.out.println("error");
            }
        }
        for (Card card : hand) {
            cards.remove(card);
        }

        return hand;
    }

    public List<Card> deal(int count) {
        List<Card> hand = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            var card = this.cards.remove(0);
            hand.add(card);
        }

        return hand;
    }

    public int count() {
        return this.cards.size();
    }

    @Override
    public String toString() {
        return "Deck{" + "cards=" + cards + '}';
    }

    @Override
    public Iterator<Card> iterator() {
        return this.cards.iterator();
    }
    ///write a method to group the suits together then within suit order them by their value(rank)
    ///suits will be ordered alphabetically
    public static List<Card> orderHand(List<Card> hand){
//        Collections.sort(hand, new Comparator<Card>() {
//            public int compare(Card c1, Card c2) {
//                
//                
//               var  suit = c1.CompareSuit(c2);
//               if (0 == suit)
//               {
//                   return c1.compareTo(c2);
//                   
//               }
//               return suit;
//            }
//        });
       
        Comparator<Card> comp = (card1, card2) -> {     
            var suit = card1.CompareSuit(card2);
            if (0 == suit)
                return card1.compareTo(card2);
            
            return suit;
        };
        Collections.sort(hand, comp);
        return hand;
    
    }

    public static class Card implements Comparable<Card> {

        final public Suit suit;
        final public Rank rank;

        public Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }

        public Color color() {
            if (this.suit == Suit.CLUBS) {
                return Color.BLACK;
            }
            if (this.suit == Suit.HEARTS) {
                return Color.RED;
            }
            if (this.suit == Suit.DIAMONDS) {
                return Color.RED;
            }

            return Color.BLACK;
            //what type can you use to represent red or black?
        }

        public boolean face() {
            if (this.rank == Rank.ACE) {
                return false;
            }

            return this.rank.compareTo(Rank.TEN) > 0;

        }

        @Override
        public String toString() {
            return "Card{" + "suit=" + suit + ", rank=" + rank + '}';
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 83 * hash + Objects.hashCode(this.suit);
            hash = 83 * hash + Objects.hashCode(this.rank);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Card other = (Card) obj;
            if (this.suit != other.suit) {
                return false;
            }
            if (this.rank != other.rank) {
                return false;
            }
            return true;
        }

        @Override
        public int compareTo(Card other) {
            if (this.rank.compareTo(other.rank) < 0) {
                return -1;
            }

            if (this.rank.compareTo(other.rank) > 0) {
                return 1;
            }
            return 0;
        }
        
        public int CompareSuit(Card other) {
            if (this.suit.compareTo(other.suit) < 0) {
                return -1;
            }
            if (this.suit.compareTo(other.suit) > 0) {
                return 1;
            }
            return 0;
        }
    }

    public static enum Color {
        BLACK, RED;
    }

    public static enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES;

    }

    public static enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;
    }
}
