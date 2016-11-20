package org.asbjorjo.cyclelogi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.asbjorjo.cyclelogi.data.entity.Role;

/**
 * Session Bean implementation class RoleService
 */
@Stateless
@LocalBean
public class RoleService {
	private static final Logger log = Logger.getLogger( PersonService.class.getName() );

	@PersistenceContext
	private EntityManager em;

    public Role addNew(Role role) {
    	em.persist(role);
    	return role;
    }
    
    public Role update(Role role) {
    	return em.merge(role);
    }
    
    public void deleteRole(Role role) {
    	if (!em.contains(role)) {
    		role = em.getReference(Role.class, role.getId());
    	}
    	role.clearRelations();

    	em.remove(role);
    }
    
    public List<Role> getAllRoles() {
    	TypedQuery<Role> query = em.createNamedQuery("findAllRoles", Role.class);
//    	query.setHint("javax.persistence.cache.storeMode", "REFRESH");
    	return query.getResultList();
    }
    
    public Role getRoleByName(String name) {
    	TypedQuery<Role> query = em.createNamedQuery("findRoleByName", Role.class);
    	query.setParameter("name", name);
    	return query.getSingleResult();
    }
    
    public Role getRoleById(Long id) {
    	Role role = em.find(Role.class, id);
    	return role;
    }

	public List<Role> getRolesByName(List<String> roleNames) {
		if (roleNames.isEmpty()) {
			return new ArrayList<>();
		} else {
			TypedQuery<Role> query = em.createQuery("SELECT r from Role r WHERE r.name IN :roleNames", Role.class);
			query.setParameter("roleNames", roleNames);
			List<Role> roles = query.getResultList();
			return roles;
		}
	}
}
