package at.fh.pupilmanagement.repositories;

import at.fh.pupilmanagement.entities.Pupil;
import at.fh.pupilmanagement.entities.SchoolClass;
import at.fh.pupilmanagement.models.User;

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
