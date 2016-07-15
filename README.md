# practice

Q] Which problem does this software solve?
A] Problem selected : http://rubyquiz.strd6.com/quizzes/223-hamurabi

Q] Why was Ruby Quiz Problem#234 chosen?
A] I am fond of strategy board games. This problem seemed like a fun challenge. 
The HR clearly mentioned the following DONT's 
     a. Don't make it a science project 
     b. Don't make it a trick question 
     Problem#2223 satisfied this criteria 


Q] What was the solution approach?
After reading about the basic idea of Hamurabi, development was started based on some assumptions (stated later). The idea was to be able to provide an application which can do the essential tasks of a Hamurabi game viz. initialize a player, play a turn, get notified if a turn is unacceptable, get an account of the state of the game after every move, end the game when appropriate.
The rules of the games were whiteboarded into algorithims which was then followed by encoding them in java.

Q] What criteria was used to certify the solution as acceptable?
A] All the tests mentioned in HamurabiPlayerTest.java were required to pass
1. The Hamurabi player should be initialized with correct values and no errors
2. The legal plays should result in correct scoring
3. Illegal plays should result in required exceptions
In addition to the above tests, Application.java class was created to perform functional user testing (and, by the way, play a few rounds of the game :-) )



Q] What were the assumptions taken for the solution?
The is no standard rule book for Hamurabi, so to solve the problem, the following assumptions were made -- 
Assumption1: The player start with the following
3000 brushels
100 people
1000 acre of land

Assumption 2: brushels can't go negative

Assumption 3: game ends when player starves 45% or more of his population, if 10 years surpass or if the number of people go below 0.

Assumption 4: Rat can eat 0 to the number of bushels available after feeding the population and using to sow the land. 

Q] How can one expand on this problem?
For a generic game a super player class can be created and hamurabi player class can then inherit (extend in case of java) that class
For a more specilized hamurabi player, one can create the specilized class and have it inherit hamurabi player class
The functionality of the class can be expanded to provide other sophisticated applications such as Setellers of Catan and PueroRico

Q] How can you make this a multi player game?
A] There is a note in Ruby Quiz about making this a multi player game. With some refactoring, this solution can be made into a multi player game. Here is the idea:

</br>
	SingletonGameManager
	{
	Map < playerName, HamurabiPlayer >

	All the players will share the common random elements. The following elements will belong to the singleton manager:
	isPlague;
	priceOfLand;
	immigrants;
	bushelsPerAcre;
	year;

	The individual properties of state of the city, such as the following, will remain with their respective players:
	numberOfBushels;
	acres;
	numberOfPeople;
	hasGameEnded;
	}
//Assumption -- a year cannot move forward unless all the players have played.
Please let me know if you would like me to implelemt the above and I will be happy to do so.

Thank You!
Shipra Shah
7734580321
