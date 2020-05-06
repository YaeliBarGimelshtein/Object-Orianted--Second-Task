package id315000539_id318353356_id208722710;

import java.util.Vector;

public class Set <T> {
	private Vector <T> set;
	
	public Set() {
		set= new Vector<>();
	}

	public boolean add(T object) {
		for (int i = 0; i < set.size(); i++) {
			if (object.equals(set.get(i))) {
				return false;
			}
		}
		set.add(object);
		return true;
	}
	
	public int getSetLenght() {
		return this.set.size();
	}
	
	public T getObjectAtIndex(int index){
		return this.set.get(index);
	}
}
