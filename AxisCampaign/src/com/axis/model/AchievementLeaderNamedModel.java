package com.axis.model;

public class AchievementLeaderNamedModel {

   private String header;

public String getHeader() {
	return header;
}

public void setHeader(String header) {
	this.header = header;
}

@Override
public int hashCode() {
	return 1;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	AchievementLeaderNamedModel other = (AchievementLeaderNamedModel) obj;
	if (header == null) {
		if (other.header != null)
			return false;
	} else if (!header.equals(other.header))
		return false;
	return true;
}

}
