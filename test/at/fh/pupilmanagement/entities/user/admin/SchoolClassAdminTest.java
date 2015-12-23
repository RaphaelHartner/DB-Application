package at.fh.pupilmanagement.entities.user.admin;

import at.fh.pupilmangement.entities.SchoolClass;

public class SchoolClassAdminTest extends AbstractAdminTest<SchoolClass>
{
	@Override
	protected Class<SchoolClass> getEntityClass()
	{
		return SchoolClass.class;
	}
	
	@Override
	protected SchoolClass getTemplateEntity()
	{
		return new SchoolClass("BHMI",2);
	}
	
	@Override
	protected void modifyEntity(SchoolClass entity)
	{
		entity.setGrade(4);;
	}

	@Override
	protected String getSequenceName()
	{
		return SchoolClass.getSequenceName();
	}

}
