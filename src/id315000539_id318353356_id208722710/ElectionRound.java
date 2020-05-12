package id315000539_id318353356_id208722710;

import java.util.Scanner;
import java.util.Vector;

public class ElectionRound implements Menuable {
	public static int ELECTION_YEAR;

	private boolean hasHappened;

	private int electionMonth;
	private int electionYear;

	private Set<Citizen> citizens;
	private Set <SickCitizen> sickCitizens;
	
	private Vector <Citizen> citizensVoters;
	private Vector<Soldier> SoldiersVoters;
	private Vector<SickCitizen> SickCitizensVoters;
	private Vector<SickSoldier> SickSoldiersVoters;
	
	
	private Vector<Party> runningParties;
	//private Vector<Ballot> ballots;
	private Vector <Ballot <Citizen>> citizenBallot;
	private Vector <Ballot <SickCitizen>> sickCitizenBallot;
	private Vector <Ballot <Soldier>> soldierBallot;
	private Vector <Ballot <SickSoldier>> sickSoldierBallot;
	

	private int ActualNumberOfVoters = 0; //this not delete
	private int numberOfvoters = 0;

	public ElectionRound(int electionMonth, int electionYear) {
		setElectionMonth(electionMonth);
		setElectionYear(electionYear);
		ELECTION_YEAR = electionYear;
		citizens = new Set<>();
		SoldiersVoters= new Vector<>();
		SickCitizensVoters= new Vector<>();
		SickSoldiersVoters= new Vector<>();
		runningParties = new Vector<>();
		sickCitizens=new Set<>();
		citizensVoters=new Vector<>();
	}

	public boolean setCitizens(Set<Citizen> citizens) { // boolean since it says so in the task
		this.citizens=citizens;
		return true;
	}
	
	public boolean setSickCitizens(Set<SickCitizen> SickCitizen) { // boolean since it says so in the task
		this.sickCitizens=SickCitizen;
		return true;
	}
	
	public boolean setSoldiersVoters(Set<Soldier> Soldiers) { // boolean since it says so in the task
		for (int i = 0; i < Soldiers.getSetLenght(); i++) {
			this.SoldiersVoters.add(Soldiers.getObjectAtIndex(i));
			numberOfvoters++;
		}
		return true;
	}
	
	
	public boolean setSickSoldiersVoters(Set<SickSoldier> SickSoldiers) { // boolean since it says so in the task
		for (int i = 0; i < SickSoldiers.getSetLenght(); i++) {
			this.SickSoldiersVoters.add(SickSoldiers.getObjectAtIndex(i));
			numberOfvoters++;
		}
		return true;
	}
	
	public boolean setCitizensVoters() {
		for (int i = 0; i < citizens.getSetLenght(); i++) {
			try {
				checkVotingException(citizens.getObjectAtIndex(i));
				this.citizensVoters.add(citizens.getObjectAtIndex(i));
				numberOfvoters++;
			} catch (ageOutOfRange notBigEnough) {
				return false;
			}
		}
		return true;
	}
	
	public boolean setSickCitizensVoters() {
		for (int i = 0; i < sickCitizens.getSetLenght(); i++) {
			try {
				checkVotingException(sickCitizens.getObjectAtIndex(i));
				this.SickCitizensVoters.add(sickCitizens.getObjectAtIndex(i));
				numberOfvoters++;
			} catch (ageOutOfRange notBigEnough) {
				return false;
			}
		}
		return true;
	}	
	
	
	public <T extends Citizen> void checkVotingException (T object) throws ageOutOfRange {
		if (object.getAge() < 18) {
			throw new ageOutOfRange("Not legal to vote yet");
		}
	}



	

	public boolean setRunningParties(Vector<Party> runningParties) { // boolean since it says so in the task
		this.runningParties=runningParties;
		return true;
	}


	public void setCitizenBallot(Vector <Ballot<Citizen>> citizenBallot) {
		this.citizenBallot = citizenBallot;
	}

	public void setSickCitizenBallot(Vector<Ballot<SickCitizen>> sickCitizenBallot) {
		this.sickCitizenBallot = sickCitizenBallot;
	}

