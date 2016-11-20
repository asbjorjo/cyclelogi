package org.asbjorjo.cyclelogi.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.asbjorjo.cyclelogi.data.entity.Event;
import org.asbjorjo.cyclelogi.data.entity.PersonRole;

/**
 * Session Bean implementation class EventService
 */
@Stateless
@LocalBean
public class EventService {
	private static final Logger log = Logger.getLogger( EventService.class.getName() );

	@PersistenceContext
	private EntityManager em;

    public List<Event> getAllEvents() {
    	TypedQuery<Event> query = em.createNamedQuery("findAllEvents", Event.class);
    	return query.getResultList();
    }
    
    public Event addNew(Event event) {
    	em.persist(event);
    	return event;
    }
    
    public Event update(Event event) {
    	event = em.merge(event);
    	return event;
    }

	public void addPeopleToEvent(Event event, List<PersonRole> personRoles) {
//		event = em.find(Event.class, event.getId());
//		for (PersonRole personRole : personRoles) {
//			event.getPeople().add(personRole);
//		}
		em.merge(event);
	}
}
