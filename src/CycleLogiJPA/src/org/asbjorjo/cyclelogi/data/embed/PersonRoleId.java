package org.asbjorjo.cyclelogi.data.embed;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PersonRoleId
 *
 */

@Embeddable
public class PersonRoleId implements Serializable {
	private static final long serialVersionUID = 1L;

	Long person;
	Long role;
	
	public Long getPerson() {
		return person;
	}
	public void setPerson(Long person) {
		this.person = person;
	}
	public Long getRole() {
		return role;
	}
	public void setRole(Long role) {
		this.role = role;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		PersonRoleId other = (PersonRoleId) obj;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("PersonRoleId [person=%s, role=%s]", person, role);
	}
}