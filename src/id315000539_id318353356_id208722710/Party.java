package id315000539_id318353356_id208722710;

import java.util.Scanner;
import java.util.Vector;

public class Party {
	public enum Faction {
		Right, Left, Center;
	}

	private Faction side;
	private String name;
	private String date;
	private Vector<Candidate> candidateList;
	private int currentCandidates = 0;
	private int placeInParty = 1;
	private int numberOfVotes = 0;

	public Party(String name, String faction, String date) {
		this.name = name;
		setFaction(faction);
		this.date = date;
		candidateList = new Vector<>();
	}

	public Party(Scanner scan) {
		System.out.println("please enter the name of the party: ");
		scan.nextLine();
		this.name = scan.nextLine();
		System.out.println("please enter the faction of the party (Left,Right,Center): ");
		setFaction(scan.next());
		System.out.println("please enter the manufacturing date of the party: ");
		scan.nextLine();
		this.date = scan.nextLine();
		this.candidateList = new Vector<>();
	}

	private boolean setFaction(String faction) { // boolean since it says so in
													// the task
		this.side = Faction.valueOf(faction);
		return true;
	}

	public String getName() {
		return this.name;
	}

	public boolean setNumberOfVoters(int number) { // boolean since it says so
													// in the task
		this.numberOfVotes = number + this.numberOfVotes;
		return true;
	}

	public int getNumberOfVoters() {
		return numberOfVotes;
	}

	public void addCandidate(Candidate candidate) {
		candidateList.add(candidate);
		candidate.setPlaceInParty(placeInParty); // (pointing at each other)
		currentCandidates++;
		placeInParty++;
	}

	public Citizen addCandidate(Scanner scan) throws ageOutOfRange, IDOutOfRange {
		System.out.println("please enter candidate details: ");
		try {
			Candidate temp = new Candidate(scan, this);
			candidateList.add(temp);
			temp.setPlaceInParty(placeInParty);
			currentCandidates++;
			placeInParty++;
			System.out.println("the candidadate was added successfuly");
			return temp;
		} catch (ageOutOfRange notBigEnough) {
			System.out.println("not possible to add, not big enough");
			return null;
		}
	}

	public boolean equals(Party party) {
		if (party != null) {
			if (this.name.equals(party.name)) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		StringBuffer makeAstring = new StringBuffer(name + ",in faction " + side + ", was founded in " + date);
		if (currentCandidates == 0) {
			makeAstring.append(" and has no candidates.\n");
		} else
			makeAstring.append("\nThe candidates in this party:\n");
		for (int i = 0; i < candidateList.size(); i++) {
			makeAstring.append(candidateList.get(i).getName() + "\n");
		}
		return makeAstring.toString();
	}
}
