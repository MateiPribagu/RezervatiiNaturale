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
@Table(name = "animale")
public class Animal {
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
	@Column(name = "Specie")
	private String Specie;

	/**
	 * 
	 */
	@Column(name = "DenumireLatina")
	private String DenumireLatina;

	/**
	 * 
	 */
	@ManyToMany(mappedBy = "animals")
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
	public String getSpecie() {
		return Specie;
	}

	/**
	 * @param specie
	 */
	public void setSpecie(String specie) {
		Specie = specie;
	}

	/**
	 * @return
	 */
	public String getDenumireLatina() {
		return DenumireLatina;
	}

	/**
	 * @param denumireLatina
	 */
	public void setDenumireLatina(String denumireLatina) {
		DenumireLatina = denumireLatina;
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
		Animal other = (Animal) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
