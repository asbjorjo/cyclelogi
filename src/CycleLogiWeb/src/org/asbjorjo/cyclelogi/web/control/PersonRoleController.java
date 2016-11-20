package org.asbjorjo.cyclelogi.web.control;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.asbjorjo.cyclelogi.data.entity.PersonRole;
import org.asbjorjo.cyclelogi.service.PersonRoleService;

@Named
@RequestScoped
public class PersonRoleController {
	private static final Logger log = Logger.getLogger( PersonRoleController.class.getName() );

	@Inject
	private PersonRoleService personRoleService;

	private List<PersonRole> personRoles;

	@PostConstruct
	public void init() {
		log.log(Level.INFO, "Initializing PersonRoleController");
		personRoles = personRoleService.getAllPersonRoles();
	}
	
	public List<PersonRole> getPersonRoles() {
		return personRoles;
	}

	public void setPersonRoles(List<PersonRole> personRoles) {
		this.personRoles = personRoles;
	}
}