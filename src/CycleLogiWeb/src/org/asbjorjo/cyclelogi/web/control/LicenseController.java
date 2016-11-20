package org.asbjorjo.cyclelogi.web.control;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.asbjorjo.cyclelogi.data.entity.License;
import org.asbjorjo.cyclelogi.data.entity.Person;
import org.asbjorjo.cyclelogi.data.entity.PersonRole;
import org.asbjorjo.cyclelogi.service.LicenseService;
import org.asbjorjo.cyclelogi.service.PersonRoleService;

@Named
@ConversationScoped
public class LicenseController implements Serializable {
	private static final long serialVersionUID = 6456449931036924973L;

	private static final Logger log = Logger.getLogger( LicenseController.class.getName() );

	@Inject
	private Conversation conversation;
	@Inject
	private LicenseService licenseService;
	@Inject
	private PersonRoleService personRoleService;

	private License license;
	private List<License> allLicenses;

	private List<Person> availablePeople;
	private Person selectedPerson;
	private List<PersonRole> availableRoles;
	private PersonRole selectedRole;
	
	@PostConstruct
	public void init() {
		log.log(Level.INFO, "Initializing LicenseController");
		
		license = new License();

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		license.getValid().setStartDate((Calendar) calendar.clone());
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		license.getValid().setEndDate(calendar);

		allLicenses = licenseService.getAllLicenses();
		availablePeople = personRoleService.getPersonsWithRoles();
	}
	
	@PreDestroy
	public void destroy() {
		log.log(Level.INFO, "Destroying LicenseController");
	}

	public void startConversation() {
		if (conversation.isTransient()) {
			log.log(Level.INFO, "Starting conversation");
			conversation.begin();
		}
	}
	
	public String addLicense() {
		log.log(Level.INFO, "Adding License: " + license);
		log.log(Level.INFO, "For PersonRole: " + selectedRole);
		license.setPersonRole(selectedRole);
		license = licenseService.addNew(license);
		
		allLicenses = licenseService.getAllLicenses();
		
		conversation.end();
		
		return "listLicenses";
	}

	public void changePerson() {
		log.log(Level.INFO, "Changing person");
		availableRoles = getAvailableRoles();
	}
	
	public List<Person> getAvailablePeople() {
		return availablePeople;
	}

	public void setAvailablePeople(List<Person> availablePeople) {
		this.availablePeople = availablePeople;
	}

	public List<PersonRole> getAvailableRoles() {
		if (selectedPerson != null) {
			availableRoles = personRoleService.getRolesForPerson(selectedPerson);
		}

		return availableRoles;
	}

	public void setAvailableRoles(List<PersonRole> availableRoles) {
		this.availableRoles = availableRoles;
	}

	public License getLicense() {
		return license;
	}
	public void setLicense(License license) {
		this.license = license;
	}
	public List<License> getAllLicenses() {
		return allLicenses;
	}
	public void setAllLicenses(List<License> allLicenses) {
		this.allLicenses = allLicenses;
	}

	public Person getSelectedPerson() {
		return selectedPerson;
	}

	public void setSelectedPerson(Person selectedPerson) {
		this.selectedPerson = selectedPerson;
	}

	public PersonRole getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(PersonRole selectedRole) {
		this.selectedRole = selectedRole;
	}	
}