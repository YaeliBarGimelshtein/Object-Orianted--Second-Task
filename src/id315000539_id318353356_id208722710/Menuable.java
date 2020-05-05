package id315000539_id318353356_id208722710;

import java.util.Scanner;

public interface Menuable {
	void addABallot(Scanner scan);

	void addACitizen(Scanner scan) throws IDOutOfRange;

	void addAParty(Scanner scan);

	void addACandidateToParty(Scanner scan) throws ageOutOfRange, IDOutOfRange;

	void showAllBallots();

	void showAllCitizens();

	void showAllParties();

	void elections(Scanner scan);

	void ShowElectionResults();
}
