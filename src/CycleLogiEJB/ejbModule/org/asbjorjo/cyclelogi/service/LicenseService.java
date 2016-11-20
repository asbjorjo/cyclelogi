package org.asbjorjo.cyclelogi.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.asbjorjo.cyclelogi.data.entity.License;
import org.asbjorjo.cyclelogi.data.entity.PersonRole;

/**
 * Session Bean implementation class LicenseService
 */
@Stateless
@LocalBean
public class LicenseService {
	private static final Logger log = Logger.getLogger( PersonService.class.getName() );

	@PersistenceContext
	private EntityManager em;

    public License addNew(License license) {
    	if (license.getPersonRole().getId() != null) {
    		PersonRole personRole = em.find(PersonRole.class, license.getPersonRole().getId());
    		license.setPersonRole(personRole);
    		personRole.getLicenses().add(license);
    	}

    	em.persist(license);
    	
    	return license;
    }
    
    public List<License> getAllLicenses() {
    	TypedQuery<License> query = em.createNamedQuery("findAllLicenses", License.class);
    	log.log(Level.INFO, "Licenses in DB: " + query.getResultList());
    	return query.getResultList();
    }
}
