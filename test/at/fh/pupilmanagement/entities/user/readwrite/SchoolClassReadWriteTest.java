package at.fh.pupilmanagement.entities.user.readwrite;

import at.fh.pupilmangement.entities.SchoolClass;

public class SchoolClassReadWriteTest extends AbstractReadWriteTest<SchoolClass>
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
