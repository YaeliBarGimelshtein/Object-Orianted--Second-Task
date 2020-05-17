package id315000539_id318353356_id208722710;

import java.util.Scanner;
import java.util.Vector;

public class SickCandidate extends Candidate implements Sickable {
	private int numOfSickDays;
	private Ballot<SickCandidate> ballot;

	public SickCandidate(String name, int ID, int year, Party party, int days) throws ageOutOfRange, IDOutOfRange {
		super(name, ID, year, party);
		setNumOfSickDays(days);
	}

	public SickCandidate(Scanner scan, Party party) throws ageOutOfRange, IDOutOfRange {
		super(scan, party);
		System.out.println("please let us know how many days you have been sick:");
		this.numOfSickDays = scan.nextInt();
		System.out.println("feel better!");
	}

	public int getNumOfSickDays() {
		return numOfSickDays;
	}

	private void setNumOfSickDays(int days) {
		if (days >= 0) {
			numOfSickDays = days;
		} else {
			numOfSickDays = 1;
		}
	}

	public boolean setPlaceInParty(int placeInParty) { // boolean since it says so in the task
		this.placeInParty = placeInParty;
		return true;
	}

	public String toString() {
		String str = super.toString() + ". He is also sick for " + this.numOfSickDays + " days";
		if (ballot != null) {
			str = str + ", votes at ballot number " + ballot.getId() + ". ";
		}
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		SickCandidate other = (SickCandidate) obj;
		return (numOfSickDays == other.numOfSickDays && super.equals(other));
	}

	public void vote(Party selectedParty) {
		this.ballot.vote(selectedParty, this);
	}

	public boolean setBallot(Ballot<? extends Citizen> ballot) throws ageOutOfRange { 
		try {
			checkAge();
			this.ballot = (Ballot<SickCandidate>) ballot;
			return true;
		} catch (ageOutOfRange notBigEnough) {
			return false;
		}
	}

	public void vote(Scanner scan, Vector<Party> parties) {
		System.out.println("Citizen: " + this.name + " ID: " + this.ID + " do you want to vote? Y for yes/N for no: ");
		if (scan.next().toUpperCase().charAt(0) == 'Y') {
			System.out.println("Do you have a protective suit? Y for yes/N for no: ");
			if (scan.next().toUpperCase().charAt(0) == 'N') {
				System.out.println("We are very sorry, you can't vote");
				isVoting=false;
				return;
			}
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
			isVoting=false;
		}
	}

}
