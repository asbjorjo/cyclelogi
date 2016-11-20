package org.asbjorjo.cyclelogi.data.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.CascadeType.ALL;

/**
 * Entity implementation class for Entity: Role
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="findAllRoles", query="SELECT r from Role r ORDER BY r.name"),
	@NamedQuery(name="findRoleByName", query="SELECT r from Role r where r.name = :name"),
	@NamedQuery(name="findRoleById", query="SELECT r from Role r where r.id = :id")
})
@Cacheable(false)
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String name;
	private String description;
	private Boolean licensed = Boolean.FALSE;
	@OneToMany(mappedBy = "role", orphanRemoval = true, cascade = ALL)
	private List<PersonRole> personRoles;

	public Role() {
		super();
		
		this.personRoles = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getLicensed() {
		return licensed;
	}

	public void setLicensed(Boolean licensed) {
		this.licensed = licensed;
	}

	public List<PersonRole> getPersonRoles() {
		return personRoles;
	}

	public void setPersonRoles(List<PersonRole> personRoles) {
		this.personRoles = personRoles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((licensed == null) ? 0 : licensed.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Role other = (Role) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (licensed == null) {
			if (other.licensed != null)
				return false;
		} else if (!licensed.equals(other.licensed))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Role [id=%s, name=%s, description=%s, licensed=%s, personRoles=%s]", id, name, description,
				licensed, personRoles);
	}

	public void clearRelations() {
		List<PersonRole> toClear = new ArrayList<>(personRoles);
		for (PersonRole personRole : toClear) {
			personRole.clearRelations();
		}
		personRoles.clear();
	}
}
