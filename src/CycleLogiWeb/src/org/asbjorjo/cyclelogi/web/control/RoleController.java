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

import org.asbjorjo.cyclelogi.data.entity.Role;
import org.asbjorjo.cyclelogi.service.RoleService;

@Named
@RequestScoped
public class RoleController {
	private static final Logger log = Logger.getLogger( RoleController.class.getName() );

	@Inject
	private RoleService roleService;

	private Role role = new Role();
	private List<Role> availableRoles;

	@PostConstruct
	public void init() {
		log.log(Level.INFO, "Initializing RoleController");
		availableRoles = roleService.getAllRoles();
	}

	@PreDestroy
	public void destroy() {
		log.log(Level.INFO, "Destroying RoleController");
	}
	
	public String addRole() {
		roleService.addNew(role);
		availableRoles = roleService.getAllRoles();
		return "listRoles";
	}

	public String deleteRole(Role role) {
		if (role.getPersonRoles().isEmpty()) {
			roleService.deleteRole(role);
			this.role = new Role();
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Role in use, cannot delete", "Role in use, cannot delete");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		this.availableRoles = roleService.getAllRoles();
		
		return "listRoles";
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getAvailableRoles() {
		return availableRoles;
	}

	public void setAvailableRoles(List<Role> availableRoles) {
		this.availableRoles = availableRoles;
	}
}