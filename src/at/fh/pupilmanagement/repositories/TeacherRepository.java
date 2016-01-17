package at.fh.pupilmanagement.repositories;

import at.fh.pupilmanagement.models.User;
import at.fh.pupilmangement.entities.SchoolClass;
import at.fh.pupilmangement.entities.Teacher;

public class TeacherRepository extends BaseRepository<Teacher>
{
	public TeacherRepository()
	{
		super(Teacher.class);
	}
	
	public TeacherRepository(User user)
	{
		super(Teacher.class, user);
	}
	
	@Override
	public void deleteFromDb(Teacher teacher)
	{
		for (SchoolClass schoolClass : teacher.getSchoolClasses())
		{
			schoolClass.getTeachers().remove(teacher);
		}
		teacher.setMainClass(null);
		teacher = getEntityManager().merge(teacher);
		getEntityManager().remove(teacher);
		commit();
	}
}
