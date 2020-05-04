package id315000539_id318353356_id208722710;

import java.util.Scanner;

public class Candidate extends Citizen {
	private Party affiliationToParty;
	private int placeInParty;

	public Candidate(String name, int ID, int year, boolean isQuarantine, Party party) throws ageOutOfRange {
		super(name, ID, year, isQuarantine);
		this.affiliationToParty = party;
		if (this.age < 18) {
			throw new ageOutOfRange("Not legal to be a candidate yet");
		} else {
			party.addCandidate(this);
		}
	}

	public Candidate(Scanner scan, Party party) throws ageOutOfRange {
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

	public boolean setPlaceInParty(int placeInParty) { // boolean since it says
														// so in the task
		this.placeInParty = placeInParty;
		return true;
	}

	public boolean equals(Candidate candidate) {
		if (this.getID() == candidate.getID()) {
			return true;
		}
		return false;
	}

	public String toString() {
		return super.toString() + "He is also a Candidate, his party is " + affiliationToParty.getName() + ".";
	}
}
