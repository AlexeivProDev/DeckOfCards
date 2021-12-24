/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deckofcards;

import com.deckofcards.Deck.Card;
import com.deckofcards.Deck.Color;
import com.deckofcards.Deck.Rank;
import com.deckofcards.Deck.Suit;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexe
 */
public class DeckTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        var deck = new Deck();
        System.out.println(deck);
        // var clubs = Deck.Suit.CLUBS;
        // var two = Deck.Rank.TWO;
        deck.shuffle();
        System.out.println(deck);
    }

    @Test
    public void cardTest() {
        var card = new Card(Deck.Suit.DIAMONDS, Deck.Rank.QUEEN);

        assertEquals("", Suit.DIAMONDS, card.suit);
        assertEquals("", Rank.QUEEN, card.rank);

    }

    @Test
    public void dealHand() {
        var deck = new Deck();

        var cards = deck.deal(5);
        assertEquals(5, cards.size());
        System.out.println(cards);
        assertEquals(47, deck.count());
        cards = deck.deal(2);
        assertEquals(2, cards.size());
        System.out.println(cards);
        assertEquals(45, deck.count());
    }

    @Test
    public void cardColor() {
        var card = new Card(Suit.CLUBS, Rank.JACK);
        assertEquals(Color.BLACK, card.color());
        card = new Card(Suit.DIAMONDS, Rank.EIGHT);
        assertEquals(Color.RED, card.color());
        card = new Card(Suit.HEARTS, Rank.THREE);
        assertEquals(Color.RED, card.color());
        card = new Card(Suit.SPADES, Rank.TEN);
        assertEquals(Color.BLACK, card.color());

    }

    @Test
    public void sortCards() {
        var deck = new Deck();
        var card = new Card(Suit.CLUBS, Rank.JACK);
        var cards = deck.sortBlack(Color.BLACK);
        assertEquals(Color.BLACK, card.color());
        var card2 = new Card(Suit.DIAMONDS, Rank.QUEEN);
        var cardss = deck.sortRed(Color.RED);
        assertEquals(Color.RED, card2.color());
    }

    @Test
    public void numBlacks() {
        var deck = new Deck();

        assertEquals(26, deck.blacks());
        List<Card> hand = deck.deal(5);
        var count = 0;
        for (Card card : hand) {
            if (card.color() == Color.BLACK) {
                count++;
            }

        }
        System.out.println(count);
        assertEquals(26 - count, deck.blacks());
    }

    @Test
    public void numReds() {
        var deck = new Deck();

        assertEquals(26, deck.reds());
        List<Card> hand = deck.deal(5);
        var count = 0;
        for (Card card : hand) {
            if (card.color() == Color.RED) {
                count++;
            }

        }
        System.out.println(count);
        assertEquals(26 - count, deck.reds());
    }
    
    @Test
    public void cardRemain(){
        var deck = new Deck();
        
        assertEquals(deck.count(), deck.reds() + deck.blacks());
        deck.deal(10);
        assertEquals(deck.count(), deck.reds() + deck.blacks());
    }
    @Test
    public void faceCards(){
        var deck = new Deck();
        assertEquals(12, deck.faceCards());
        List<Card> hand = deck.deal(7);
        var count = 0;
        for (Card card: hand)
        {
            if (card.face())
                count++;
        }
        System.out.println(count);
        assertEquals(12 - count, deck.faceCards());
    }

    @Test
    public void cardFace() {
        var card = new Card(Suit.CLUBS, Rank.JACK);
        assertEquals(true, card.face());
        card = new Card(Suit.CLUBS, Rank.EIGHT);
        assertEquals(false, card.face());
        card = new Card(Suit.DIAMONDS, Rank.QUEEN);
        assertEquals(true, card.face());
        card = new Card(Suit.DIAMONDS, Rank.ACE);
        assertEquals(false, card.face());
    }
    @Test
    public void deckEquality(){
        var deck = new Deck();
        var deck2 = new Deck();
        
        assertEquals(deck, deck2);
        
        deck.deal(5);
        
        assertNotEquals(deck, deck2);
        
        deck2.deal(5);
        
        assertNotEquals(deck, deck2);

    }
    @Test
    public void loopCards(){
        var deck = new Deck();
        
        for (Card card : deck) {
            
        }
        deck.deal(40);
        
        for (Card card : deck) {
            System.out.println(card);
        }
    }
    @Test
    public void cardCompare(){
        var card = new Card(Suit.CLUBS, Rank.JACK);
        var card2 = new Card(Suit.DIAMONDS, Rank.QUEEN);
        var card3 = new Card(Suit.DIAMONDS, Rank.JACK);
        assertEquals(-1, card.compareTo(card2));
        assertEquals(0, card.compareTo(card3));
        assertEquals(1, card2.compareTo(card3));
    }
    @Test
    public void orderHand(){
        var card = new Card(Suit.SPADES, Rank.JACK);
        var card2 = new Card(Suit.DIAMONDS, Rank.FOUR);
        var card3 = new Card(Suit.SPADES, Rank.TWO);
        var card4 = new Card(Suit.HEARTS, Rank.ACE);
        var card5 = new Card(Suit.HEARTS, Rank.TEN);
        
        var hand = Arrays.asList(card, card2, card3, card4, card5);
        
        var order = Deck.orderHand(hand);
        
        assertEquals(card2, order.get(0));
        assertEquals(card5, order.get(1));
        assertEquals(card4, order.get(2));
        assertEquals(card3, order.get(3));
        assertEquals(card, order.get(4));
        
        System.out.println(order);
    }
   
    @Test
    public void listEquality(){
        var list1 = Arrays.asList(1,2);
        var list2 = Arrays.asList(2,1);
        
        assertFalse(Objects.equals(list1, list2));
        list2 = Arrays.asList(1, 2);
        
        assertTrue(Objects.equals(list1, list2));
    }
    /**
     * Test of toString method, of class Deck.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Deck instance = new Deck();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
