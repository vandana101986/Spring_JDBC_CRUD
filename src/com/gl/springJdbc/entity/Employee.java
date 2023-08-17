package com.gl.springJdbc.entity;

public class Employee {
	
	private String name;
	private String email;
	private String address;
	private String phoneno;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
	@Override
	public String toString() {
		return "Employee [name : "+name+",\n email : "+email+
				",\n address : "+address+",\n phoneno : "+phoneno+"]";
	}

}
