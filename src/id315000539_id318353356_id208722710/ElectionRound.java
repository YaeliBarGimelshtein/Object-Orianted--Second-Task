package id315000539_id318353356_id208722710;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class ElectionRound implements Menuable {
	public static int ELECTION_YEAR;

	private boolean hasHappened;

	private int electionMonth;
	private int electionYear;

	private Vector<Citizen> voters;
	private Vector<Party> runningParties;
	private Vector<Ballot> ballots;
	private Vector<Citizen> citizens;

	private int ActualNumberOfVoters = 0;
	private int numberOfrunningParties = 0;
	private int numberOfBallots = 0;
	private int numberOfCitizens = 0;
	private int numberOfvoters = 0;

	public ElectionRound(int electionMonth, int electionYear) {
		setElectionMonth(electionMonth);
		setElectionYear(electionYear);
		ELECTION_YEAR = electionYear;
		voters = new Vector<>();
		runningParties = new Vector<>();
		ballots = new Vector<>();
		citizens = new Vector<>();
	}

	public boolean setCitizens(Vector<Citizen> citizens) { // boolean since it
															// says so in the
															// task
		for (int i = 0; i < citizens.size(); i++) {
			this.citizens.add(citizens.get(i));
			numberOfCitizens++;
		}
		return true;
	}

	public boolean setVoter(Citizen citizen) {
		try {
			Citizen temp = new Citizen(citizen);
			this.voters.add(temp);
			this.numberOfvoters++;
		} catch (ageOutOfRange notBigEnough) {
			return false;
		}
		return true;
	}

	public List<Citizen> getVoters() {
		return this.voters;
	}

	public void setVoters(Vector<Citizen> voters) {
		this.voters = voters;

	}

	public boolean setRunningParties(Vector<Party> runningParties) { // boolean
																		// since
																		// it
																		// says
																		// so in
																		// the
																		// task
		for (int i = 0; i < runningParties.size(); i++) {
			this.runningParties.add(runningParties.get(i));
			this.numberOfrunningParties++;
		}
		return true;
	}

	public boolean setBallots(Vector<Ballot> ballots) { // boolean since it says
														// so in the task
		for (int i = 0; i < ballots.size(); i++) {
			this.ballots.add(ballots.get(i));
			this.numberOfBallots++;
		}
		return true;
	}

	public boolean setElectionMonth(int electionMonth) { // boolean since it
															// says so in the
															// task
		if (this.electionMonth > 12 || electionMonth < 1) {
			this.electionMonth = 1;
			return false;
		} else
			this.electionMonth = electionMonth;
		return true;
	}

	public boolean setElectionYear(int electionYear) { // boolean since it says
														// so in the task
		if (electionYear <= 0) {
			this.electionYear = 2020;
			return false;
		} else
			this.electionYear = electionYear;
		return true;
	}

	public int getElectionYear() {
		return this.electionYear;
	}

	public boolean setHasHappened(boolean happened) {
		this.hasHappened = happened;
		return true;
	}

	public boolean getHasHappened() {
		return this.hasHappened;
	}

	public String toString() {
		return "Election Round on " + electionMonth + "/" + electionYear;
	}

	public boolean equals(ElectionRound other) {
		return this.electionMonth == other.electionMonth && this.electionYear == other.electionYear;
	}

	public boolean setBallotsAndCitizens(Vector<Citizen> voters, Vector<Ballot> ballots) throws ageOutOfRange { // boolean
																												// since
																												// it
																												// says
																												// so
																												// in
																												// the
																												// task
		for (int i = 0; i < voters.size(); i++) {
			for (int j = 0; j < ballots.size(); j++) {
				if (ballots.get(j).belongs(voters.get(i))) {
					voters.get(i).setBallot(ballots.get(j));
					break;
				}
			}
		}
		return true;
	}

	public boolean setBallotAndASingleCitizen(Scanner scan, Vector<Ballot> ballots) { // boolean
																						// since
																						// it
																						// says
																						// so
																						// in
																						// the
																						// task
		Citizen temp = new Citizen(scan);
		citizens.add(temp);
		try {
			for (int i = 0; i < ballots.size(); i++) {
				if (ballots.get(i).belongs(temp)) {
					temp.setBallot(ballots.get(i));
					voters.add(temp);
					numberOfvoters++;
					break;
				}
			}
		} catch (ageOutOfRange notBigEnough) {
			return false;
		}
		return true;
	}

	public boolean setBallotAndASingleCandidate(Candidate candidate, Vector<Ballot> ballots) throws ageOutOfRange {
		for (int i = 0; i < ballots.size(); i++) {
			if (ballots.get(i).belongs(candidate)) {
				candidate.setBallot(ballots.get(i));
				voters.add(candidate);
				numberOfvoters++;
				break;
			}
		}
		return true;
	}

	private double calculateTotalVotingPercent() {
		if (numberOfvoters == 0) {
			return 0;
		} else {
			double votingPercent = ((double) ActualNumberOfVoters) / ((double) numberOfvoters) * 100;
			votingPercent = votingPercent * 100;
			votingPercent = Math.round(votingPercent);
			votingPercent = votingPercent / 100;
			return votingPercent;
		}
	}

	private int calculateActualVoters() {
		for (int i = 0; i < voters.size(); i++) {
			if (voters.get(i).getIsVoting()) {
				ActualNumberOfVoters++;
			}
		}
		return ActualNumberOfVoters;
	}

	private boolean setNumberOfVotersPerParty() { // boolean since it says so in
													// the task
		for (int i = 0; i < ballots.size(); i++) {
			Vector<BallotsResults> temp = ballots.get(i).getResults();
			for (int j = 0; j < temp.size(); j++) {
				for (int k = 0; k < runningParties.size(); k++) {
					if (temp.get(j).getParty().equals(runningParties.get(k))) {
						runningParties.get(k).setNumberOfVoters(temp.get(j).getVotesNumber());
					}
				}
			}
		}
		return true;
	}

	// from menu:

	public void addABallot(Scanner scan) {
		System.out.println("You have chose to add a ballot:");
		System.out.println(
				"Press 1 for Corona ballot.\nPress 2 for Military ballot." + "\nPress 3 for a regular ballot.");
		int choise = scan.nextInt();
		switch (choise) {
		case 1:
			ballots.add(new CoronaBallot(scan, runningParties));
			System.out.println("Ballot was added successfuly");
			break;
		case 2:
			ballots.add(new MilitaryBallot(scan, runningParties));
			System.out.println("Ballot was added successfuly");
			break;
		case 3:
			ballots.add(new Ballot(scan, runningParties));
			System.out.println("Ballot was added successfuly");
			break;
		default:
			System.out.println("You have pressed a wrong key");
		}
		numberOfBallots++;
	}

	public void addACitizen(Scanner scan) {
		System.out.println("You have chose to add a citizen, please enter details:");
		setBallotAndASingleCitizen(scan, this.ballots);
		numberOfCitizens++;
		System.out.println("Citizen was added successfuly");
	}

	public void addAParty(Scanner scan) {
		System.out.println("You have chose to add a party, please enter details:");
		runningParties.add(new Party(scan));
		numberOfrunningParties++;
		System.out.println("Party was added successfuly");
	}

	public void addACandidateToParty(Scanner scan) throws ageOutOfRange {
		System.out.println("You have chose to add a candidate to a party: please press the number of wanted party.");
		for (int i = 0; i < runningParties.size(); i++) {
			System.out.println((i + 1) + "--> " + runningParties.get(i).getName());
		}
		int choise;
		choise = scan.nextInt();
		Candidate temp = (Candidate) runningParties.get(choise - 1).addCandidate(scan);
		if (temp != null) {
			setBallotAndASingleCandidate(temp, ballots);
			citizens.add(temp);
			numberOfCitizens++;
		}
	}

	public void showAllBallots() {
		System.out.println("Here are all the ballots in this election:\n");
		for (int i = 0; i < numberOfBallots; i++) {
			System.out.println(ballots.get(i));
		}
		System.out.println();
	}

	public void showAllCitizens() {
		System.out.println("Here are all the citizens in this election:\n");
		for (int i = 0; i < numberOfCitizens; i++) {
			System.out.println(citizens.get(i));
		}
		System.out.println();
	}

	public void showAllParties() {
		System.out.println("Here are all the parties in this election:\n");
		for (int i = 0; i < numberOfrunningParties; i++) {
			System.out.println(runningParties.get(i));
		}
		System.out.println();
	}

	public void elections(Scanner scan) {
		System.out.println("You have chose to start the voting");
		for (int i = 0; i < numberOfvoters; i++) {
			voters.get(i).vote(scan, runningParties);
		}
		System.out.println("The voting is done\n");
	}

	public void ShowElectionResults() {
		System.out.println(this.toString() + " is over.");
		System.out.println("The number of voters is " + calculateActualVoters() + " out of " + this.numberOfvoters
				+ " Potential voters. " + "The voting percent of the election round: " + calculateTotalVotingPercent()
				+ "%\n");
		for (int i = 0; i < numberOfBallots; i++) {
			ballots.get(i).showResults();
		}
		setNumberOfVotersPerParty();
		System.out.println("The number of votes that each party got is: ");
		for (int i = 0; i < numberOfrunningParties; i++) {
			System.out.println("The party: " + runningParties.get(i).getName() + " got "
					+ runningParties.get(i).getNumberOfVoters() + " votes.");
		}
		System.out.println();
	}
}