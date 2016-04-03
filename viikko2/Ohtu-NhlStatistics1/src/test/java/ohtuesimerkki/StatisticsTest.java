/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Redande
 */
public class StatisticsTest {
    
    Statistics stats;
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };
    
    public StatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void loytaaPelaajanJosJoukkueessa() {
        String name = "Semenko";
        Player player = stats.search(name);
        Player expPlayer = readerStub.getPlayers().get(0);
        assertEquals(player, expPlayer);
    }
    
    @Test
    public void palauttaaNullJosEiJoukkueessa() {
        String name = "Mursu";
        Player result = stats.search(name);
        assertEquals(null, result);
    }

    @Test
    public void testTeam() {
        String teamName = "EDM";
        List<Player> expPlayers = new ArrayList<Player>();
        for (Player player : readerStub.getPlayers()) {
            if (player.getTeam().equals(teamName)) {
                expPlayers.add(player);
            }
        }
        List<Player> players = stats.team(teamName);
        assertEquals(expPlayers.size(), players.size());
        for (Player p : expPlayers) {
            assertTrue(players.contains(p));
        }
    }

    @Test
    public void testTopScorers() {
        int howMany = 2;
        List<Player> expResult = readerStub.getPlayers();
        Collections.sort(expResult);
        expResult = expResult.subList(0, howMany+1);
        List<Player> result = stats.topScorers(howMany);
        assertEquals(expResult, result);
    }
    
}
