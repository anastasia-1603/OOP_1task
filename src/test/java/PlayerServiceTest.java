import model.*;
import static org.junit.Assert.*;

import org.junit.Test;
import service.PlayerService;

import java.util.ArrayList;
import java.util.List;

public class PlayerServiceTest
{
    private PlayerService ps = new PlayerService();
    @Test
    public void testIsHaveCard()
    {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TWO, Suit.CLUBS));
        cards.add(new Card(Rank.EIGHT, Suit.DIAMONDS));
        cards.add(new Card(Rank.FOUR, Suit.CLUBS));
        cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
        Player player = new Player();
        player.setPlayerCards(cards);
        assertTrue(ps.isHaveCard(new Card(Rank.FOUR, Suit.CLUBS), player));
    }

    @Test
    public void testIsHaveCard2()
    {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TWO, Suit.CLUBS));
        Player player = new Player();
        player.setPlayerCards(cards);
        assertFalse(ps.isHaveCard(new Card(Rank.FOUR, Suit.CLUBS), player));
    }
}
