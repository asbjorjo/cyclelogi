package org.asbjorjo.cyclelogi.data.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.asbjorjo.cyclelogi.data.embed.Address;
import org.asbjorjo.cyclelogi.data.embed.EntityTimestamp;
import org.asbjorjo.cyclelogi.data.interfaces.TimestampedEntity;
import org.asbjorjo.cyclelogi.data.listener.TimestampEntityListener;

import static javax.persistence.TemporalType.DATE;
import static javax.persistence.CascadeType.ALL;

/**
 * Entity implementation class for Entity: Person
 *
 */
@Entity
@EntityListeners(TimestampEntityListener.class)
@NamedQueries({
	@NamedQuery(name="findAllPeople", query="SELECT p from Person p ORDER BY p.lastName, p.firstName"),
	@NamedQuery(name="findPersonById", query="SELECT p from Person p where p.id = :id")
})
public class Person implements Serializable, TimestampedEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	@Column(name="FIRST_NAME", nullable=false)
	private String firstName;
	@Column(name="LAST_NAME", nullable=false)
	private String lastName;
	@Column(name="EMAIL", unique = true, nullable = false)
	private String email;
	@Column(name="NATIONALITY")
	private String nationality;
	private Address address;
	@Temporal(DATE)
	@Column(name="BIRTHDAY")
	private Date birthday;
	@OneToMany(mappedBy = "person", cascade = ALL, orphanRemoval = true)
	private List<PersonRole> personRoles;
	private EntityTimestamp timestamp;

	public Person() {
		super();

		this.personRoles = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<PersonRole> getPersonRoles() {
		return personRoles;
	}

	public void setPersonRoles(List<PersonRole> personRoles) {
		this.personRoles = personRoles;
	}

	public EntityTimestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(EntityTimestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (nationality == null) {
			if (other.nationality != null)
				return false;
		} else if (!nationality.equals(other.nationality))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"Person [id=%s, firstName=%s, lastName=%s, email=%s, nationality=%s, birthday=%s, personRoles=%s]", id,
				firstName, lastName, email, nationality, birthday, personRoles );
	}
}
