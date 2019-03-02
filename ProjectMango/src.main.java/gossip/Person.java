package gossip;

import java.util.Arrays;

/**
 * 
 * Class identify the person and rute to visit with the current store visited
 * 
 * @author igarciac
 *
 */
public class Person {

	private String name;
	private String[] rute;
	private String actualStore;

	public Person(String name, String[] rute) {
		super();
		this.name = name;
		this.rute = rute;
	}

	public Person(String name, String actualStore) {
		super();
		this.name = name;
		this.actualStore = actualStore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getRute() {
		return rute;
	}

	public void setRute(String[] rute) {
		this.rute = rute;
	}

	public String getActualStore() {
		return actualStore;
	}

	public void setActualStore(String actualStore) {
		this.actualStore = actualStore;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", rute=" + Arrays.toString(rute) + ", actualStore=" + actualStore + "]";
	}

}
