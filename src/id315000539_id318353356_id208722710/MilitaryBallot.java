package id315000539_id318353356_id208722710;

import java.util.List;
import java.util.Scanner;

public class MilitaryBallot extends Ballot {
	public MilitaryBallot(String address,  List<Party> parties) {
		super(address, parties);
	}
	
	public MilitaryBallot(Scanner scan,  List<Party> parties) {
		super(scan, parties);
	}
	
	public boolean belongs(Citizen voter) {
		if (voter.getAge()<22 &&super.belongs(voter)) {
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

