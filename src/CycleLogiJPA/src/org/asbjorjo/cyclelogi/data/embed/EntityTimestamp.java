package org.asbjorjo.cyclelogi.data.embed;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EntityTimestamp {
	@Column(insertable = true, updatable = false)
	private Timestamp created;	
	@Column(insertable = false, updatable = true)
	private Timestamp updated;
	
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
}
