package Quiz.SystemDesignAndScalability;

import java.util.HashMap;

/* Millions of user should be divided into many servers*/

public class Server {

	HashMap<Integer, Machine> machines = new HashMap<Integer, Machine>();
	// In real world, a user id would have some rule that can make it easy to get the machine it belong
    // And, here we just use a simple map to match the person and machine.
	HashMap<Integer, Integer> personToMachineMap = new HashMap<Integer, Integer>();
	
	public Machine getMachineWithId(int machineId) {
		return machines.get(machineId);
	}
	
	public int getMachineIdWithPersonId(int personId) {
		Integer machineId = personToMachineMap.get(personId);
		return machineId == null ? -1 : machineId;
	}
	
	public Person getPersonWithId(int personId) {
		Integer machineId = personToMachineMap.get(personId);
		if(machineId == null) return null;
		
		Machine machine = getMachineWithId(machineId);
		if(machine == null) return null;
		
		Person person = machine.getPersonWithID(personId);
		return person;
	}
	
	public Server() {
		// TODO Auto-generated constructor stub
	}

}
