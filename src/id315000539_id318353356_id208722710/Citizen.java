package id315000539_id318353356_id208722710;

import java.util.List;
import java.util.Scanner;

public class Citizen {
	protected String name;
	protected int ID;
	protected int birthYear;
	protected boolean isQuarantine;
	protected Ballot ballot;
	protected int age;
	protected boolean isVoting;

	public Citizen(String name, int ID, int year, boolean isQuarantine)  {
		this.name = name;
		setID(ID);
		setYear(year);
		this.isQuarantine = isQuarantine;
		setAge();
	}

	public Citizen(Scanner scan) {
		System.out.println("please enter the citizen's name:");
		scan.nextLine();
		this.name = scan.nextLine();
		System.out.println("please enter the citizen's ID:");
		this.ID = scan.nextInt();
		System.out.println("please enter the citizen's birth year:");
		scan.nextLine();
		this.birthYear = scan.nextInt();
		System.out.println("please enter if the citizen is in quarantine (true/false):");
		scan.nextLine();
		this.isQuarantine = scan.nextBoolean();
		setAge();
	}

	public Citizen(Citizen citizen) throws ageOutOfRange {
		this.name = citizen.name;
		setID(citizen.ID);
		setYear(citizen.getYear());
		this.isQuarantine = citizen.isQuarantine;
		setAge();
		if (this.age < 18) {
			throw new ageOutOfRange("Not legal to vote yet");
		}
	}

	private boolean setAge() { //boolean since it says so in the task
		this.age = ElectionRound.ELECTION_YEAR - this.birthYear;
		return true;
	}

	public int getAge() {
		return this.age;
	}

	public Ballot getBallot() {
		return this.ballot;
	}

	public boolean setBallot(Ballot ballot) throws ageOutOfRange { //boolean since it says so in the task
		if (this.age <= 18) {
			throw new ageOutOfRange("Not legal to vote yet");
		}else {
		this.ballot = ballot;
		ballot.addVoter(this); // (pointing at each other)
		return true;
		}
	}

	public int getID() {
		return this.ID;
	}

	public int getYear() {
		return this.birthYear;
	}

	public boolean getIsQuarantine() {
		return this.isQuarantine;
	}

	private boolean setYear(int year) { //boolean since it says so in the task
		if (year > 0 && year < 2021) {
			this.birthYear = year;
			return true;
		} else {
			this.birthYear = 0;
			return false;
		}
	}

	private boolean setID(int iD) { //boolean since it says so in the task
		if (iD > 0) {
			this.ID = iD;
			return true;
		} else {
			this.ID = 0;
			return false;
		}
	}

	public String getName() {
		return this.name;
	}

	public boolean getIsVoting() {
		return this.isVoting;
	}

	public boolean equals(Citizen citizen) {
		if (this.ID == citizen.ID) {
			return true;
		}
		return false;
	}

	public void vote(Party selectedParty) {
		this.ballot.vote(selectedParty, this);
	}

	public void vote(Scanner scan, List <Party> parties) {
		System.out.println("Citizen: " + this.name + " ID: " + this.ID + " do you want to vote? Y for yes/N for no: ");
		if (scan.next().toUpperCase().charAt(0) == 'Y') {
			if (this.isQuarantine == true) {
				System.out.println("Do you have a protective suit? Y for yes/N for no: ");
				if (scan.next().toUpperCase().charAt(0) == 'N') {
					System.out.println("We are very sorry, you can't vote");
					return;
				}
			}
			System.out.println("You are voting in : "+ this.ballot);
			System.out.println("choose a party from the list: ");
			for (int i = 0; i < parties.size(); i++) {
					System.out.println((i + 1) + "--> " + parties.get(i).getName());
			}
			int choise = scan.nextInt();
			this.vote(parties.get(choise-1));
			isVoting = true;
		} else {
			System.out.println("thank you, have a nice day!");
		}
	}

	public String toString() {
		String str = name + " is " + age + " ,ID=" + ID + ", born in " + birthYear + ", is in Quarantine="
				+ isQuarantine;
		if (ballot != null) {
			str = str + ", votes at ballot number " + ballot.getId()+ ". ";
		}
		return str;
	}
}
