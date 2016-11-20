package org.asbjorjo.cyclelogi.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.asbjorjo.cyclelogi.data.entity.Person;

/**
 * Session Bean implementation class PersonBean
 */
@Stateless
@LocalBean
public class PersonService {
	private static final Logger log = Logger.getLogger( PersonService.class.getName() );

	@PersistenceContext(unitName="CycleLogiJPA")
	private EntityManager em;
	@EJB
	private PersonRoleService personRoleService;
	
    public Person addNew(Person person) {
    	person = em.merge(person);
    	log.log(Level.INFO, "Person added: " + person);
    	return person;
    }
    
    public List<Person> getAllPersons() {
    	TypedQuery<Person> query = em.createNamedQuery("findAllPeople", Person.class);
    	return query.getResultList();
    }

	public Person findById(Long id) {
		Person person = em.find(Person.class, id);
		return person;
	}

	public void deletePerson(Person person) {
		if (!em.contains(person)) {
			person = em.getReference(Person.class, person.getId());
		}
		em.remove(person);
	}

	public Person update(Person person) {
		return em.merge(person);
	}
}