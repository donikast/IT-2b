package models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
	//@XmlElement(name="town")
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
