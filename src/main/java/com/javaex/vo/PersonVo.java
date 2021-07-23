package com.javaex.vo;

public class PersonVo {
	
	//Field
	private int personId; 
	private String name;
	private String hp;
	private String company;
	
	//Constructor
	public PersonVo() {
		
	}
	
	public PersonVo(int personId, String name, String hp, String company) {
		this.personId = personId;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	public PersonVo(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	//Method G/S
	public int getPersonId() {
		return personId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getHp() {
		return hp;
	}
	
	public String getCompany() {
		return company;
	}
	
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setHp(String hp) {
		this.hp = hp;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	//Method - Ordinary
	@Override
	public String toString() {
		return "PersonVo [personId=" + personId + ", name=" + name + ", hp=" + hp + ", company=" + company + "]";
	}
	
	
}
