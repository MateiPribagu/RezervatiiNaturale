package reznat;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import main.java.Animal;
import main.java.Area;
import main.java.Habitat;
import main.java.Monument;
import main.java.Plant;
import main.java.Reservation;
import main.java.User;
import repository.*;

/**
 * @author User
 *
 */
@Service("service")
public class Servyce implements IServyce {
	/**
	 * 
	 */
	IRepository<User, String> repoUser;
	/**
	 * 
	 */
	IRepository<Reservation, Integer> repoReservation;
	/**
	 * 
	 */
	IRepository<Habitat, Integer> repoHabitat;
	/**
	 * 
	 */
	IRepository<Plant, Integer> repoPlant;
	/**
	 * 
	 */
	IRepository<Animal, Integer> repoAnimal;

	/**
	 * 
	 */
	public Servyce() {
		repoUser = new RepoUser();
		repoReservation = new RepoReservation();
		repoHabitat = new RepoHabitat();
		repoPlant = new RepoPlant();
		repoAnimal = new RepoAnimal();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see reznat.IServyce#CheckUser(java.lang.String, java.lang.String)
	 */
	public boolean CheckUser(String username, String password) {
		User user = repoUser.Get(username);
		if (user == null)
			return false;
		return password.equals(user.getPassword());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see reznat.IServyce#GetAllReservations()
	 */
	public ArrayList<Reservation> GetAllReservations() {
		return repoReservation.GetAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see reznat.IServyce#GetAllHabitats()
	 */
	public ArrayList<Habitat> GetAllHabitats() {
		return repoHabitat.GetAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see reznat.IServyce#GetAllAnimals()
	 */
	@Override
	public ArrayList<Animal> GetAllAnimals() {
		return repoAnimal.GetAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see reznat.IServyce#GetAllPlants()
	 */
	@Override
	public ArrayList<Plant> GetAllPlants() {
		return repoPlant.GetAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see reznat.IServyce#GetAllMonuments()
	 */
	@Override
	public ArrayList<Monument> GetAllMonuments() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see reznat.IServyce#GetAllAreas()
	 */
	@Override
	public ArrayList<Area> GetAllAreas() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see reznat.IServyce#DeleteReservation(int)
	 */
	@Override
	public void DeleteReservation(int id) {
		repoReservation.Delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see reznat.IServyce#AddReservation(main.java.Reservation)
	 */
	@Override
	public void AddReservation(Reservation reservation) {
		repoReservation.Add(reservation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see reznat.IServyce#GetReservation(int)
	 */
	@Override
	public Reservation GetReservation(int id) {
		return repoReservation.Get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see reznat.IServyce#UpdateReservation(main.java.Reservation)
	 */
	@Override
	public void UpdateReservation(Reservation reservation) {
		repoReservation.Update(reservation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see reznat.IServyce#GetHabitat(int)
	 */
	@Override
	public Habitat GetHabitat(int id) {
		return repoHabitat.Get(id);
	}

}
