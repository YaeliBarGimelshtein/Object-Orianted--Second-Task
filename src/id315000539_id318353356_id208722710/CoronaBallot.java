package id315000539_id318353356_id208722710;

import java.util.List;
import java.util.Scanner;

public class CoronaBallot extends Ballot {
	public CoronaBallot(String address, List<Party> parties) {
		super(address, parties);
	}

	public CoronaBallot(Scanner scan, List<Party> parties) {
		super(scan, parties);
	}

	public void addVoter(Citizen voter) {
		super.addVoter(voter);
	}

	public boolean belongs(Citizen voter) {
		if (voter.getIsQuarantine() == true && super.belongs(voter)) {
			return true;
		}
		return false;
	}

	public String toString() {
		return "Corona " + super.toString();
	}

	public boolean equals(Ballot other) {
		return (super.equals(other));
	}
}

