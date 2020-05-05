package id315000539_id318353356_id208722710;

public class IDOutOfRange extends Exception {
	private String exceptionExplenation;

	public IDOutOfRange(String exceptionExplenation ) {
		this.exceptionExplenation=exceptionExplenation;
	}

	public String toString() {
		return super.toString()+" IDOutOfRange " + exceptionExplenation;
	}
	
	public String getExplenation() {
		return exceptionExplenation;
	}
}
