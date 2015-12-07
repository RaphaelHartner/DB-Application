package at.fh.pupilmangement.entities;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

	
	public abstract long getId();
}
