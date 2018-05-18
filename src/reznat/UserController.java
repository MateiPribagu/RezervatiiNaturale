package reznat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import main.java.*;

/**
 * @author User
 *
 */
@Controller
public class UserController {

	/**
	 * 
	 */
	ArrayList<User> users;
	/**
	 * 
	 */
	String currentUser = "Guest";
	/**
	 * 
	 */
	String currentPass = "NoYouCant";
	/**
	 * 
	 */
	String buttonValue;
	/**
	 * 
	 */
	Reservation midEdit = null;
	/**
	 * 
	 */
	ArrayList<Habitat> allhabitats;
	/**
	 * 
	 */
	ArrayList<Animal> allanimals;
	/**
	 * 
	 */
	ArrayList<Plant> allplants;
	/**
	 * 
	 */
	@Autowired
	IServyce service;

	/**
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		currentUser = "Guest";
		currentPass = "NoYouCant";
		return new ModelAndView("index", "command", new User());
	}

	/**
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("SpringWeb") User user, ModelMap model) {
		if (user.getUsername().equals("")) {
			model.addAttribute("label", "You must type a username!");
			return new ModelAndView("index", "command", new User());
		}
		if (user.getPassword().equals("")) {
			model.addAttribute("label", "You must type a password!");
			return new ModelAndView("index", "command", new User());
		}
		if (service.CheckUser(user.getUsername(), user.getPassword())) {
			model.addAttribute("username", user.getUsername());
			model.addAttribute("reslist", service.GetAllReservations());
			currentUser = user.getUsername();
			currentPass = user.getPassword();
			return new ModelAndView("main_admin", "command", new Reservation());
		} else {
			model.addAttribute("label", "Invalid username and password combination!");
			return new ModelAndView("index", "command", new User());
		}

	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public ModelAndView backAdmin(ModelMap model) {
		if (currentUser.equals("Guest") || !service.CheckUser(currentUser, currentPass)) {
			model.addAttribute("label", "YOU ABSOLUTE CHEATER!!!");
			return new ModelAndView("index", "command", new User());
		} else {
			model.addAttribute("username", currentUser);
			model.addAttribute("reslist", service.GetAllReservations());
			return new ModelAndView("main_admin", "command", new Reservation());
		}
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginGuest(ModelMap model) {
		model.addAttribute("username", "Guest");
		model.addAttribute("reslist", service.GetAllReservations());
		currentUser = "Guest";
		return new ModelAndView("main", "command", new Reservation());

	}

	/**
	 * @param reservation
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reservation", method = RequestMethod.POST)
	public ModelAndView res(@ModelAttribute("SpringWeb") Reservation reservation, ModelMap model) {
		model.addAttribute("resname", reservation.getNume());
		model.addAttribute("resloc", reservation.getLocalizare());
		Reservation realReservation = service.GetReservation(reservation.getId());
		model.addAttribute("habitats", realReservation.getHabitats());
		model.addAttribute("plants", realReservation.getPlants());
		model.addAttribute("animals", realReservation.getAnimals());
		return new ModelAndView("reservation", "command", reservation);

	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/searchAdmin", method = RequestMethod.GET)
	public ModelAndView resSearchAdmin(ModelMap model) {
		model.addAttribute("username", "Guest");
		model.addAttribute("reslist", service.GetAllReservations());
		return new ModelAndView("search_admin", "command", new SearchParams());

	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView resSearch(ModelMap model) {
		model.addAttribute("username", currentUser);
		model.addAttribute("reslist", service.GetAllReservations());
		return new ModelAndView("search", "command", new SearchParams());

	}

	/**
	 * @param sp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/searchAdmin", method = RequestMethod.POST)
	public ModelAndView resSearchAdminPost(@ModelAttribute("SpringWeb") SearchParams sp, ModelMap model) {
		model.addAttribute("username", currentUser);
		System.out.println("BONUS:::" + sp.getBonusHabitate());
		model.addAttribute("reslist", searchResult(sp.getNume(), sp.getLocalizare(), sp.getBonusHabitate(),
				sp.getBonusPlante(), sp.getBonusAnimale()));
		return new ModelAndView("search_admin", "command", sp);

	}

	/**
	 * @param sp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView resSearchPost(@ModelAttribute("SpringWeb") SearchParams sp, ModelMap model) {
		model.addAttribute("username", "Guest");
		model.addAttribute("reslist", searchResult(sp.getNume(), sp.getLocalizare(), sp.getBonusHabitate(),
				sp.getBonusPlante(), sp.getBonusAnimale()));
		return new ModelAndView("search", "command", sp);

	}

	/**
	 * @param nume
	 * @param loc
	 * @param habitat
	 * @param plant
	 * @param animal
	 * @return
	 */
	private ArrayList<Reservation> searchResult(String nume, String loc, String habitat, String plant, String animal) {
		nume = nume.trim();
		loc = loc.trim();
		nume = nume.toLowerCase();
		loc = loc.toLowerCase();
		habitat = habitat.trim();
		habitat = habitat.toLowerCase();
		plant = plant.trim();
		plant = plant.toLowerCase();
		animal = animal.trim();
		animal = animal.toLowerCase();
		ArrayList<Reservation> res = new ArrayList<Reservation>();
		for (Reservation x : service.GetAllReservations()) {
			boolean add = true;
			String xNume = x.getNume();
			String xLoc = x.getLocalizare();
			xNume = xNume.toLowerCase();
			xLoc = xLoc.toLowerCase();
			if (!xNume.contains(nume) && !nume.equals("")) {
				add = false;
			} else if (!xLoc.contains(loc) && !loc.equals("")) {
				add = false;
			}
			if (add == true && !habitat.equals("")) {
				ArrayList<String> list = new ArrayList<String>();
				x.getHabitats().forEach((y) -> {
					list.add(y.getDenumire());
				});
				add = searchAmong(list, habitat);
			}
			if (add == true && !plant.equals("")) {
				ArrayList<String> list = new ArrayList<String>();
				x.getPlants().forEach((y) -> {
					list.add(y.getSpecie());
				});
				add = searchAmong(list, plant);
			}
			if (add == true && !animal.equals("")) {
				ArrayList<String> list = new ArrayList<String>();
				x.getAnimals().forEach((y) -> {
					list.add(y.getSpecie());
				});
				add = searchAmong(list, animal);
			}
			if (add) {
				res.add(x);
			}
		}

		return res;
	}

