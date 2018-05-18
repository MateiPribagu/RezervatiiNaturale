package repository;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import main.java.Animal;

/**
 * @author User
 *
 */
public class RepoAnimal implements IRepository<Animal, Integer> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#GetAll()
	 */
	@Override
	public ArrayList<Animal> GetAll() {
		SessionFactory sessionFactoryObj = new Configuration().configure().buildSessionFactory();
		Session sessionObj = sessionFactoryObj.openSession();
		ArrayList<Animal> anis = new ArrayList<Animal>();
		try {
			sessionObj.beginTransaction();
			anis.addAll(sessionObj.createQuery("from Animal", Animal.class).list());
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
		return anis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Get(java.lang.Object)
	 */
	@Override
	public Animal Get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Add(java.lang.Object)
	 */
	@Override
	public void Add(Animal e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Update(java.lang.Object)
	 */
	@Override
	public void Update(Animal e) {
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
