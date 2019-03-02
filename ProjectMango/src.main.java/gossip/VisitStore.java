package gossip;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class for calculate after how many store visits everybody knows all the
 * gossips.
 * 
 * @author igarciac
 *
 */
public class VisitStore {

	public static void main(String[] args) {

		/**
		 * Exemple 1
		 */
		List<Person> personList = new ArrayList<Person>();
		personList.add(new Person("A", new String[] { "Store3", "Store1", "Store2", "Store3" }));
		personList.add(new Person("B", new String[] { "Store3", "Store2", "Store3", "Store1" }));
		personList.add(new Person("C", new String[] { "Store4", "Store2", "Store3", "Store4", "Store5" }));

		// Calculate visits
		GossipControl co = new GossipControl();
		Integer interactions = co.calculateInteractions(personList);

		// Show how many stores visited for know all gossip.
		if (interactions > 0) {
			System.out.println("Exemple 1: " + interactions);
		} else if (interactions < 0) {
			System.out.println("No values for calculate how many store visits everybody to knows all the gossips");
		} else {
			System.out.println("Exemple 1: Never");
		}

		System.out.println("---------------------------- ");

		/**
		 * Exemple 2
		 */
		personList = new ArrayList<Person>();
		personList.add(new Person("A", new String[] { "Store2", "Store1", "Store2" }));
		personList.add(new Person("B", new String[] { "Store5", "Store2", "Store8" }));

		// Calculate visits
		interactions = co.calculateInteractions(personList);

		// Show how many stores visited for know all gossip.
		if (interactions > 0) {
			System.out.println("Exemple 2: " + interactions);
		} else if (interactions < 0) {
			System.out.println("No values for calculate how many store visits everybody to knows all the gossips");
		} else {
			System.out.println("Exemple 2: Never");
		}

	}

}
