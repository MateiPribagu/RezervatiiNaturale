package repository;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import main.java.Plant;

/**
 * @author User
 *
 */
public class RepoPlant implements IRepository<Plant, Integer> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#GetAll()
	 */
	@Override
	public ArrayList<Plant> GetAll() {
		SessionFactory sessionFactoryObj = new Configuration().configure().buildSessionFactory();
		Session sessionObj = sessionFactoryObj.openSession();
		ArrayList<Plant> plants = new ArrayList<Plant>();
		try {
			sessionObj.beginTransaction();
			plants.addAll(sessionObj.createQuery("from Plant", Plant.class).list());
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
		return plants;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Get(java.lang.Object)
	 */
	@Override
	public Plant Get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Add(java.lang.Object)
	 */
	@Override
	public void Add(Plant e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Update(java.lang.Object)
	 */
	@Override
	public void Update(Plant e) {
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
