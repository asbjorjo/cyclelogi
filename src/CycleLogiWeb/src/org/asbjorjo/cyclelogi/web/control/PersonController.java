package org.asbjorjo.cyclelogi.web.control;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.asbjorjo.cyclelogi.data.entity.Person;
import org.asbjorjo.cyclelogi.service.PersonService;

@Named
@RequestScoped
public class PersonController {
	private static final Logger log = Logger.getLogger( PersonController.class.getName() );
	
	@Inject
	private PersonService personService;
	
	private List<Person> persons;

	@PostConstruct
	public void init() {
		log.log(Level.INFO, "Initializing PersonController");
		
		this.persons = personService.getAllPersons();
	}
	
	@PreDestroy
	public void destroy() {
		log.log(Level.INFO, "Destroying PersonController");
	}
	
	public String deletePerson(String personId) {
		if (personId == null || personId == "") {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Missing user ID, cannot delete", "Missing user ID, cannot delete");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			Long id = null;
			Person person = null;
			
			try {
				id = Long.parseLong(personId);
				person =  personService.findById(id);
			} catch (NumberFormatException e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid user ID, cannot delete", "Invalid user ID, cannot delete");
				FacesContext.getCurrentInstance().addMessage(null, msg);				
			}
			
			log.log(Level.INFO, "Deleting person:" + person);
		
			personService.deletePerson(person);
		
			persons = personService.getAllPersons();
		}
		
		return "listPersons";
	}
	
	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
}