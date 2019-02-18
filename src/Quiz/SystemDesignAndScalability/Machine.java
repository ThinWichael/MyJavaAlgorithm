package Quiz.SystemDesignAndScalability;

import java.util.HashMap;

public class Machine {

	int machineId;
	String account;
	String pwd;
	String ip;
	
//	HashMap<Integer, Person> psersonMap = new HashMap<Integer, Person>();
	
	public Person getPersonWithID(Integer personId) {
		/* go api of this machine and get the Person*/
//		Person person = searchPersonApi(personId);
//		return person
		return new Person(personId); // fake code
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getMachineId() {
		return machineId;
	}

	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Machine() {
		// TODO Auto-generated constructor stub
	}

}
