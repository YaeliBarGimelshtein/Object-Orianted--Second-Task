package id315000539_id318353356_id208722710;

import java.util.Scanner;

public class Soldier extends Citizen {
	private boolean carryWeapon;

	public Soldier(Citizen citizen,boolean weapon) throws ageOutOfRange {
		super(citizen);
		this.carryWeapon=weapon;
	}

	public Soldier(String name, int ID, int year, boolean isQuarantine,boolean weapon) throws IDOutOfRange  {
		super(name,ID,year,isQuarantine);
		this.carryWeapon=weapon;
	}
	
	public Soldier(Scanner scan) throws IDOutOfRange {
		super(scan);
		System.out.println("please let us know if you carry a weapon(true/false):");
		this.carryWeapon=scan.nextBoolean();
	}
	
	public boolean getCarryWeapon() {
		return this.carryWeapon;
	}
	
	public String toString() {
		return super.toString()+"he is also a Soldier is carry a weapon "+this.carryWeapon;
	}
	
	public boolean equals(Soldier other) {
		if(carryWeapon==other.carryWeapon && super.equals(other)) {
			return true;
		}
		return false ;
	}
}
