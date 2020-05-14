package id315000539_id318353356_id208722710;

import java.util.Scanner;
import java.util.Vector;

public class Main {
	//problems:

	//2. each object that is not citizen gets 2 ballots even though in citizen
	//   it is private and not supose to go..

	public static void main(String[] args) throws ageOutOfRange, IDOutOfRange {
		
		Scanner scan = new Scanner(System.in);
		int choise;
		ElectionRound firstRound = new ElectionRound(9, 2020); // election has a date
		hardCode(firstRound);

		// menu:
		do {
			menu();
			choise = scan.nextInt();
			switch (choise) {
			default:
				System.out.println("You have pressed a wrong key");
			case ADD_A_BALLOT:
				firstRound.addABallot(scan);
				break;
			case ADD_A_CITIZEN:
				firstRound.addACitizen(scan);
				break;
			case ADD_A_PARTY:
				firstRound.addAParty(scan);
				break;
			case ADD_CITIZEN_AFFILIATED_WITH_A_PARTY:
				firstRound.addACandidateToParty(scan);
				break;
			case SHOW_ALL_BALLOTS:
				firstRound.showAllBallots();
				break;
			case SHOW_ALL_CITIZENS:
				firstRound.showAllCitizens();
				break;
			case SHOW_ALL_PARTIES:
				firstRound.showAllParties();
				break;
			case ELECTIONS:
				if (!firstRound.getHasHappened()) {
					firstRound.elections(scan);
					firstRound.setHasHappened(true);
				} else
					System.out.println("The voting has already happened. please press 9 to see results");
				break;
			case SHOW_ELECTION_RESULTS:
				if (!firstRound.getHasHappened()) {
					System.out.println("Can not show results until election started\n");
				} else {
					firstRound.ShowElectionResults();
				}
				break;
			case EXIT:
				System.out.println("You have chose to exit, have a good day");
			}
		} while (choise != EXIT);
		scan.close();
	}

	public static final int ADD_A_BALLOT = 1;
	public static final int ADD_A_CITIZEN = 2;
	public static final int ADD_A_PARTY = 3;
	public static final int ADD_CITIZEN_AFFILIATED_WITH_A_PARTY = 4;
	public static final int SHOW_ALL_BALLOTS = 5;
	public static final int SHOW_ALL_CITIZENS = 6;
	public static final int SHOW_ALL_PARTIES = 7;
	public static final int ELECTIONS = 8;
	public static final int SHOW_ELECTION_RESULTS = 9;
	public static final int EXIT = 10;

	public static void menu() {
		System.out.println("Hello and welcome to the elections program");
		System.out.println(ADD_A_BALLOT + ") To add a ballot, please press " + ADD_A_BALLOT);
		System.out.println(ADD_A_CITIZEN + ") To add a citizen to registered citizens, please press " + ADD_A_CITIZEN);
		System.out.println(ADD_A_PARTY
				+ ") To add a party to registered parties compiting in this elecion , please press " + ADD_A_PARTY);
		System.out.println(ADD_CITIZEN_AFFILIATED_WITH_A_PARTY
				+ ") To add a candidate (sick or not) to registered party, please press " + ADD_CITIZEN_AFFILIATED_WITH_A_PARTY);
		System.out.println(SHOW_ALL_BALLOTS + ") To show all ballots, please press " + SHOW_ALL_BALLOTS);
		System.out.println(SHOW_ALL_CITIZENS + ") To show all citizents registered, please press " + SHOW_ALL_CITIZENS);
		System.out.println(SHOW_ALL_PARTIES + ") To show all parties registered, please press " + SHOW_ALL_PARTIES);
		System.out.println(ELECTIONS + ") To start the elections, please press " + ELECTIONS);
		System.out.println(
				SHOW_ELECTION_RESULTS + ") To show the elecions result, please press " + SHOW_ELECTION_RESULTS);
		System.out.println(EXIT + ") To exit the system, please press " + EXIT);
	}
	
