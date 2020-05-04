package id315000539_id318353356_id208722710;

public class BallotsResults {
	private Party party;
	private int votesNumber;

	public BallotsResults(Party party) {
		this.party = party;
	}

	public Party getParty() {
		return this.party;
	}

	public int getVotesNumber() {
		return this.votesNumber;
	}

	public void addVote() {
		votesNumber++;

	}

	public String toString() {
		return "The party: " + this.party.getName() + " has received " + this.votesNumber + " votes\n";
	}
}

