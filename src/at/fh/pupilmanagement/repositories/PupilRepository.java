package at.fh.pupilmanagement.repositories;

import at.fh.pupilmanagement.models.User;
import at.fh.pupilmangement.entities.Pupil;

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
	
	@Override
	public void deleteFromDb(Pupil pupil)
	{
		pupil.getSchoolClass().getPupils().remove(pupil);
		pupil.setSchoolClass(null);
		pupil = getEntityManager().merge(pupil);
		getEntityManager().remove(pupil);
		commit();
	}
}
