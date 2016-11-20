package org.asbjorjo.cyclelogi.data.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import org.asbjorjo.cyclelogi.data.embed.EntityTimestamp;
import org.asbjorjo.cyclelogi.data.embed.TimePeriod;
import org.asbjorjo.cyclelogi.data.interfaces.TimestampedEntity;
import org.asbjorjo.cyclelogi.data.listener.TimestampEntityListener;

/**
 * Entity implementation class for Entity: Event
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@EntityListeners(TimestampEntityListener.class)
@NamedQuery(name="findAllEvents", query="SELECT e from Event e ORDER BY e.time.startTime, e.time.endTime")
public abstract class Event implements Serializable, TimestampedEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private TimePeriod time;
	@OneToMany
	@MapKey(name = "name")
	private Map<String, EventAtrribute> attributes;
	@ManyToMany
	private List<Person> participants;
	private EntityTimestamp timestamp;
	
	public Event() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TimePeriod getTime() {
		return time;
	}

	public void setTime(TimePeriod time) {
		this.time = time;
	}

	public Map<String, EventAtrribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, EventAtrribute> attributes) {
		this.attributes = attributes;
	}

	public List<Person> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Person> participants) {
		this.participants = participants;
	}

	public EntityTimestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(EntityTimestamp timestamp) {
		this.timestamp = timestamp;
	}
}