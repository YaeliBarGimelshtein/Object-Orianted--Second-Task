package id315000539_id318353356_id208722710;

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Military_Corona_Ballot extends Ballot {
	public Military_Corona_Ballot(String address, Vector<Party> parties) {
		super(address, parties);
	}

	public Military_Corona_Ballot(Scanner scan, Vector<Party> parties) {
		super(scan, parties);
	}

	public void addVoter(Citizen voter) {
		super.addVoter(voter);
	}

	public boolean belongs(Citizen voter) {
		if (voter.getIsQuarantine() == true && voter.getAge() < 22 && voter.getAge() > 17 && super.belongs(voter)) {
			return true;
		}
		return false;
	}

	public String toString() {
		return "Military-Corona " + super.toString();
	}

	public boolean equals(Ballot other) {
		return (super.equals(other));
	}
}