	public void setSoldierBallot(Vector<Ballot<Soldier>> soldierBallot) {
		this.soldierBallot = soldierBallot;
	}

	public void setSickSoldierBallot(Vector<Ballot<SickSoldier>> sickSoldierBallot) {
		this.sickSoldierBallot = sickSoldierBallot;
	}

	public boolean setElectionMonth(int electionMonth) { // boolean since it says so in the task
		if (this.electionMonth > 12 || electionMonth < 1) {
			this.electionMonth = 1;
			return false;
		} else
			this.electionMonth = electionMonth;
		return true;
	}

	public boolean setElectionYear(int electionYear) { // boolean since it says so in the task
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

	public void setHasHappened(boolean happened) {
		this.hasHappened = happened;
	}

	public boolean getHasHappened() {
		return this.hasHappened;
	}

	public String toString() {
		return "Election Round on " + electionMonth + "/" + electionYear;
	}
	
	@Override
	public boolean equals(Object obj) {
		ElectionRound other = (ElectionRound) obj;
		return (electionMonth== other.electionMonth && electionYear != other.electionYear);
	}

	public boolean setBallotsAndVoters() throws ageOutOfRange {
		// boolean since it says so in the task
		//no need for try and catch sicne this function only fir hard code
		for (int i = 0; i < citizensVoters.size(); i++) {
			citizensVoters.get(i).setBallot(citizenBallot.get(0));
			citizenBallot.get(0).addVoter(citizensVoters.get(i));
		}
		
		for (int i = 0; i < SickCitizensVoters.size(); i++) {
			SickCitizensVoters.get(i).setBallot(sickCitizenBallot.get(0));
			sickCitizenBallot.get(0).addVoter(SickCitizensVoters.get(i));
		}
		
		for (int i = 0; i < SoldiersVoters.size(); i++) {
			SoldiersVoters.get(i).setBallot(soldierBallot.get(0));
			soldierBallot.get(0).addVoter(SoldiersVoters.get(i));
		}
		
		for (int i = 0; i < SickSoldiersVoters.size(); i++) {
			SickSoldiersVoters.get(i).setBallot(sickSoldierBallot.get(0));
			sickSoldierBallot.get(0).addVoter(SickSoldiersVoters.get(i));
		}
		return true;
	}

	public boolean setBallotAndASingleCitizen(Scanner scan)throws ageOutOfRange, IDOutOfRange { // boolean since it says so in the task																								
		boolean notSamePerson;
		Citizen temp = new Citizen(scan);
		notSamePerson = citizens.add(temp);
		if (!notSamePerson) {
			System.out.println("Not able to add since this person is already registered");
		} else {
			System.out.print("Citizen was added successfuly");
			try {
				checkVotingException(temp);
				matchBallotAndCitizen(temp);
			} catch (ageOutOfRange notBigEnough) {
				return false;
			}
		}
		return true;
	}
	

	private void matchBallotAndCitizen(Citizen c) throws ageOutOfRange {
		if (c.getClass() == Soldier.class) {
			c.setBallot(this.soldierBallot.get(0));
			SoldiersVoters.add((Soldier) c);
		} else if (c.getClass() == SickSoldier.class) {
			c.setBallot(this.sickSoldierBallot.get(0));
			SickSoldiersVoters.add((SickSoldier) c);
		} else if (c.getClass() == Citizen.class || c.getClass() ==Candidate.class) {
			c.setBallot(this.citizenBallot.get(0));
			citizensVoters.add(c);
		} else if (c.getClass() == SickCitizen.class) {
			c.setBallot(this.sickCitizenBallot.get(0));
			sickCitizens.add((SickCitizen) c);
		}
	}

	public boolean setBallotAndASingleCandidate(Candidate candidate) throws ageOutOfRange {
		matchBallotAndCitizen(candidate);
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
		for (int i = 0; i < citizens.getSetLenght(); i++) {
			if (citizens.getObjectAtIndex(i).getIsVoting()) {
				ActualNumberOfVoters++;
			}
		}
		for (int i = 0; i < sickCitizens.getSetLenght(); i++) {
			if (sickCitizens.getObjectAtIndex(i).getIsVoting()) {
				ActualNumberOfVoters++;
			}
		}
		for (int i = 0; i < SoldiersVoters.size(); i++) {
			if (SoldiersVoters.get(i).getIsVoting()) {
				ActualNumberOfVoters++;
			}
		}
		for (int i = 0; i < SickSoldiersVoters.size(); i++) {
			if (SickSoldiersVoters.get(i).getIsVoting()) {
				ActualNumberOfVoters++;
			}
		}
		return ActualNumberOfVoters;
	}

	private boolean setNumberOfVotersPerParty() { // boolean since it says so in the task
		for (int i = 0; i < citizenBallot.size(); i++) {
			Vector<BallotsResults> temp = citizenBallot.get(i).getResults();
			for (int j = 0; j < temp.size(); j++) {
				for (int k = 0; k < runningParties.size(); k++) {
					if (temp.get(j).getParty().equals(runningParties.get(k))) {
						runningParties.get(k).setNumberOfVoters(temp.get(j).getVotesNumber());
					}
				}
			}
		}
		for (int i = 0; i < sickCitizenBallot.size(); i++) {
			Vector<BallotsResults> temp = sickCitizenBallot.get(i).getResults();
			for (int j = 0; j < temp.size(); j++) {
				for (int k = 0; k < runningParties.size(); k++) {
					if (temp.get(j).getParty().equals(runningParties.get(k))) {
						runningParties.get(k).setNumberOfVoters(temp.get(j).getVotesNumber());
					}
				}
			}
		}
		for (int i = 0; i < soldierBallot.size(); i++) {
			Vector<BallotsResults> temp = soldierBallot.get(i).getResults();
			for (int j = 0; j < temp.size(); j++) {
				for (int k = 0; k < runningParties.size(); k++) {
					if (temp.get(j).getParty().equals(runningParties.get(k))) {
						runningParties.get(k).setNumberOfVoters(temp.get(j).getVotesNumber());
					}
				}
			}
		}
		
		for (int i = 0; i < sickSoldierBallot.size(); i++) {
			Vector<BallotsResults> temp = sickSoldierBallot.get(i).getResults();
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
				"Press 1 for Sick Citizen's ballot.\nPress 2 for Soldier's ballot." + "\nPress 3 for a Citizens and "
						+ "candidates ballot.\nPress 4 for Sick Soldier's regular ballot.");
						
		int choise = scan.nextInt();
		switch (choise) {
		case 1:
			sickCitizenBallot.add(new Ballot<SickCitizen>(scan, runningParties));
			System.out.println("Ballot was added successfuly");
			break;
		case 2:
			soldierBallot.add(new Ballot<Soldier>(scan, runningParties));
			System.out.println("Ballot was added successfuly");
			break;
		case 3:
			citizenBallot.add(new Ballot<Citizen>(scan, runningParties));
			System.out.println("Ballot was added successfuly");
			break;
		case 4:
			sickSoldierBallot.add(new Ballot<SickSoldier>(scan, runningParties));
			System.out.println("Ballot was added successfuly");
			break;
		default:
			System.out.println("You have pressed a wrong key");
		}
	}

	public void addACitizen(Scanner scan) throws IDOutOfRange, ageOutOfRange {
		System.out.println("You have chose to add a citizen, please enter details:");
		boolean isAbleToVote;
		isAbleToVote=setBallotAndASingleCitizen(scan);
		if(isAbleToVote) {
			System.out.println(" and was matched with a ballot");
		}else {
			System.out.println(" and was not matched with a ballot since he is not old enough");
		}
	}

	public void addAParty(Scanner scan) {
		System.out.println("You have chose to add a party, please enter details:");
		runningParties.add(new Party(scan));
		System.out.println("Party was added successfuly");
	}

	public void addACandidateToParty(Scanner scan) throws ageOutOfRange, IDOutOfRange {
		System.out.println("You have chose to add a candidate to a party: please press the number of wanted party.");
		for (int i = 0; i < runningParties.size(); i++) {
			System.out.println((i + 1) + "--> " + runningParties.get(i).getName());
		}
		int choise;
		choise = scan.nextInt();
		Candidate temp = (Candidate) runningParties.get(choise - 1).addCandidate(scan);
		if (temp != null) {
			setBallotAndASingleCandidate(temp);
			citizens.add(temp);
		}
	}

	public void showAllBallots() {
		System.out.println("Here are all the ballots in this election:\n");
		for (int i = 0; i < citizenBallot.size(); i++) {
			System.out.println("Citizen's ballots:");
			System.out.println(citizenBallot.get(i).toString());
			System.out.println();
		}
		for (int i = 0; i < sickCitizenBallot.size(); i++) {
			System.out.println("Sick Citizens Ballots:");
			System.out.println(sickCitizenBallot.get(i).toString());
			System.out.println();
		}
		for (int i = 0; i < soldierBallot.size(); i++) {
			System.out.println("Soldiers Ballots:");
			System.out.println(soldierBallot.get(i).toString());
			System.out.println();
		}
		
		for (int i = 0; i < sickSoldierBallot.size(); i++) {
			System.out.println("Sick Soldiers ballots:");
			System.out.println(sickSoldierBallot.get(i).toString());
		}
		System.out.println();
	}

	public void showAllCitizens() {
		System.out.println("Here are all the citizens in this election:\n");
		for (int i = 0; i < citizens.getSetLenght(); i++) {
			System.out.println(citizens.getObjectAtIndex(i));
		}
		for (int i = 0; i < sickCitizens.getSetLenght(); i++) {
			System.out.println(sickCitizens.getObjectAtIndex(i));
		}
		for (int i = 0; i < SoldiersVoters.size(); i++) {
			System.out.println(SoldiersVoters.get(i));
		}
		for (int i = 0; i < SickSoldiersVoters.size(); i++) {
			System.out.println(SickSoldiersVoters.get(i));
		}
		System.out.println();
	}

	public void showAllParties() {
		System.out.println("Here are all the parties in this election:\n");
		for (int i = 0; i < runningParties.size(); i++) {
			System.out.println(runningParties.get(i));
		}
		System.out.println();
	}

	public void elections(Scanner scan) {
		System.out.println("You have chose to start the voting");
		for (int i = 0; i < citizensVoters.size(); i++) {
			citizensVoters.get(i).vote(scan, runningParties);
		}
		for (int i = 0; i < SoldiersVoters.size(); i++) {
			SoldiersVoters.get(i).vote(scan, runningParties);
		}
		for (int i = 0; i < SickCitizensVoters.size(); i++) {
			SickCitizensVoters.get(i).vote(scan, runningParties);
		}
		for (int i = 0; i < SickSoldiersVoters.size(); i++) {
			SickSoldiersVoters.get(i).vote(scan, runningParties);
		}
		System.out.println("The voting is done\n");
	}

	public void ShowElectionResults() {
		System.out.println(this.toString() + " is over.");
		System.out.println("The number of voters is " + calculateActualVoters() + " out of " + this.numberOfvoters
				+ " Potential voters. " + "The voting percent of the election round: " + calculateTotalVotingPercent()
				+ "%\n");
		for (int i = 0; i < citizenBallot.size(); i++) {
			citizenBallot.get(i).showResults();	
		}
		for (int i = 0; i < sickCitizenBallot.size(); i++) {
			sickCitizenBallot.get(i).showResults();
		}
		for (int i = 0; i < soldierBallot.size(); i++) {
			soldierBallot.get(i).showResults();
			
		}
		for (int i = 0; i < sickSoldierBallot.size(); i++) {
			sickSoldierBallot.get(i).showResults();
		}
		setNumberOfVotersPerParty();
		System.out.println("The number of votes that each party got is: ");
		for (int i = 0; i < runningParties.size(); i++) {
			System.out.println("The party: " + runningParties.get(i).getName() + " got "
					+ runningParties.get(i).getNumberOfVoters() + " votes.");
		}
		System.out.println();
	}
}