package at.fh.pupilmanagement.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "pupil")
@DiscriminatorValue(value = "pupil")
@NamedQueries({
		@NamedQuery(name = "Pupil.findAllEntryThisYear", 
				query = "SELECT p FROM Pupil p "
					+ "WHERE p.yearOfEntry = :entry"),
		@NamedQuery(name = "Pupil.findAllClassRepeater", 
			query = "SELECT p FROM Pupil p JOIN p.schoolClass c "
				+ "WHERE (:currentYear - p.yearOfEntry) >= 4 AND "
				+ "c.grade < 4"),
})
public class Pupil extends Person {

	private short yearOfEntry;
	@ManyToOne
	private SchoolClass schoolClass;

	public Pupil() {
	}

	public Pupil(String firstName, String lastName, Date birthDate,
			short yearOfEntry) {
		super(firstName, lastName, birthDate);
		setYearOfEntry(yearOfEntry);
	}

	public Pupil(String firstName, String lastName, Date birthDate,
			short yearOfEntry, SchoolClass schoolClass) {
		this(firstName, lastName, birthDate, yearOfEntry);
		setSchoolClass(schoolClass);
	}

	public SchoolClass getSchoolClass() {
		return schoolClass;
	}

	public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
		if (schoolClass != null) {
			this.schoolClass.addPupil(this);
		}
	}

	public short getYearOfEntry() {
		return yearOfEntry;
	}

	public void setYearOfEntry(short yearOfEntry) {
		this.yearOfEntry = yearOfEntry;
	}

	@Override
	public BaseEntity createClonedEntity() {
		return new Pupil(getFirstName(), getLastName(), getBirthDate(),
				getYearOfEntry());
	}

	@Override
	public String toString() {
		return super.toString() + ", " + getYearOfEntry();
	}
}
