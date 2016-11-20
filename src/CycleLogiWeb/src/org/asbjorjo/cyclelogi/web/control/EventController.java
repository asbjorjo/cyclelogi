package org.asbjorjo.cyclelogi.web.control;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.asbjorjo.cyclelogi.data.entity.Event;
import org.asbjorjo.cyclelogi.service.EventService;

@Named("eventController")
@RequestScoped
public class EventController {
	private static final Logger log = Logger.getLogger( EventController.class.getName() );

	@Inject
	private EventService eventService;

	private List<Event> events;
	
	@PostConstruct
	public void init() {
		log.log(Level.INFO, "Initializing EventController");
		events = eventService.getAllEvents();
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	public String addEvent() {		
		return "listEvents.jsf";
	}
}
