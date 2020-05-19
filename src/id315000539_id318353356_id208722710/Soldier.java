package id315000539_id318353356_id208722710;

import java.util.Scanner;
import java.util.Vector;

public class Soldier extends Citizen {
	protected boolean carryWeapon;
	private Ballot<Soldier> ballot;


	public Soldier(String name, int ID, int year,boolean weapon) throws IDOutOfRange  {
		super(name,ID,year);
		this.carryWeapon=weapon;
	}
	
	public Soldier(Scanner scan) throws IDOutOfRange,ageOutOfRange {
		super(scan);
		try {
			System.out.println("please let us know if you carry a weapon(true/false):");
			this.carryWeapon = scan.nextBoolean();
			checkAge();
		} catch (ageOutOfRange notBigEnough) {
			int year = 0;
			do {
				System.out.println("We are sorry, the age of a Soldier is less 18, please re-enter year");
				year = scan.nextInt();
			} while (ElectionRound.ELECTION_YEAR -year<18 );
			this.birthYear = year;
			setAge();
		}
	}
	
	public boolean getCarryWeapon() {
		return this.carryWeapon;
	}
	
	public String toString() {
		String str= super.toString()+"he is also a Soldier is carry a weapon "+this.carryWeapon;
		if (ballot != null) {
		str = str + ", votes at ballot number " + ballot.getId() + ". ";
		}
		return str;
	}
	
	@Override
	public boolean equals(Object obj) {
		Soldier other= (Soldier) obj;
		return (carryWeapon==other.carryWeapon && super.equals(other));
	}
	protected void checkAge() throws ageOutOfRange {
		if (this.getAge() < 18) {
			throw new ageOutOfRange("Not legal to vote yet");
		}
	}
	public void vote(Party selectedParty) {
		this.ballot.vote(selectedParty, this);
	}
	
	public boolean setBallot(Ballot<? extends Citizen> ballot) throws ageOutOfRange { // boolean since it says so un the taks
		try {
			checkAge();
			this.ballot = (Ballot<Soldier>) ballot;
			return true;
		} catch (ageOutOfRange notBigEnough) {
			return false;
		}
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
			isVoting=false;
		}
	}
}
