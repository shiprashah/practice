package shah.shipra.hamurabi;

import static org.junit.Assert.*;

import org.junit.Test;

public class HamurabiPlayerTest {

	HamurabiPlayer player1 = new HamurabiPlayer(true);
	@Test
	public void testHamurabiPlayer() {
		assertNotEquals(null, player1);
		assertEquals(100, player1.getNumOfPeople());
	}

	@Test
	public void testPlay() throws Exception {
		System.out.println(player1.toString());
		player1.play(10, 80, 900);
		System.out.println(player1.toString());
		assertEquals(90, player1.getNumOfPeople());
		assertEquals(910, player1.getAcreOfLand());
		assertEquals(4590, player1.getNumOfBushels());
		/*
		 * initial amount = 3000
		 * 10 acres of land bought @25 bushels/acre = -250
		 * bushels eaten by rats = -200
		 * bushels fed to people = -1600
		 * bushels to sow = -910
		 * bushels harvested = 910 * 5 = 4550
		 * */
	}

	@Test
	public void testHasGameEndedDueTo10Yrs() throws Exception {
		player1.play(10, 90, 900);
		player1.play(10, 90, 900);
		player1.play(10, 90, 900);
		player1.play(10, 90, 900);
		player1.play(10, 90, 900);
		player1.play(10, 90, 900);
		player1.play(10, 90, 900);
		player1.play(10, 90, 900);
		player1.play(10, 90, 900);
		player1.play(10, 90, 900);
		
		assertEquals(true, player1.hasGameEnded());
	}
	
	@Test
	public void testHasGameEndedDueToNoFood() throws Exception {
        player1.play(10, 50, 900);
		
		assertEquals(true, player1.hasGameEnded());
	}
	
	@Test
	public void testInputValidationExceptions() {
		try {
			player1.play(10, -50, 900);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("Number of people to feed cannot be less than zero", e.getMessage());
		}
		
		try {
			player1.play(10, 50, -900);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("Land to sow cannot be less than zero", e.getMessage());
		}
		
		try {
			player1.play(10, 500, 900);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("Number of people to feed cannot exceed the total number of people present", e.getMessage());
		}
		
		try {
			player1.play(10, 50, 1000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("Land to Sow can't be more than land available", e.getMessage());
		}
		
		try {
			player1.play(-100, 50, 901);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("Land to Sow  plus land to sell cant exceed land available", e.getMessage());
		}
		
		try {
			player1.play(-150, 50, 600);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("Bushels used for feeding, sowing and buying new land cant exceed bushels available", e.getMessage());
		}
		
	}

}
