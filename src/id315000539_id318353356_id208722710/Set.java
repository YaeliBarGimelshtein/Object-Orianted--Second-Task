package id315000539_id318353356_id208722710;

import java.util.Vector;

public class Set <T> {
	private Vector <T> set;
	
	public Set() {
		set= new Vector<>();
	}

	public boolean add(T object) {
		for (int i = 0; i < set.size(); i++) {
			if (set.get(i).equals(object)) {
				return false;
			}
		}
		set.add(object);
		return true;
	}

	public Vector<T> getSet() {
		return set;
	}
}
