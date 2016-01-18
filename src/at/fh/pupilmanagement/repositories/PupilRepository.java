package at.fh.pupilmanagement.repositories;

import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;

import at.fh.pupilmanagement.entities.Pupil;
import at.fh.pupilmanagement.models.User;

public class PupilRepository extends BaseRepository<Pupil>
{
	public PupilRepository()
	{
		super(Pupil.class);
	}
	
	public PupilRepository(User user)
	{
		super(Pupil.class, user);
	}
	
	public List<Pupil> findAllEntryThisYear()
	{
		int year = Calendar.getInstance().get(Calendar.YEAR);
		TypedQuery<Pupil> query = getEntityManager().createNamedQuery(
				"Pupil.findAllEntryThisYear", Pupil.class);
		query.setParameter("entry", year);
		
		return query.getResultList();
	}
	
	@Override
	public void deleteFromDb(Pupil pupil)
	{
		pupil.setSchoolClass(null);
		pupil = getEntityManager().merge(pupil);
		getEntityManager().remove(pupil);
		commit();
	}
}
