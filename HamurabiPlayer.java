package shah.shipra.hamurabi;

import java.util.Random;

//Each HamurabiPlayer will be associated with 
//1. Number of bushels
//2. Land available to the player
//3. People in the city
//4.year or round number of the game
//5. Game Ended flag
public class HamurabiPlayer {

	private int numOfBushels;
	private int acreOfLand;
	private int numOfPeople;
	private int year;
	private boolean hasGameEnded;
	
	//For TEST CASES
	private boolean testMode = false;
	
	private int pricePerAcre;
	
	//ASSUMPTION VALUE MADE FOR THE GAME 
	private static final int INIT_NUM_BUSHELS = 3000;
	private static final int INIT_ACRE_LAND = 1000;
	private static final int INIT_NUM_PEOPLE = 100;
	private static final int INIT_YEAR = 1;
	private static final int BUSHELS_PER_PERSON = 20;
	private static final int BUSHELS_PER_ACRE = 1;
	private static final double LEAST_POPULATION_REQUIRED = 0.55;
	private static final int LAST_YEAR = 10;

	//CONSTRUCTOR
	public HamurabiPlayer() {
		initializeHamurabiPlayer();
	}
	
	public HamurabiPlayer(boolean testMode) {
		this.testMode = testMode;
		initializeHamurabiPlayer();
	}
	
	private void initializeHamurabiPlayer()
	{
		numOfBushels = INIT_NUM_BUSHELS;
		acreOfLand = INIT_ACRE_LAND;
		numOfPeople = INIT_NUM_PEOPLE;
		year = INIT_YEAR;
		hasGameEnded=false;
		pricePerAcre = generateRandomPriceOfLand();
	}

	public void play(int landTrade, int numberOfPeopleToFeed, int landToSow) throws Exception{
		
		//CHECKING FOR BORDER CASES 
		if(numberOfPeopleToFeed < 0)
			throw new Exception("Number of people to feed cannot be less than zero");
		
		if(landToSow < 0)
			throw new Exception("Land to sow cannot be less than zero");
		
		
		if(numOfPeople < numberOfPeopleToFeed)
			throw new Exception("Number of people to feed cannot exceed the total number of people present");
		
		if(acreOfLand < landToSow)
			throw new Exception("Land to Sow can't be more than land available");
	
		
		int bushelsToFeed=BUSHELS_PER_PERSON*numberOfPeopleToFeed;
		int bushelsToSow=BUSHELS_PER_ACRE*landToSow;
		int bushelsToBuyLand=0;
		int bushelsToSowNewLand = 0;
		
		if(landTrade < 0) {
			if(acreOfLand < (landTrade*-1) + landToSow)
				throw new Exception("Land to Sow  plus land to sell cant exceed land available");
		}
		else {
			bushelsToBuyLand=pricePerAcre*landTrade;
			bushelsToSowNewLand=landTrade;
		}
		
		
		if(numOfBushels < bushelsToFeed + bushelsToSow + bushelsToBuyLand)
			throw new Exception("Bushels used for feeding, sowing and buying new land cant exceed bushels available");
		
		if(numberOfPeopleToFeed< LEAST_POPULATION_REQUIRED*numOfPeople){   hasGameEnded=true;
		return;
		}
		
		//PEOPLE WHO ARE NOT FED , STARVE
		numOfPeople=numberOfPeopleToFeed;
		
		//BUSHELS REMAINING AFTER FEEDING, SOWING, BUYING/SELLING LAND
		numOfBushels=numOfBushels-bushelsToFeed-bushelsToSow-bushelsToSowNewLand;
				
		//REMOVNG LAND WHICH WAS NEITHER TRADED NOR FARMED 
		if(landTrade>=0)
			acreOfLand=(landToSow+landTrade);
		else 
			acreOfLand=landToSow;
		
		//FUNCTION TO GENERATE ALL THE RANDOM CASES
		
		generateRandomEvent(landTrade,landToSow);
		
			
		
		//IF POPULATION DROPPED DOWN TO 0
		if(numOfPeople<=0){   hasGameEnded=true;
		return;
		}
		year++;
		
		//if all the years are played
		if(year==LAST_YEAR){   hasGameEnded=true;
		return;
		}
		
		pricePerAcre = generateRandomPriceOfLand();
	}
	
	private void generateRandomEvent(int landTrade,int landToSow ){
		int eatenByRat=generateRandomEatenByRat();
		System.out.println("Bushels eaten by rat : " + eatenByRat);
		numOfBushels=numOfBushels-eatenByRat;
		Boolean isPlague=generateRandomPlague();
		
		if(isPlague){
			numOfPeople=numOfPeople/2;
			System.out.println("Plague reduced the population by half");
		}
		else System.out.println("Plague was absent");
		
		int immigrants=generateRandomImmigrants();
		numOfPeople=numOfPeople+immigrants;
		
		System.out.println("Number of people arrived : " + immigrants);
		//int priceOfLand=generateRandomPriceOfLand();
		numOfBushels=numOfBushels-(pricePerAcre*landTrade);
		
		System.out.println("Price per acre : " + pricePerAcre);
		int bushelsPerAcre=generateRandomBushelsPerAcre();
		numOfBushels=numOfBushels+(acreOfLand*bushelsPerAcre);
		
		System.out.println("Bushels per acre : " + bushelsPerAcre);
	}


	private int generateRandomBushelsPerAcre() {
		
		if(testMode)
			return 5;
		
		//generate random number between 0 to 10 both inclusive
		Random rand = new Random();
		return rand.nextInt(11); 
	}


	private int generateRandomPriceOfLand() {
		
		if(testMode)
			return 25;
		
		
		//generate random price between 17 to 26
		Random rand = new Random();
		return (rand.nextInt(10) + 17);
		
	}


	private int generateRandomImmigrants() {
		
		if(testMode)
			return 10;
		
		//generate random number between 0 to 20 both inclusive
		Random rand = new Random();
		return rand.nextInt(21); 
	}


	private Boolean generateRandomPlague() {
		
		if(testMode)
			return false;
		
		//there is a 20% chance that plague will happen
		Random rand = new Random();
		int chance = rand.nextInt(101);
		if(chance > 79)
			return true;
		else
			return false;
	}


	private int  generateRandomEatenByRat() {
		
		if(testMode)
			return 200;
		
		//generate random number between 0 to all both inclusive
		Random rand = new Random();
		return rand.nextInt(numOfBushels + 1);
	}

	public boolean hasGameEnded()
	{
		return hasGameEnded;
	}
	
	
	
	public int getNumOfBushels() {
		return numOfBushels;
	}


	public int getAcreOfLand() {
		return acreOfLand;
	}


	public int getNumOfPeople() {
		return numOfPeople;
	}


	public int getYear() {
		return year;
	}


	@Override
	public String toString() {
		return "HamurabiPlayer [numOfBushels=" + numOfBushels + ", acreOfLand=" + acreOfLand + ", numOfPeople="
				+ numOfPeople + ", year=" + year + ", pricePerAcre=" + pricePerAcre + "]";
	}
	
}
