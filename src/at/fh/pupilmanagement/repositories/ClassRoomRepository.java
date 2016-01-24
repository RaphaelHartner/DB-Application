package at.fh.pupilmanagement.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import at.fh.pupilmanagement.entities.ClassRoom;
import at.fh.pupilmanagement.entities.Pupil;
import at.fh.pupilmanagement.entities.SchoolClass;
import at.fh.pupilmanagement.models.User;

public class ClassRoomRepository extends BaseRepository<ClassRoom>
{
	public ClassRoomRepository()
	{
		super(ClassRoom.class);
	}
	
	public ClassRoomRepository(User user)
	{
		super(ClassRoom.class, user);
	}
	
	public List<SchoolClass> findSchoolClassesWithClassRoomInBuilding (String building)
	{
		TypedQuery<SchoolClass> query = getEntityManager().createNamedQuery(
				"SchoolClass.findSchoolClassesWithClassRoomInBuilding", SchoolClass.class);
		query.setParameter("building", building);
		
		return new ArrayList<SchoolClass>(query.getResultList());
	}
	
	public List<Pupil> findAllPupils (long id)
	{
		TypedQuery<Pupil> query = getEntityManager().createNamedQuery(
				"Pupil.findAllPupils", Pupil.class);
		query.setParameter("id", id);
		
		return new ArrayList<Pupil>(query.getResultList());
	}
	
	@Override
	public void deleteFromDb(ClassRoom classRoom)
	{
		classRoom.setMainClass(null);
		classRoom = getEntityManager().merge(classRoom);
		getEntityManager().remove(classRoom);
		commit();
	}
}
