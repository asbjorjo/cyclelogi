package org.asbjorjo.cyclelogi.data.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import org.asbjorjo.cyclelogi.data.entity.Event;

/**
 * Entity implementation class for Entity: RaceEvent
 *
 */
@Entity

public class RaceEvent extends Event implements Serializable {

	
	private String name;
	private String url;
	private static final long serialVersionUID = 1L;

	public RaceEvent() {
		super();
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
   
}
