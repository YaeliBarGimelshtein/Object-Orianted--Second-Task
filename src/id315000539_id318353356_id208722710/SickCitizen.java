package id315000539_id318353356_id208722710;

import java.util.Scanner;

public class SickCitizen extends Citizen {
	private int numOfSickDays;

	public SickCitizen(String name, int ID, int year, boolean isQuarantine,int days) throws IDOutOfRange {
		super(name, ID, year, isQuarantine);
		setNumOfSickDays(days);
	}
	
	public SickCitizen(Citizen citizen,int days)throws ageOutOfRange {
			super(citizen);
			setNumOfSickDays(days);
		}
	
	public SickCitizen(Scanner scan)throws IDOutOfRange {
		super(scan);
		System.out.println("please let us know how many days you have been sick:");
		this.numOfSickDays=scan.nextInt();
	}

	private void setNumOfSickDays(int days) {
		if(days>=0) {
			numOfSickDays=days;
		}
		else {
			numOfSickDays=0;
		}
	}
	
	public int getNumOfSickDays() {
		return this.numOfSickDays;
	}
	
	public String toString() {
		return super.toString()+"he is also sick for "+this.numOfSickDays+" days";
	}
	
	public boolean equals(SickCitizen other) {
		if(numOfSickDays==other.numOfSickDays&& super.equals(other)) {
			return true;
		}
		return false ;
	}
}
