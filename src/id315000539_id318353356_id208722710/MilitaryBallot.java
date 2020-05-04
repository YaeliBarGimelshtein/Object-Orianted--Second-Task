package id315000539_id318353356_id208722710;


import java.util.Scanner;
import java.util.Vector;

public class MilitaryBallot extends Ballot {
	public MilitaryBallot(String address, Vector<Party> parties) {
		super(address, parties);
	}

	public MilitaryBallot(Scanner scan, Vector<Party> parties) {
		super(scan, parties);
	}

	public boolean belongs(Citizen voter) {
		if (voter.getAge() < 22 && super.belongs(voter)) {
			return true;
		}
		return false;
	}

	public void addVoter(Citizen voter) {
		super.addVoter(voter);
	}

	public String toString() {
		return "Military " + super.toString();
	}

	public boolean equals(Ballot other) {
		return (super.equals(other));
	}
}
