package org.asbjorjo.cyclelogi.web.edit;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.asbjorjo.cyclelogi.data.entity.Person;
import org.asbjorjo.cyclelogi.data.entity.PersonRole;
import org.asbjorjo.cyclelogi.data.entity.Role;
import org.asbjorjo.cyclelogi.service.PersonRoleService;
import org.asbjorjo.cyclelogi.service.PersonService;
import org.asbjorjo.cyclelogi.service.RoleService;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

@Named
@ConversationScoped
public class PersonEdit implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger( PersonEdit.class.getName() );
	
	@Inject
	Conversation conversation;
	@Inject
	PersonService personService;
	@Inject
	PersonRoleService personRoleService;
	@Inject
	RoleService roleService;
	
	private Person person;
	private DualListModel<PersonRole> roles;
	
	@PostConstruct
	public void init() {
		log.log(Level.INFO, "Initializing PersonEdit");
		
		this.person = new Person();
	}
	
	@PreDestroy
	public void destroy() {
		log.log(Level.INFO, "Destroying PersonEdit");
	}

	public void loadPerson() {
		if (conversation.isTransient()) {
			if (this.person.getId() != null) {
				this.person = personService.findById(this.person.getId());
			} else {
				this.person = new Person();
			}
			log.log(Level.INFO, "Loaded Person: " + person);
			
			setupRoles();
			
			conversation.begin();
		}
	}
	
	private void setupRoles() {
		roles = new DualListModel<>();
		
		if (this.person.getPersonRoles().isEmpty()) {
			List<Role> rawRoles = roleService.getAllRoles();
			for (Role role : rawRoles) {
				PersonRole pr = new PersonRole();
				pr.setRole(role);
				roles.getSource().add(pr);
			}
		} else {
			roles.setTarget(this.person.getPersonRoles());
			roles.setSource(personRoleService.findAvailabaleRolesForPerson(this.person));
		}
	}

	public String addPerson() {
		this.person.setPersonRoles(roles.getTarget());
		for (PersonRole personRole : this.person.getPersonRoles()) {
			personRole.setPerson(this.person);
		}
		
		personService.addNew(this.person);
		
		return "listPersons";
	}
	
	public String updatePerson() {
		log.log(Level.INFO, "Updating Person: " + person);
		log.log(Level.INFO, "New roles: " + roles.getTarget());
		
		personService.update(this.person);
		
		conversation.end();
		
		return "listPersons";
	}
	
	public void onTransfer(TransferEvent event) {
		log.log(Level.INFO, "Items: " + event.getItems() + " added " + event.isAdd());
		for (Object item : event.getItems()) {
			PersonRole personRole = (PersonRole) item;
			if (event.isAdd()) {
				personRole.setPerson(person);
				personRole.getPerson().getPersonRoles().add(personRole);
				personRole.getRole().getPersonRoles().add(personRole);
			} else {
				personRole.getPerson().getPersonRoles().remove(personRole);
				personRole.getRole().getPersonRoles().remove(personRole);
				personRole.setPerson(null);
			}
		}
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		log.log(Level.INFO, "Setting person: " + person);
		this.person = person;
	}

	public DualListModel<PersonRole> getRoles() {
		return roles;
	}

	public void setRoles(DualListModel<PersonRole> roles) {
		this.roles = roles;
	}
}