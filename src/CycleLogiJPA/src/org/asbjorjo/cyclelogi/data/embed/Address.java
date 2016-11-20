package org.asbjorjo.cyclelogi.data.embed;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String street;
	private String city;
	private Integer postCode;
	private String country;

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getPostCode() {
		return postCode;
	}
	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
