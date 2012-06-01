package les.syntra.androidsommen.logic;

/* Ontwerp velden:
	> Name: string
	> Age: int
	> UnlockedLevelIndex: int
	> TotalTimePlayed: int
	> Player(Name, Age, ULI, TTP)
	> Player (Name, Age)
 */
public class Player {
	// Moet nog methods voor naam en leeftijd krijgen
	String Name = "";
	int Age = 0;
	int UnlockedLevelIndex = 0;
	int TotalTimePlayed = 0;
	
	public Player(String aName, int aAge, int aULI, int aTTP)
	{
		Name = aName;
		Age = aAge;
		UnlockedLevelIndex = aULI;
		TotalTimePlayed = aTTP;
	}
	public Player(String aName, int aAge)
	{
		Name = aName;
		Age = aAge;
	}
}
