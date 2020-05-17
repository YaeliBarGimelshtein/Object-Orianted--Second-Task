package id315000539_id318353356_id208722710;

import java.util.Scanner;
import java.util.Vector;

public class Citizen {
	protected String name;
	protected int ID;
	protected int birthYear;
	private Ballot<Citizen> ballot;
	protected int age;
	protected boolean isVoting;
	protected boolean idCorrect;

	public Citizen(String name, int ID, int year) throws IDOutOfRange  {
		try {
		this.name = name;
		setID(ID);
		setYear(year);
		setAge();
		}catch(IDOutOfRange wrongID){
			throw new IDOutOfRange("Illegal ID");
		}	
	}

	public Citizen(Scanner scan) throws IDOutOfRange {
		try {
			System.out.println("please enter the citizen's name:");
			scan.nextLine();
			this.name = scan.nextLine();
			System.out.println("please enter the citizen's birth year:");
			this.birthYear = scan.nextInt();
			setAge();
			System.out.println("please enter the citizen's ID:");
			setID(scan.nextInt());
			}catch(IDOutOfRange wrongID){
				int tempId;
				do {
					System.out.println("Inccorect id. please enter 9 digit ID");
					tempId= scan.nextInt();
					idCorrect=checkId(tempId);
				} while (!idCorrect);
				this.ID=tempId;
			}
		}


	public Citizen(Citizen citizen) {
		this.name = citizen.name;
		this.ID=citizen.ID;	
		setYear(citizen.getYear());
		setAge();
	}
		

	protected boolean setAge() { // boolean since it says so in the task
		this.age = ElectionRound.ELECTION_YEAR - this.birthYear;
		return true;
	}

	public int getAge() {
		return this.age;
	}

	public Ballot<?> getBallot() {
		return this.ballot;
	}

	
	public boolean setBallot(Ballot<? extends Citizen> ballot) throws ageOutOfRange { // boolean since it says so un the
		try {
			checkAge();
			this.ballot = (Ballot<Citizen>) ballot;
			return true;
		} catch (ageOutOfRange notBigEnough) {
			return false;
		}
	}
	
	protected void checkAge() throws ageOutOfRange {
		if (this.getAge() < 18) {
			throw new ageOutOfRange("Not legal to vote yet");
		}
	}

	public int getID() {
		return this.ID;
	}

	public int getYear() {
		return this.birthYear;
	}


	private boolean setYear(int year) { // boolean since it says so in the task
		if (year > 0 && year < 2021) {
			this.birthYear = year;
			return true;
		} else {
			this.birthYear = 0;
			return false;
		}
	}

	private boolean setID(int iD)throws IDOutOfRange { // boolean since it says so in the task
		if(iD<100000000||iD>999999999) {
			throw new IDOutOfRange("Illegal ID");
		}else {
		this.ID=iD;
		return true;
		}
	}
	private boolean checkId(int idTemp) {
		if(idTemp<100000000||idTemp>999999999) {
			return false;
		}
		return true;
	}

	public String getName() {
		return this.name;
	}

	public boolean getIsVoting() {
		return this.isVoting;
	}

	
	public void vote(Party selectedParty) {
		this.ballot.vote(selectedParty, this);
	}
	 

	@Override
	public boolean equals(Object obj) {
		Citizen other = (Citizen) obj;
		if (this.ID != other.ID) {
			return false;
		}
		return true;
	}

	public void vote(Scanner scan, Vector<Party> parties) {
		System.out.println("Citizen: " + this.name + " ID: " + this.ID + " do you want to vote? Y for yes/N for no: ");
		if (scan.next().toUpperCase().charAt(0) == 'Y') {
			System.out.println("You are voting in : " + this.ballot);
			System.out.println("choose a party from the list: ");
			for (int i = 0; i < parties.size(); i++) {
				System.out.println((i + 1) + "--> " + parties.get(i).getName());
			}
			int choise = scan.nextInt();
			this.vote(parties.get(choise - 1));
			isVoting = true;
		} else {
			System.out.println("thank you, have a nice day!");
			isVoting = false;
		}
	}

	public String toString() {
		String str = name + " is " + age + " ,ID=" + ID + ", born in " + birthYear ;
		if (ballot != null) {
			str = str + ", votes at ballot number " + ballot.getId() + ". ";
		}
		return str;
	}
}
