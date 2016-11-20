package org.asbjorjo.cyclelogi.data.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

import org.asbjorjo.cyclelogi.data.embed.DatePeriod;
import org.asbjorjo.cyclelogi.data.embed.EntityTimestamp;
import org.asbjorjo.cyclelogi.data.interfaces.TimestampedEntity;
import org.asbjorjo.cyclelogi.data.listener.TimestampEntityListener;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.MERGE;

/**
 * Entity implementation class for Entity: License
 *
 */
@Entity
@EntityListeners(TimestampEntityListener.class)
@NamedQuery(name="findAllLicenses", query="SELECT l from License l order by l.valid.endDate DESC")
public class License implements Serializable, TimestampedEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	@Column(name="NATIONALITY")
	private String nationality;
	private DatePeriod valid;
	@ManyToOne(optional = false, cascade = { PERSIST, MERGE })
	private PersonRole personRole;
	@Column(name="NUMBER", nullable=false)
	private String number;
	private EntityTimestamp timestamp;

	public License() {
		super();
		
		valid = new DatePeriod();
		personRole = new PersonRole();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nation) {
		this.nationality = nation;
	}

	public DatePeriod getValid() {
		return valid;
	}

	public void setValid(DatePeriod valid) {
		this.valid = valid;
	}

	public PersonRole getPersonRole() {
		return personRole;
	}

	public void setPersonRole(PersonRole personRole) {
		this.personRole = personRole;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((personRole == null) ? 0 : personRole.hashCode());
		result = prime * result + ((valid == null) ? 0 : valid.hashCode());
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
		License other = (License) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nationality == null) {
			if (other.nationality != null)
				return false;
		} else if (!nationality.equals(other.nationality))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (personRole == null) {
			if (other.personRole != null)
				return false;
		} else if (!personRole.equals(other.personRole))
			return false;
		if (valid == null) {
			if (other.valid != null)
				return false;
		} else if (!valid.equals(other.valid))
			return false;
		return true;
	}
}
