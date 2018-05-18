package main.java;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author User
 *
 */
@Entity
@Table(name = "monumenteistorice")
public class Monument {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	/**
	 * 
	 */
	@Column(name = "Denumire")
	private String Denumire;

	/**
	 * 
	 */
	@Column(name = "PerioadaConstructie")
	private String PerioadaConstructie;

	/**
	 * 
	 */
	@ManyToMany(mappedBy = "monuments")
	private Set<Reservation> reservations = new HashSet<>();

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getDenumire() {
		return Denumire;
	}

	/**
	 * @param denumire
	 */
	public void setDenumire(String denumire) {
		Denumire = denumire;
	}

	/**
	 * @return
	 */
	public String getPerioadaConstructie() {
		return PerioadaConstructie;
	}

	/**
	 * @param perioadaConstructie
	 */
	public void setPerioadaConstructie(String perioadaConstructie) {
		PerioadaConstructie = perioadaConstructie;
	}

	/**
	 * @return
	 */
	public Set<Reservation> getReservations() {
		return reservations;
	}

	/**
	 * @param reservations
	 */
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Monument other = (Monument) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
