package org.asbjorjo.cyclelogi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.asbjorjo.cyclelogi.data.entity.Person;
import org.asbjorjo.cyclelogi.data.entity.PersonRole;
import org.asbjorjo.cyclelogi.data.entity.Role;

/**
 * Session Bean implementation class PersonRoleService
 */
@Stateless
@LocalBean
public class PersonRoleService {
	private static final Logger log = Logger.getLogger( PersonRoleService.class.getName() );

	@PersistenceContext
	private EntityManager em;

    public PersonRole addNew(PersonRole personRole) {
    	log.log(Level.INFO, "PersonRole to save: " + personRole);
    	log.log(Level.INFO, em.toString());
    	
    	if (personRole.getPerson().getId() != null) {
    		Person person = em.find(Person.class, personRole.getPerson().getId());
    		personRole.setPerson(person);
    		person.getPersonRoles().add(personRole);
    	}
    	if (personRole.getRole().getId() != null) {
    		Role role = em.find(Role.class, personRole.getRole().getId());
    		personRole.setRole(role);
    		role.getPersonRoles().add(personRole);
    	}

//    	personRole.getPerson().getPersonRoles().add(personRole);
//    	personRole.getRole().getPersonRoles().add(personRole);
    	
    	em.persist(personRole);
    	log.log(Level.INFO, "PersonRole saved: " + personRole);
    	return personRole;
    }
    
    public List<PersonRole> getAllPersonRoles() {
    	TypedQuery<PersonRole> query = em.createNamedQuery("findAllPersonRoles", PersonRole.class);
    	log.log(Level.INFO, "PersonRoles retrieved from DB: " + query.getResultList());
    	return query.getResultList();
    }

	public PersonRole getById(Long id) {
		return em.find(PersonRole.class, id);
	}

	public List<Person> getPersonsWithRoles() {
		TypedQuery<Person> query = em.createNamedQuery("findPersonsWithRoles", Person.class);
		return query.getResultList();
	}

	public PersonRole update(PersonRole personRole) {
		personRole = em.merge(personRole);
		return personRole;
	}

	public void delete(PersonRole personRole) {
		if (!em.contains(personRole)) {
			personRole = em.getReference(PersonRole.class, personRole.getId());
		}
		personRole.clearRelations();
		
		em.remove(personRole);
	}
	
	public List<PersonRole> findAvailabaleRolesForPerson(Person person) {
		List<PersonRole> available = new ArrayList<>();
		
		TypedQuery<Role> query = em.createQuery("SELECT DISTINCT r FROM Role r WHERE r not IN (SELECT r FROM Role r JOIN r.personRoles pr WHERE pr.person = :person)", Role.class);
		query.setParameter("person", person);
		List<Role> roles = query.getResultList();
		for (Role role : roles) {
			PersonRole personRole = new PersonRole();
			personRole.setRole(role);
			available.add(personRole);
		}
		return available;
	}

	public List<PersonRole> getRolesForPerson(Person selectedPerson) {
		TypedQuery<PersonRole> query = em.createQuery("SELECT pr FROM PersonRole pr where pr.person = :person", PersonRole.class);
		query.setParameter("person", selectedPerson);
		return query.getResultList();
	}
}
