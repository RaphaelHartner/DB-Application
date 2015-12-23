package at.fh.pupilmanagement.entities.user.admin;

import java.util.GregorianCalendar;

import at.fh.pupilmangement.entities.Teacher;

public class TeacherAdminTest extends AbstractAdminTest<Teacher>
{
	@Override
	protected Class<Teacher> getEntityClass()
	{
		return Teacher.class;
	}

	@Override
	protected Teacher getTemplateEntity()
	{
		return new Teacher("Raphael", "Hartner", new GregorianCalendar(1994, 4, 23).getTime(), "HarR");
	}

	@Override
	protected void modifyEntity(Teacher entity)
	{
		entity.setAbbreviation("HartRap");
	}

	@Override
	protected String getSequenceName()
	{
		return Teacher.getSequenceName();
	}

}
