package id315000539_id318353356_id208722710;

import java.util.Scanner;
import java.util.Vector;

public class Main {
	public static void main(String[] args) throws ageOutOfRange, IDOutOfRange {
		Scanner scan = new Scanner(System.in);
		int choise;

		// hard code:
		ElectionRound firstRound = new ElectionRound(9, 2020); // election has a
																// date

		Vector<Party> runningParties = new Vector<>(); // parties
		runningParties.add(new Party("Meretz", "Left", "09/08/1990"));
		runningParties.add(new Party("HaLikud", "Right", "14/05/1980"));
		runningParties.add(new Party("Kahol Lavan", "Center", "25/01/2018"));

		firstRound.setRunningParties(runningParties); // election has parties

		Set<Citizen> citizens = new Set<>();
		citizens.add(new Citizen("Yakir", 123456789, 1997));
		citizens.add(new Citizen("Ran", 303939155, 1996));
		citizens.add(new SickCitizen("Yaeli", 208722719, 1997,8));
		citizens.add(new Soldier("Shir", 246123784, 2002,true));
		citizens.add(new SickSoldier("Or", 123567488, 2001,false,20));
		citizens.add(new Candidate("Benjamin Netanyahu", 251637285, 1949, runningParties.get(1)));
		citizens.add(new Candidate("Beny Gantz", 278492871, 1959,  runningParties.get(2)));
		citizens.add(new Candidate("Amir Peretz", 362719835, 1958, runningParties.get(0)));
		citizens.add(new Candidate("Miri Regev", 274958637, 1959, runningParties.get(1)));
		citizens.add(new Candidate("Yair Lapid", 198365039, 1960, runningParties.get(2)));
		citizens.add(new Candidate("Nitzan Horovitz", 284019372, 1962, runningParties.get(0)));

		firstRound.setCitizens(citizens); // election has citizens
		Vector<Citizen> voters = new Vector<>();
		boolean isOldEnough;
		for (int i = 0; i < citizens.getSetLenght(); i++) {
			isOldEnough = firstRound.setVoter(citizens.getObjectAtIndex(i));
			if (isOldEnough) {
				voters.add(citizens.getObjectAtIndex(i)); // election has voters
			}
		}

		Vector<Ballot> ballots = new Vector<>();
		ballots.add(new CoronaBallot("Haifa", runningParties));
		ballots.add(new MilitaryBallot("Beer Sheva", runningParties));
		ballots.add(new Ballot("Tel aviv", runningParties));

		firstRound.setBallotsAndCitizens(voters, ballots); // citizens get ballots && ballots get citizens
		firstRound.setVoters(voters);
		firstRound.setBallots(ballots); // election has ballots

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
				+ ") To add a candidate to registered party, please press " + ADD_CITIZEN_AFFILIATED_WITH_A_PARTY);
		System.out.println(SHOW_ALL_BALLOTS + ") To show all ballots, please press " + SHOW_ALL_BALLOTS);
		System.out.println(SHOW_ALL_CITIZENS + ") To show all citizents registered, please press " + SHOW_ALL_CITIZENS);
		System.out.println(SHOW_ALL_PARTIES + ") To show all parties registered, please press " + SHOW_ALL_PARTIES);
		System.out.println(ELECTIONS + ") To start the elections, please press " + ELECTIONS);
		System.out.println(
				SHOW_ELECTION_RESULTS + ") To show the elecions result, please press " + SHOW_ELECTION_RESULTS);
		System.out.println(EXIT + ") To exit the system, please press " + EXIT);
	}
}
