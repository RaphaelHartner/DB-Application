package at.fh.pupilmanagement.entities.user.readwrite;

import java.util.GregorianCalendar;

import at.fh.pupilmangement.entities.Teacher;

public class TeacherReadWriteTest extends AbstractReadWriteTest<Teacher>
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
