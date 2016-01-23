package at.fh.pupilmanagement.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import at.fh.pupilmanagement.models.User;
import at.fh.pupilmangement.entities.Pupil;
import at.fh.pupilmangement.entities.Room;
import at.fh.pupilmangement.entities.SchoolClass;

public class SchoolClassRepository extends BaseRepository<SchoolClass>
{
		public SchoolClassRepository()
		{
			super(SchoolClass.class);
		}
		
		public SchoolClassRepository(User user)
		{
			super(SchoolClass.class, user);
		}
		
		public List<Room> findSuitableRooms(long id)
		{
			TypedQuery<Room> query = getEntityManager().createNamedQuery(
					"Room.findSuitableRooms", Room.class);
			query.setParameter("id", id);
			
			return new ArrayList<Room>(query.getResultList());
		}
		
		@Override
		public void deleteFromDb(SchoolClass schoolClass)
		{
			for (Pupil pupil : schoolClass.getPupils())
			{
				pupil.setSchoolClass(null);
			}
			schoolClass.setClassTeacher(null);
			schoolClass.setClassRoom(null);
			schoolClass = getEntityManager().merge(schoolClass);
			getEntityManager().remove(schoolClass);
			commit();
		}
}
