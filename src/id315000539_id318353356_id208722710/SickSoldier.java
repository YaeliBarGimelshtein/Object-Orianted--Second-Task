package id315000539_id318353356_id208722710;

import java.util.Scanner;

public class SickSoldier extends Soldier {
	
	private int numOfSickDays;

	public SickSoldier(Citizen citizen,boolean weapon,int days) {
		super(citizen,weapon);
		numOfSickDays=days;
	}

	public SickSoldier(String name, int ID, int year,boolean weapon,int days) throws IDOutOfRange  {
		super(name,ID,year,weapon);
		numOfSickDays=days;

	}
	
	public SickSoldier(Scanner scan) throws IDOutOfRange {
		super(scan);
		System.out.println("please let us know how many days you are sick:");
		numOfSickDays=scan.nextInt();
	}
	
	public String toString() {
		return super.toString()+"he is also sick";
	}

}
