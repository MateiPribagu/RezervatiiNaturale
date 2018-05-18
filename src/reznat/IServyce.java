package reznat;

import java.util.ArrayList;

import main.java.*;

/**
 * @author User
 *
 */
public interface IServyce {
	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean CheckUser(String username, String password);

	/**
	 * @return
	 */
	public ArrayList<Reservation> GetAllReservations();

	/**
	 * @return
	 */
	public ArrayList<Habitat> GetAllHabitats();

	/**
	 * @return
	 */
	public ArrayList<Animal> GetAllAnimals();

	/**
	 * @return
	 */
	public ArrayList<Plant> GetAllPlants();

	/**
	 * @return
	 */
	public ArrayList<Monument> GetAllMonuments();

	/**
	 * @return
	 */
	public ArrayList<Area> GetAllAreas();

	/**
	 * @param id
	 */
	public void DeleteReservation(int id);

	/**
	 * @param reservation
	 */
	public void AddReservation(Reservation reservation);

	/**
	 * @param reservation
	 */
	public void UpdateReservation(Reservation reservation);

	/**
	 * @param id
	 * @return
	 */
	public Reservation GetReservation(int id);

	/**
	 * @param id
	 * @return
	 */
	public Habitat GetHabitat(int id);
}
