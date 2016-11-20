package org.asbjorjo.cyclelogi.data.entity;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.asbjorjo.cyclelogi.data.embed.EntityTimestamp;
import org.asbjorjo.cyclelogi.data.embed.PersonRoleId;
import org.asbjorjo.cyclelogi.data.interfaces.TimestampedEntity;
import org.asbjorjo.cyclelogi.data.listener.TimestampEntityListener;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

/**
 * Entity implementation class for Entity: PersonRole
 *
 */
@Entity
@EntityListeners(TimestampEntityListener.class)
@NamedQueries({
	@NamedQuery(name="findAllPersonRoles", query="SELECT pr from PersonRole pr"),
	@NamedQuery(name="findPersonRoleByRoleAndPerson", query="SELECT pr from PersonRole pr where pr.person = :person AND pr.role = :role"),
	@NamedQuery(name="findPersonsWithRoles", query="SELECT DISTINCT p from Person p inner join p.personRoles as pr")
})
public class PersonRole implements Serializable, TimestampedEntity {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonRoleId id;
	@Column(name="ACTIVE")
	private Boolean active = Boolean.TRUE;
	@ManyToOne(optional = false, cascade = { MERGE, PERSIST } )
	@MapsId("role")
	private Role role;
	@ManyToOne(optional = false, cascade = MERGE)
	@MapsId("person")
	private Person person;
	@OneToMany(orphanRemoval = true, cascade = ALL, mappedBy = "personRole")
	@OrderBy("valid.endDate DESC")
	private List<License> licenses;
	private EntityTimestamp timestamp;
	
	public PersonRole() {
		super();
		
		licenses = new ArrayList<>();
	}

	public PersonRole(Role role2, Person person2) {
		this();
		
		this.role = role2;
		this.person = person2;
		person2.getPersonRoles().add(this);
		role2.getPersonRoles().add(this);
	}

	public void clearRelations() {
		if (this.person != null) {
			this.person.getPersonRoles().remove(this);
		}
		if (this.role != null) {
			this.role.getPersonRoles().remove(this);
		}
		this.role = null;
		this.person = null;
	}

	public PersonRoleId getId() {
		return id;
	}

	public void setId(PersonRoleId id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<License> getLicenses() {
		return licenses;
	}

	public void setLicenses(List<License> licenses) {
		this.licenses = licenses;
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
		result = prime * result + ((active == null) ? 0 : active.hashCode());
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
		PersonRole other = (PersonRole) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
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
		return String.format("PersonRole [id=%s, active=%s, role=%s, person=%s, licenses=%s, timestamp=%s]", id, active,
				role != null ? role.getName() : null, person != null ? person.getId() : null, 
				licenses != null ? licenses.size() : null, timestamp);
	}
}