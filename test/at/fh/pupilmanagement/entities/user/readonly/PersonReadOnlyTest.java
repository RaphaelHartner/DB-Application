package at.fh.pupilmanagement.entities.user.readonly;

import java.util.GregorianCalendar;

import at.fh.pupilmangement.entities.Person;

public class PersonReadOnlyTest extends AbstractReadOnlyTest<Person>
{
	@Override
	protected Class<Person> getEntityClass()
	{
		return Person.class;
	}
	
	@Override
	protected Person getTemplateEntity()
	{
		return new Person("Raphael", "Hartner", new GregorianCalendar(1994, 4, 23).getTime());
	}
	
	@Override
	protected void modifyEntity(Person entity)
	{
		entity.setFirstName("Georg");
	}

	@Override
	protected String getSequenceName()
	{
		return Person.getSequenceName();
	}

}
