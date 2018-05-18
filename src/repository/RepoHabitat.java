package repository;

import java.util.ArrayList;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import main.java.Habitat;
import main.java.Reservation;

/**
 * @author User
 *
 */
public class RepoHabitat implements IRepository<Habitat, Integer> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#GetAll()
	 */
	@Override
	public ArrayList<Habitat> GetAll() {
		SessionFactory sessionFactoryObj = new Configuration().configure().buildSessionFactory();
		Session sessionObj = sessionFactoryObj.openSession();
		ArrayList<Habitat> habs = new ArrayList<Habitat>();
		try {
			sessionObj.beginTransaction();
			habs.addAll(sessionObj.createQuery("from Habitat", Habitat.class).list());
			sessionObj.getTransaction().commit();
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
		return habs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Get(java.lang.Object)
	 */
	@Override
	public Habitat Get(Integer id) {
		SessionFactory sessionFactoryObj = new Configuration().configure().buildSessionFactory();
		Session sessionObj = sessionFactoryObj.openSession();
		Habitat habitat = null;
		try {
			sessionObj.beginTransaction();
			habitat = sessionObj.get(Habitat.class, id);
			// Hibernate.initialize(habitat.getReservations());
			sessionObj.getTransaction().commit();
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
		return habitat;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Add(java.lang.Object)
	 */
	@Override
	public void Add(Habitat e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Update(java.lang.Object)
	 */
	@Override
	public void Update(Habitat e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Delete(int)
	 */
	@Override
	public void Delete(int id) {
		// TODO Auto-generated method stub

	}

}
