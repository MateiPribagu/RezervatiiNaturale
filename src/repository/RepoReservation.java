package repository;

import java.util.ArrayList;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import main.java.Reservation;

/**
 * @author User
 *
 */
public class RepoReservation implements IRepository<Reservation, Integer> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#GetAll()
	 */
	@Override
	public ArrayList<Reservation> GetAll() {
		SessionFactory sessionFactoryObj = new Configuration().configure().buildSessionFactory();
		Session sessionObj = sessionFactoryObj.openSession();
		ArrayList<Reservation> res = new ArrayList<Reservation>();
		try {
			sessionObj.beginTransaction();
			res.addAll(sessionObj.createQuery("from Reservation", Reservation.class).list());
			res.forEach((x) -> {
				System.out.println(x.getHabitats().size());
				System.out.println(x.getAnimals().size());
				System.out.println(x.getPlants().size());
			});
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
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Get(java.lang.Object)
	 */
	@Override
	public Reservation Get(Integer id) {
		SessionFactory sessionFactoryObj = new Configuration().configure().buildSessionFactory();
		Session sessionObj = sessionFactoryObj.openSession();
		Reservation reservation = null;
		try {
			sessionObj.beginTransaction();
			reservation = sessionObj.get(Reservation.class, id);
			Hibernate.initialize(reservation.getHabitats());
			Hibernate.initialize(reservation.getAnimals());
			Hibernate.initialize(reservation.getPlants());
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
		return reservation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Add(java.lang.Object)
	 */
	@Override
	public void Add(Reservation e) {
		SessionFactory sessionFactoryObj = new Configuration().configure().buildSessionFactory();
		Session sessionObj = sessionFactoryObj.openSession();
		try {
			sessionObj.beginTransaction();
			System.out.println("Reservation Add:" + sessionObj.save(e));
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

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Update(java.lang.Object)
	 */
	@Override
	public void Update(Reservation e) {
		SessionFactory sessionFactoryObj = new Configuration().configure().buildSessionFactory();
		Session sessionObj = sessionFactoryObj.openSession();
		try {
			sessionObj.beginTransaction();
			sessionObj.update(e);
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

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.IRepository#Delete(int)
	 */
	@Override
	public void Delete(int id) {
		SessionFactory sessionFactoryObj = new Configuration().configure().buildSessionFactory();
		Session sessionObj = sessionFactoryObj.openSession();
		try {
			sessionObj.beginTransaction();
			System.out.println("Reservation Delete:" + sessionObj.createQuery("delete Reservation where ID=:idparam")
					.setParameter("idparam", id).executeUpdate());
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

	}

}
