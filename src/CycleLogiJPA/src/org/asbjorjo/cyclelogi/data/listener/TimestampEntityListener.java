package org.asbjorjo.cyclelogi.data.listener;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.asbjorjo.cyclelogi.data.embed.EntityTimestamp;
import org.asbjorjo.cyclelogi.data.interfaces.TimestampedEntity;

public class TimestampEntityListener {
	@PrePersist
	void onCreate(Object entity) {
		if (entity instanceof TimestampedEntity) {
			TimestampedEntity timestampedEntity = (TimestampedEntity) entity;
			if (timestampedEntity.getTimestamp() == null) {
				timestampedEntity.setTimestamp(new EntityTimestamp());
			}
			timestampedEntity.getTimestamp().setCreated(new Timestamp((new Date()).getTime()));
		}
	}
	
	@PreUpdate
	void onUpdate(Object entity) {
		if (entity instanceof TimestampedEntity) {
			TimestampedEntity timestampedEntity = (TimestampedEntity) entity;
			if (timestampedEntity.getTimestamp() == null) {
				timestampedEntity.setTimestamp(new EntityTimestamp());
			}
			timestampedEntity.getTimestamp().setUpdated(new Timestamp((new Date()).getTime()));
		}
	}
}
