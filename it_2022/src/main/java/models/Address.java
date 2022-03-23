package models;

public class Address {
	private String city;
	private String street;
	
	public Address() {
		city="Не е зададен";
		street="Не е зададен";
	}
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
}
