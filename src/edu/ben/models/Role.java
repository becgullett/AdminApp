package edu.ben.models;

public class Role {
	private int role;
	private int name;

	public Role(int role, int name) {
		this.role = role;
		this.name = name;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

}
