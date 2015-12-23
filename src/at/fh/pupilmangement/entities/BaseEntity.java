package at.fh.pupilmangement.entities;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

	
	public abstract long getId();
	
	/**
	 * creates an entity which has the same values like the given
	 * But the Id isn't the same!
	 * without relations like n:m or 1:n 
	 */
	public abstract BaseEntity createClonedEntity();
}