	/**
	 * @param list
	 * @param item
	 * @return
	 */
	public boolean searchAmong(ArrayList<String> list, String item) {
		for (String s : list) {
			if (s.toLowerCase().contains(item)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param reservation
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reservationAdmin", params = { "view", "!delete", "!new" }, method = RequestMethod.POST)
	public ModelAndView resAdminView(@ModelAttribute("SpringWeb") Reservation reservation, ModelMap model) {
		model.addAttribute("resname", reservation.getNume());
		model.addAttribute("resloc", reservation.getLocalizare());
		model.addAttribute("username", currentUser);
		Reservation realReservation = service.GetReservation(reservation.getId());
		model.addAttribute("habitats", realReservation.getHabitats());
		model.addAttribute("plants", realReservation.getPlants());
		model.addAttribute("animals", realReservation.getAnimals());

		return new ModelAndView("reservation_admin", "command", reservation);
	}

	/**
	 * @param reservation
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reservationAdmin", params = { "delete", "!view", "!new" }, method = RequestMethod.POST)
	public ModelAndView resAdminDelete(@ModelAttribute("SpringWeb") Reservation reservation, ModelMap model) {
		service.DeleteReservation(reservation.getId());
		model.addAttribute("username", currentUser);
		model.addAttribute("reslist", service.GetAllReservations());
		return new ModelAndView("main_admin", "command", new Reservation());
	}

	/**
	 * @param reservation
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reservationAdmin", params = { "new", "!view", "!delete" }, method = RequestMethod.POST)
	public ModelAndView resAdminNew(@ModelAttribute("SpringWeb") Reservation reservation, ModelMap model) {
		model.addAttribute("username", currentUser);
		model.addAttribute("resname", "New Reservation");
		model.addAttribute("buttonvalue", "Create");
		buttonValue = "Create";
		allhabitats = service.GetAllHabitats();
		allanimals = service.GetAllAnimals();
		allplants = service.GetAllPlants();
		model.addAttribute("allhabitats", allhabitats);
		model.addAttribute("allplants", allplants);
		model.addAttribute("allanimals", allanimals);
		Reservation res = new Reservation();
		res.setId(-1);
		midEdit = res;
		return new ModelAndView("reservation_admin_new", "command", res);
	}

	/**
	 * @param reservation
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reservationAdmin/edit", method = RequestMethod.POST)
	public ModelAndView resAdminUpdate(@ModelAttribute("SpringWeb") Reservation reservation, ModelMap model) {
		model.addAttribute("username", currentUser);
		model.addAttribute("resname", reservation.getNume());
		model.addAttribute("buttonvalue", "Edit");
		buttonValue = "Edit";
		midEdit = reservation;
		allhabitats = service.GetAllHabitats();
		allanimals = service.GetAllAnimals();
		allplants = service.GetAllPlants();
		Reservation realReservation = service.GetReservation(reservation.getId());
		midEdit.setHabitats(realReservation.getHabitats());
		midEdit.setAnimals(realReservation.getAnimals());
		midEdit.setPlants(realReservation.getPlants());
		model.addAttribute("habitats", realReservation.getHabitats());
		model.addAttribute("plants", realReservation.getPlants());
		model.addAttribute("animals", realReservation.getAnimals());
		realReservation.getHabitats().forEach((x) -> {
			allhabitats.remove(x);
		});
		realReservation.getAnimals().forEach((x) -> {
			allanimals.remove(x);
		});
		realReservation.getPlants().forEach((x) -> {
			allplants.remove(x);
		});
		model.addAttribute("allhabitats", allhabitats);
		model.addAttribute("allplants", allplants);
		model.addAttribute("allanimals", allanimals);
		return new ModelAndView("reservation_admin_new", "command", reservation);
	}

	/**
	 * @param reservation
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reservationAdmin/create", method = RequestMethod.POST)
	public ModelAndView resAdminCreate(@ModelAttribute("SpringWeb") Reservation reservation, ModelMap model) {
		model.addAttribute("username", currentUser);
		if (reservation.getLocalizare().equals("")) {
			model.addAttribute("label", "You must enter a Location!");
		}
		if (reservation.getNume().equals("")) {
			model.addAttribute("label", "You must enter a Reservation Name!");
		}
		if (reservation.getLocalizare().equals("") || reservation.getNume().equals("")) {
			model.addAttribute("buttonvalue", buttonValue);
			model.addAttribute("resname", midEdit.getNume());
			model.addAttribute("buttonvalue", buttonValue);
			model.addAttribute("resname", midEdit.getNume());
			model.addAttribute("habitats", midEdit.getHabitats());
			model.addAttribute("plants", midEdit.getPlants());
			model.addAttribute("animals", midEdit.getAnimals());
			model.addAttribute("allhabitats", allhabitats);
			model.addAttribute("allplants", allplants);
			model.addAttribute("allanimals", allanimals);
			return new ModelAndView("reservation_admin_new", "command", midEdit);
		}
		reservation.setHabitats(midEdit.getHabitats());
		reservation.setAnimals(midEdit.getAnimals());
		reservation.setPlants(midEdit.getPlants());
		if (reservation.getId() == -1) {
			service.AddReservation(reservation);
		} else {
			service.UpdateReservation(reservation);
		}
		midEdit = null;
		allhabitats = null;
		allanimals = null;
		allplants = null;
		model.addAttribute("reslist", service.GetAllReservations());
		return new ModelAndView("main_admin", "command", new Reservation());
	}

	/**
	 * @param id
	 * @param denumire
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reservationAdmin/linkHabitat", params = { "id", "denumire" }, method = RequestMethod.GET)
	public ModelAndView resAdminLinkHabitat(@RequestParam(value = "id") int id,
			@RequestParam(value = "denumire") String denumire, ModelMap model) {
		Habitat habitat = new Habitat();
		habitat.setId(id);
		habitat.setDenumire(denumire);
		midEdit.getHabitats().add(habitat);
		allhabitats.remove(habitat);
		model.addAttribute("username", currentUser);
		model.addAttribute("buttonvalue", buttonValue);
		model.addAttribute("resname", midEdit.getNume());
		model.addAttribute("allhabitats", allhabitats);
		model.addAttribute("allplants", allplants);
		model.addAttribute("allanimals", allanimals);
		model.addAttribute("habitats", midEdit.getHabitats());
		model.addAttribute("plants", midEdit.getPlants());
		model.addAttribute("animals", midEdit.getAnimals());
		return new ModelAndView("reservation_admin_new", "command", midEdit);
	}

	/**
	 * @param id
	 * @param specie
	 * @param denumire
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reservationAdmin/linkPlant", params = { "id", "specie",
			"denumire" }, method = RequestMethod.GET)
	public ModelAndView resAdminLinkPlant(@RequestParam(value = "id") int id,
			@RequestParam(value = "specie") String specie, @RequestParam(value = "denumire") String denumire,
			ModelMap model) {
		Plant plant = new Plant();
		plant.setId(id);
		plant.setSpecie(specie);
		plant.setDenumireLatina(denumire);
		midEdit.getPlants().add(plant);
		allplants.remove(plant);
		model.addAttribute("username", currentUser);
		model.addAttribute("buttonvalue", buttonValue);
		model.addAttribute("resname", midEdit.getNume());
		model.addAttribute("allhabitats", allhabitats);
		model.addAttribute("allplants", allplants);
		model.addAttribute("allanimals", allanimals);
		model.addAttribute("habitats", midEdit.getHabitats());
		model.addAttribute("plants", midEdit.getPlants());
		model.addAttribute("animals", midEdit.getAnimals());
		return new ModelAndView("reservation_admin_new", "command", midEdit);
	}

	/**
	 * @param id
	 * @param specie
	 * @param denumire
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reservationAdmin/linkAnimal", params = { "id", "specie",
			"denumire" }, method = RequestMethod.GET)
	public ModelAndView resAdminLinkAnimal(@RequestParam(value = "id") int id,
			@RequestParam(value = "specie") String specie, @RequestParam(value = "denumire") String denumire,
			ModelMap model) {
		Animal animal = new Animal();
		animal.setId(id);
		animal.setSpecie(specie);
		animal.setDenumireLatina(denumire);
		midEdit.getAnimals().add(animal);
		allanimals.remove(animal);
		model.addAttribute("username", currentUser);
		model.addAttribute("buttonvalue", buttonValue);
		model.addAttribute("resname", midEdit.getNume());
		model.addAttribute("allhabitats", allhabitats);
		model.addAttribute("allplants", allplants);
		model.addAttribute("allanimals", allanimals);
		model.addAttribute("habitats", midEdit.getHabitats());
		model.addAttribute("plants", midEdit.getPlants());
		model.addAttribute("animals", midEdit.getAnimals());
		return new ModelAndView("reservation_admin_new", "command", midEdit);
	}

	/**
	 * @param reservation
	 * @param response
	 */
	@RequestMapping(value = "/download" , produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, method = RequestMethod.POST)
	public void download(@ModelAttribute("SpringWeb") Reservation reservation, HttpServletResponse response) {
		ArrayList<String> output = new ArrayList<String>();
		output.add(reservation.getNume());
		output.add(reservation.getLocalizare());
		Reservation realReservation = service.GetReservation(reservation.getId());
		output.add("=== Habitate ===");
		realReservation.getHabitats().forEach((x)->{output.add(x.getDenumire());});
		output.add("=== Plante ===");
		realReservation.getPlants().forEach((x)->{output.add(x.getSpecie());});
		output.add("=== Animale ===");
		realReservation.getAnimals().forEach((x)->{output.add(x.getSpecie());});
		java.nio.file.Path path = Paths.get("C:/Users/User/Fortech/" + reservation.getNume() + ".txt");
		try {
			if (!Files.exists(path)) {
				Files.createFile(path);
			}
			Files.write(path, output, Charset.forName("UTF-8"));
			InputStream is = new FileInputStream(path.toString());
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment; filename=" + reservation.getNume()+".txt");
			org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
