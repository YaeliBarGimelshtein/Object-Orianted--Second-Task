package id315000539_id318353356_id208722710;

import java.util.Scanner;
import java.util.Vector;

public class Candidate extends Citizen {
	private Ballot<Candidate> ballot;
	protected Party affiliationToParty;
	protected int placeInParty;

	public Candidate(String name, int ID, int year, Party party) throws ageOutOfRange, IDOutOfRange {
		super(name, ID, year);
		try {
		party.addCandidate(this);
		checkAge();
		this.affiliationToParty = party;
		}catch(ageOutOfRange notBigEnough) {
			throw new ageOutOfRange("Not legal to be a candidate yet");
		}
	}

	public Candidate(Scanner scan, Party party) throws ageOutOfRange, IDOutOfRange {
		super(scan);
		try {
			this.affiliationToParty = party;
			checkAge();
		}catch (ageOutOfRange notBigEnough) {
			int year = 0;
			do {
				System.out.println("We are sorry, the age of a Candidate less 18, please re-enter year");
				year = scan.nextInt();
			} while (ElectionRound.ELECTION_YEAR -year<18);
			this.birthYear = year;
			setAge();
		}
		
	}

	public Party getAffiliationToParty() {
		return affiliationToParty;
	}
	
	protected void checkAge() throws ageOutOfRange {
		if (this.getAge() < 18) {
			throw new ageOutOfRange("Not legal to vote yet");
		}
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
		String str= super.toString() + ". He is also a Candidate, his party is " + affiliationToParty.getName();
		if (ballot != null) {
			str = str + ", votes at ballot number " + ballot.getId() +".";
		}
		return str;
	}
	
	public void vote(Party selectedParty) {
		this.ballot.vote(selectedParty, this);
	}
	
	public boolean setBallot(Ballot<? extends Citizen> ballot) throws ageOutOfRange { // boolean since it says so un the taks
		try {
			checkAge();
			this.ballot = (Ballot<Candidate>) ballot;
			return true;
		} catch (ageOutOfRange notBigEnough) {
			return false;
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
			isVoting = false;
		}
	}
}
