package id315000539_id318353356_id208722710;

import java.util.Scanner;
import java.util.Vector;

public class Ballot {
	public static int ID = 1;
	protected int id;
	protected String address;
	protected Vector<Citizen> votersList;
	protected double votersPercent;
	protected int numberOfActualVoters;
	protected int potentialVoters;
	protected Vector<BallotsResults> results;

	public Ballot(String address, Vector<Party> parties) {
		this.id = ID++;
		this.address = address;
		votersList = new Vector<>();
		this.results = new Vector<>();
		for (int i = 0; i < parties.size(); i++) {
			if (parties.get(i) != null) {
				results.add(new BallotsResults(parties.get(i)));
			}
		}
	}

	public Ballot(Scanner scan, Vector<Party> parties) {
		System.out.println("Please enter Ballot address");
		scan.nextLine();
		this.address = scan.nextLine();
		this.votersList = new Vector<>();
		this.results = new Vector<>();
		for (int i = 0; i < parties.size(); i++) {
			if (parties.get(i) != null) {
				results.add(new BallotsResults(parties.get(i)));
			}
		}
		this.id = ID++;
	}

	public Vector<Citizen> getvotersList() {
		return this.votersList;
	}

	public double getVotersPercent() {
		calculateVotersPercent();
		return this.votersPercent;
	}

	public Vector<BallotsResults> getResults() {
		return this.results;
	}

	public int getId() {
		return this.id;
	}

	public void addVoter(Citizen voter) {
		votersList.add(voter); // (pointing at each other)
		potentialVoters++;
		
	}

	public boolean belongs(Citizen voter) {
		if (voter.getAge() >= 18) {
			return true;
		}
		return false;
	}

	public boolean vote(Party selectedParty, Citizen voter) {
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getParty().equals(selectedParty)) {
				results.get(i).addVote();
				numberOfActualVoters++;
				return true;
			}
		}
		return false;
	}

	private void calculateVotersPercent() {
		if (potentialVoters == 0) {
			votersPercent = 0;
			return;
		}
		votersPercent = ((double) numberOfActualVoters) / ((double) potentialVoters) * 100;
		votersPercent = votersPercent * 100;
		votersPercent = Math.round(votersPercent);
		votersPercent = votersPercent / 100;
	}

	public void showResults() {
		StringBuffer str = new StringBuffer("Ballot number " + this.id + " located in " + this.address + " has "
				+ this.numberOfActualVoters + " votes out of " + this.potentialVoters
				+ " potential voters and has voting percentage of " + getVotersPercent() + "%\n");
		str.append("And the results are: \n");
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i) != null) {
				str.append(results.get(i).toString());
			}
		}
		System.out.println(str.toString());
	}

	public String toString() {
		return "Ballot number " + this.id + " located in " + this.address;
	}

	public boolean equals(Ballot other) {
		if (this.id == other.id)
			return true;
		else
			return false;
	}
}
