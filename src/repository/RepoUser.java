package repository;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import main.java.User;

/**
 * @author User
 *
 */
public class RepoUser implements IRepository<User, String> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#GetAll()
	 */
	@Override
	public ArrayList<User> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Get(java.lang.Object)
	 */
	@Override
	public User Get(String id) {
		SessionFactory sessionFactoryObj = new Configuration().configure().buildSessionFactory();
		Session sessionObj = sessionFactoryObj.openSession();
		User user = null;
		try {
			sessionObj.beginTransaction();
			user = sessionObj.get(User.class, new String(id));
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
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Add(java.lang.Object)
	 */
	@Override
	public void Add(User e) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Update(java.lang.Object)
	 */
	@Override
	public void Update(User e) {
		// TODO Auto-generated method stub

	}
}
