package main.java;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author User
 *
 */
@Entity
@Table(name = "rezervatiinaturale")
public class Reservation {
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
	@Column(name = "Nume")
	private String Nume;

	/**
	 * 
	 */
	@Column(name = "Localizare")
	private String Localizare;

	/**
	 * 
	 */
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "rezervatiihabitate", joinColumns = { @JoinColumn(name = "IDR") }, inverseJoinColumns = {
			@JoinColumn(name = "IDH") })
	Set<Habitat> habitats = new HashSet<>();

	/**
	 * 
	 */
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "rezervatiianimale", joinColumns = { @JoinColumn(name = "IDR") }, inverseJoinColumns = {
			@JoinColumn(name = "IDA") })
	Set<Animal> animals = new HashSet<>();

	/**
	 * 
	 */
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "rezervatiiplante", joinColumns = { @JoinColumn(name = "IDR") }, inverseJoinColumns = {
			@JoinColumn(name = "IDP") })
	Set<Plant> plants = new HashSet<>();

	/**
	 * 
	 */
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "rezervatiimonumente", joinColumns = { @JoinColumn(name = "IDR") }, inverseJoinColumns = {
			@JoinColumn(name = "IDM") })
	Set<Plant> monuments = new HashSet<>();

	/**
	 * 
	 */
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "rezervatiiarii", joinColumns = { @JoinColumn(name = "IDR") }, inverseJoinColumns = {
			@JoinColumn(name = "IDA") })
	Set<Plant> areas = new HashSet<>();

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
	public String getNume() {
		return Nume;
	}

	/**
	 * @param nume
	 */
	public void setNume(String nume) {
		Nume = nume;
	}

	/**
	 * @return
	 */
	public String getLocalizare() {
		return Localizare;
	}

	/**
	 * @param localizare
	 */
	public void setLocalizare(String localizare) {
		Localizare = localizare;
	}

	/**
	 * @return
	 */
	public Set<Habitat> getHabitats() {
		return habitats;
	}

	/**
	 * @param habitats
	 */
	public void setHabitats(Set<Habitat> habitats) {
		this.habitats = habitats;
	}

	/**
	 * @return
	 */
	public Set<Animal> getAnimals() {
		return animals;
	}

	/**
	 * @param animals
	 */
	public void setAnimals(Set<Animal> animals) {
		this.animals = animals;
	}

	/**
	 * @return
	 */
	public Set<Plant> getPlants() {
		return plants;
	}

	/**
	 * @param plants
	 */
	public void setPlants(Set<Plant> plants) {
		this.plants = plants;
	}

	/**
	 * @return
	 */
	public Set<Plant> getMonuments() {
		return monuments;
	}

	/**
	 * @param monuments
	 */
	public void setMonuments(Set<Plant> monuments) {
		this.monuments = monuments;
	}

	/**
	 * @return
	 */
	public Set<Plant> getAreas() {
		return areas;
	}

	/**
	 * @param areas
	 */
	public void setAreas(Set<Plant> areas) {
		this.areas = areas;
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
		Reservation other = (Reservation) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
