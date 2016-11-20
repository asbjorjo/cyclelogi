package org.asbjorjo.cyclelogi.data.embed;

import java.util.Calendar;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

@Embeddable
public class DatePeriod {
	@Temporal(DATE)
	private Calendar startDate;
	@Temporal(DATE)
	private Calendar endDate;
	
	public Calendar getStartDate() {
		return startDate;
	}
	public void setStartDate(Calendar startTime) {
		this.startDate = startTime;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Calendar endTime) {
		this.endDate = endTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatePeriod other = (DatePeriod) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
}
