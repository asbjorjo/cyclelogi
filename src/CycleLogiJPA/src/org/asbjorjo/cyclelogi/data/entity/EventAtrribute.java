package org.asbjorjo.cyclelogi.data.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: EventAtrribute
 *
 */
@Entity
public class EventAtrribute implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String value;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}