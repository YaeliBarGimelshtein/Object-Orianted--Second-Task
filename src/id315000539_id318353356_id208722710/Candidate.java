package id315000539_id318353356_id208722710;

import java.util.Scanner;
import java.util.Vector;

public class Candidate extends Citizen {
	private Ballot<Citizen> ballot;
	private Party affiliationToParty;
	private int placeInParty;

	public Candidate(String name, int ID, int year, Party party) throws ageOutOfRange, IDOutOfRange {
		super(name, ID, year);
		this.affiliationToParty = party;
		if (this.age < 18) {
			throw new ageOutOfRange("Not legal to be a candidate yet");
		} else {
			party.addCandidate(this);
		}
	}

	public Candidate(Scanner scan, Party party) throws ageOutOfRange, IDOutOfRange {
		super(scan);
		if (this.age < 18) {
			throw new ageOutOfRange("Not legal to be a candidate yet");
		} else {
			this.affiliationToParty = party;
		}
	}

	public Party getAffiliationToParty() {
		return affiliationToParty;
	}

	public int getPlaceInParty() {
		return placeInParty;
	}

	public boolean setPlaceInParty(int placeInParty) { // boolean since it says so in the task
		this.placeInParty = placeInParty;
		return true;
	}

	@Override
	public boolean equals(Object candidate) {
		return (super.equals(candidate));
	}

	public String toString() {
		String str= super.toString() + "He is also a Candidate, his party is " + affiliationToParty.getName() + ".";
		if (ballot != null) {
			str = str + ", votes at ballot number " + ballot.getId() + ". ";
		}
		return str;
	}
	
	public void vote(Party selectedParty) {
		this.ballot.vote(selectedParty, this);
	}
	
	public boolean setBallot(Ballot<? extends Citizen> ballot) throws ageOutOfRange { // boolean since it says so un the taks
		if (this.age <= 18) {
			throw new ageOutOfRange("Not legal to vote yet");
		} else {
			this.ballot = (Ballot<Citizen>) ballot;
			//ballot.addVoter(this); // (pointing at each other)
			return true;
		}
	}
	public void vote(Scanner scan, Vector<Party> parties) {
		System.out.println("Citizen: " + this.name + " ID: " + this.ID + " do you want to vote? Y for yes/N for no: ");
		if (scan.next().toUpperCase().charAt(0) == 'Y') {
			System.out.println("You are voting in : " + this.ballot);
			System.out.println("choose a party from the list: ");
			for (int i = 0; i < parties.size(); i++) {
				System.out.println((i + 1) + "--> " + parties.get(i).getName());
			}
			int choise = scan.nextInt();
			this.vote(parties.get(choise - 1));
			isVoting = true;
		} else {
			System.out.println("thank you, have a nice day!");
		}
	}
}
