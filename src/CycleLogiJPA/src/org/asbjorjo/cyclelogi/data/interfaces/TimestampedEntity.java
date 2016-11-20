package org.asbjorjo.cyclelogi.data.interfaces;

import org.asbjorjo.cyclelogi.data.embed.EntityTimestamp;

public interface TimestampedEntity {
	public EntityTimestamp getTimestamp();
	
	public void setTimestamp(EntityTimestamp entityTimestamp);
}