	public static void hardCode(ElectionRound firstRound) throws ageOutOfRange, IDOutOfRange {
		Vector<Party> runningParties = new Vector<>(); // parties
		runningParties.add(new Party("Meretz", "Left", "09/08/1990"));
		runningParties.add(new Party("HaLikud", "Right", "14/05/1980"));
		runningParties.add(new Party("Kahol Lavan", "Center", "25/01/2018"));

		firstRound.setRunningParties(runningParties); // election has parties

		Set<Citizen> citizens = new Set<>();
		citizens.add(new Citizen("Yakir", 123456789, 1997));
		citizens.add(new Citizen("Ran", 303939155, 1996));
		firstRound.setCitizens(citizens); // election has citizens
		firstRound.setCitizensVoters(); //election has the voters that are citizens
		
		Set<Candidate> candidates= new Set<>();
		candidates.add(new Candidate("Benjamin Netanyahu", 251637285, 1949, runningParties.get(1)));
		candidates.add(new Candidate("Beny Gantz", 278492871, 1959,  runningParties.get(2)));
		candidates.add(new Candidate("Miri Regev", 274958637, 1959, runningParties.get(1)));
		candidates.add(new Candidate("Yair Lapid", 198365039, 1960, runningParties.get(2)));
		firstRound.setCandidatesVoters(candidates); 
		
		
		Set<SickCandidate> sickCandidates= new Set<>();
		sickCandidates.add(new SickCandidate("Amir Peretz", 362719835, 1958, runningParties.get(0),10));
		sickCandidates.add(new SickCandidate("Nitzan Horovitz", 284019372, 1962, runningParties.get(0),4));
		firstRound.setSickCandidatesVoters(sickCandidates);
		
		Set<SickCitizen> sickCitizens= new Set<>();
		sickCitizens.add(new SickCitizen("Rom", 208722719, 1997,8));
		firstRound.setSickCitizens(sickCitizens); // election has sick citizens
		firstRound.setSickCitizensVoters(); //election has the voters that are sick citizens
		
		Set<Soldier> soldiers= new Set<>();
		soldiers.add(new Soldier("Shir", 246123784, 2002,true));
		firstRound.setSoldiersVoters(soldiers); // election has soldiers + they are voters
		
		Set<SickSoldier> sickSoldiers= new Set<>();
		sickSoldiers.add(new SickSoldier("Or", 123567488, 2001,false,20));
		firstRound.setSickSoldiersVoters(sickSoldiers);// election has sick soldiers + they are voters
		
		
		Vector <Ballot <Soldier>> arrayB2= new Vector<>(); //election has ballots
		Ballot <Soldier> b2 = new Ballot <Soldier>("Beer Sheva", runningParties);
		arrayB2.add(b2);
		firstRound.setSoldierBallot(arrayB2);
		
		Vector <Ballot <SickSoldier>> arrayB4= new Vector<>();
		Ballot <SickSoldier> b4= new Ballot<SickSoldier>("Jerusalem", runningParties);
		arrayB4.add(b4);
		firstRound.setSickSoldierBallot(arrayB4);
		
		Vector <Ballot <Citizen>> arrayB3= new Vector<>();
		Ballot <Citizen> b3 = new Ballot <Citizen>("Tel aviv", runningParties);
		arrayB3.add(b3);
		firstRound.setCitizenBallot(arrayB3);
		
		Vector <Ballot <SickCitizen>> arrayB1= new Vector<>(); 	 
		Ballot <SickCitizen> b1= new Ballot<SickCitizen>("Haifa", runningParties);
		arrayB1.add(b1);
		firstRound.setSickCitizenBallot(arrayB1);
		
		Vector <Ballot <Candidate>> arrayB5 = new Vector<>();
		Ballot<Candidate> b5= new Ballot<Candidate>("Jerusalem", runningParties);
		arrayB5.add(b5);
		firstRound.setCandidatesBallot(arrayB5);
		
		Vector <Ballot <SickCandidate>> arrayB6 = new Vector<>();
		Ballot<SickCandidate> b6= new Ballot<SickCandidate>("Yaffo", runningParties);
		arrayB6.add(b6);
		firstRound.setSickCandidatesBallot(arrayB6);
		
		firstRound.setBallotsAndVoters(); // citizens get ballots && ballots get citizens
	}
}
