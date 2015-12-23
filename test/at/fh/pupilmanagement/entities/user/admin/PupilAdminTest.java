package at.fh.pupilmanagement.entities.user.admin;

import java.util.GregorianCalendar;

import at.fh.pupilmangement.entities.Pupil;

public class PupilAdminTest extends AbstractAdminTest<Pupil>
{
	@Override
	protected Class<Pupil> getEntityClass()
	{
		return Pupil.class;
	}
	
	@Override
	protected Pupil getTemplateEntity()
	{
		return new Pupil("Raphael", "Hartner", new GregorianCalendar(1994, 4, 23).getTime(), (short)2012);
	}
	
	@Override
	protected void modifyEntity(Pupil entity)
	{
		entity.setYearOfEntry((short)2013);
	}

	@Override
	protected String getSequenceName()
	{
		return Pupil.getSequenceName();
	}

}
