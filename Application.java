package shah.shipra.hamurabi;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		//CREATE THE FIRST OBJECT
		HamurabiPlayer player1 = new HamurabiPlayer();
		
		Scanner sc = new Scanner(System.in);
		while (!player1.hasGameEnded()){
			System.out.println(player1.toString());
			System.out.print("Enter Land to buy(positive) or sell (negative) : ");
			
		    int landTrade = sc.nextInt();
		    System.out.print("Enter Number of people to feed(20 bushels per person): ");
		    int numOfPeople = sc.nextInt();
		    System.out.print("Enter Acre of land to sow(1 bushels per acre): ");
		    int landToSow = sc.nextInt();
		    try {
				player1.play(landTrade, numOfPeople, landToSow);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		    
		    	
		}
		System.out.println("Game Ended");
		System.out.println(player1.toString());
		sc.close();
		
	    
	}

}
