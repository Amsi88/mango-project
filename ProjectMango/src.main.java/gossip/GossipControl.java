package gossip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class class for know how many visits need everybody for knows all the
 * gossips.
 * 
 * @author igarciac
 *
 */
public class GossipControl {

	/**
	 * First interactions
	 */
	private static final int _0_FIRST_INTERACTION = 0;
	/**
	 * Second interactions
	 */
	private static final int _1_SECOND_INTERACTION = 1;
	/**
	 * Maximum of 480 minutes to get all the gossiping around
	 */
	private static final int _LIMIT_TIME = 480;
	/**
	 * List of interactions
	 */
	private Map<String, List<String>> listOfInteractions;

	/**
	 * 
	 * Method for calculate after how many store visits everybody knows all the
	 * gossips
	 * 
	 * @param List<Person> personList
	 * @return Integer
	 */
	public Integer calculateInteractions(List<Person> peopleList) {
		listOfInteractions = new HashMap<String, List<String>>();
		List<String> interactions;
		if (peopleList.size() > 0) {
			Person[][] rutesList = prepareCompleteRute(peopleList);
			for (int i = 0; i < _LIMIT_TIME; i++) {
				interactions = new ArrayList<String>();
				for (int x = 0; x < peopleList.size(); x++) {
					for (int y = x + 1; y < peopleList.size(); y++) {
						if (rutesList[x][i].getActualStore().equals(rutesList[y][i].getActualStore())) {
							interactions.add(rutesList[x][i].getName() + ":" + rutesList[y][i].getName());
						}
					}
				}
				if (interactions.size() > 0) {
					if (validateTotalInteractions(peopleList, interactions)) {
						return i + 1; // Because is initialized on 0
					}
				}
			}
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * 
	 * Method for validate if all person know the gossip
	 * 
	 * @param peopleList
	 * @param interactions
	 * @return Boolean
	 */
	private Boolean validateTotalInteractions(List<Person> peopleList, List<String> interactions) {
		prepareInteractions(interactions);
		Integer total = 0;
		// Validate if all people know the gossip.
		for (Person person : peopleList) {
			if (listOfInteractions.get(person.getName()) != null
					&& (listOfInteractions.get(person.getName()).size() >= peopleList.size())) {
				total++;
			}
		}
		if (total >= peopleList.size()) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Method for prepare list of interactions.
	 * 
	 * @param interactions
	 */
	private void prepareInteractions(List<String> interactions) {

		// For know how many visits needed is necessary to save the all interactions for
		// the different people participated on interaction
		for (String personInteraction : interactions) {

			List<String> finalInteractions = new ArrayList<String>();
			String[] person = personInteraction.split(":");

			if (listOfInteractions.get(person[_0_FIRST_INTERACTION]) != null) {
				for (String previouslyInteractions : listOfInteractions.get(person[_0_FIRST_INTERACTION])) {
					finalInteractions.add(previouslyInteractions);
				}
			}
			if (listOfInteractions.get(person[_1_SECOND_INTERACTION]) != null) {
				for (String previouslyInteractions : listOfInteractions.get(person[_1_SECOND_INTERACTION])) {
					finalInteractions.add(previouslyInteractions);
				}
			}

			finalInteractions.add(person[_0_FIRST_INTERACTION]);
			finalInteractions.add(person[_1_SECOND_INTERACTION]);

			// Set no repeated values
			listOfInteractions.put(person[_0_FIRST_INTERACTION],
					finalInteractions.stream().distinct().collect(Collectors.toList()));
			listOfInteractions.put(person[_1_SECOND_INTERACTION],
					finalInteractions.stream().distinct().collect(Collectors.toList()));

		}
	}

	/**
	 * 
	 * Method that prepare the full rute of persons on one day
	 * 
	 * 
	 * @param List<Person> peopleList
	 * @return Person[][]
	 */

	private Person[][] prepareCompleteRute(List<Person> peopleList) {
		// The matrix is created for the total of stores visited in a day (8h or 480min)
		// by person
		Person[][] ruteList = new Person[peopleList.size()][_LIMIT_TIME];
		Integer maxPerson = 0;
		for (Person person : peopleList) {
			Integer numberOfStores = 0;
			for (int i = 0; i < _LIMIT_TIME; i++) {
				String[] personsRute = person.getRute();
				ruteList[maxPerson][i] = new Person(person.getName(), personsRute[numberOfStores]);
				if (numberOfStores >= person.getRute().length - 1) {
					numberOfStores = 0;
				} else {
					numberOfStores++;
				}
			}
			maxPerson++;
		}
		return ruteList;
	}
}
