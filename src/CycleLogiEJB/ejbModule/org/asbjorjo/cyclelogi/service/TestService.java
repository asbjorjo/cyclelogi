package org.asbjorjo.cyclelogi.service;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.asbjorjo.cyclelogi.data.embed.Address;
import org.asbjorjo.cyclelogi.data.embed.DatePeriod;
import org.asbjorjo.cyclelogi.data.embed.TimePeriod;
import org.asbjorjo.cyclelogi.data.entity.License;
import org.asbjorjo.cyclelogi.data.entity.Person;
import org.asbjorjo.cyclelogi.data.entity.PersonRole;
import org.asbjorjo.cyclelogi.data.entity.Role;

/**
 * Session Bean implementation class TestBean
 */
@Singleton
@LocalBean
@Startup
public class TestService {
	private static final Logger log = Logger.getLogger( TestService.class.getName() );
	
	@PersistenceContext
	private EntityManager em;
	@EJB
	private PersonService personService;
	@EJB
	private EventService eventService;
	@EJB
	private RoleService roleService;
	@EJB
	private PersonRoleService personRoleService;
	@EJB
	private LicenseService licenseService;
	
	private Boolean dataCreated;
	
    @PostConstruct
    public void init() {
    	dataCreated = Boolean.FALSE;
    	createData();
    }
    
    public void createData() {
    	if (! dataCreated) {
	    	Role r = new Role();
	    	License l = new License();
	    	Person p = new Person();
	    	PersonRole pr = new PersonRole();
	    	Address a = new Address();
	    	
	    	a.setCity("Stavanger");
	    	a.setCountry("Norway");
	    	a.setPostCode(4027);
	    	a.setStreet("Stølstunet 44");
	    	
	    	r.setDescription("Fix bikes");
	    	r.setName("Mechanic");
	    	r = roleService.addNew(r);
	    	r = new Role();
	    	r.setName("DS");
	    	r.setDescription("Sports Director");
	    	r.setLicensed(Boolean.TRUE);
	    	r = roleService.addNew(r);
	
	 	   	p.setFirstName("Arne");
	    	p.setLastName("And");
	    	p.setEmail("arne@and.example");
	    	p.setNationality("Dutch");
	    	p = personService.addNew(p);
	    	p.setAddress(a);
	    	
	    	pr.setRole(r);
	    	pr.setPerson(p);
	    	personRoleService.addNew(pr);
	    	
	    	l.setPersonRole(pr);
	    	l.setNumber("LICENSENEW");
	    	Calendar calendar = Calendar.getInstance();
	    	calendar.set(2016, Calendar.JANUARY, 1);
	    	DatePeriod dp = new DatePeriod();
	    	dp.setStartDate((Calendar) calendar.clone());
	    	calendar.set(2016, Calendar.DECEMBER, 31);
	    	dp.setEndDate((Calendar) calendar.clone());
	    	l.setValid(dp);
	    	licenseService.addNew(l);
	    	
	    	l = new License();
	    	l.setPersonRole(pr);
	    	l.setNumber("LICENSEOLD");
	    	dp = new DatePeriod();
	    	calendar.set(Calendar.YEAR, 2015);
	    	dp.setEndDate((Calendar) calendar.clone());
	    	calendar.set(2015, Calendar.JANUARY, 1);
	    	dp.setStartDate((Calendar) calendar.clone());
	    	l.setValid(dp);
	    	licenseService.addNew(l);
	    	
	    	p = new Person();
	    	p.setFirstName("Donald");
	    	p.setLastName("Duck");
	    	p.setEmail("donald@duck.example");
	    	p.setNationality("Dutch");
	    	p = personService.addNew(p);
	    	
	    	p = new Person();
	    	p.setFirstName("Kalle");
	    	p.setLastName("Anka");
	    	p.setEmail("kalle@anka.example");
	    	p.setNationality("Dutch");
	    	p = personService.addNew(p);
	    	

	    	dataCreated = Boolean.TRUE;
	    } else {
	    	log.log(Level.INFO, "Data already created");
	    }
    }
}