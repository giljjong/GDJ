package com.gdu.app01.xml06;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// Collection Framework(List, Set, Map)에 주입

public class Person {
	
	private List<String> hobbies;
	private Set<String> contacts;
	private Map<String, String> friends;
	
	public List<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
	public Set<String> getContacts() {
		return contacts;
	}
	public void setContacts(Set<String> contacts) {
		this.contacts = contacts;
	}
	public Map<String, String> getFriends() {
		return friends;
	}
	public void setFriends(Map<String, String> friends) {
		this.friends = friends;
	}

	public void info() {
		for(int i = 0; i < hobbies.size(); i++) {
			System.out.println((i + 1) + "번째 취미 : " + hobbies.get(i));
		}
		for(String contact : contacts) {
			System.out.println(contact);
		}
		for(Map.Entry<String, String> entry : friends.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
	
}
