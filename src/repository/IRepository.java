package repository;

import java.util.ArrayList;

/**
 * @author User
 *
 * @param <E>
 * @param <ID>
 */
public interface IRepository<E, ID> {
	/**
	 * @return
	 */
	public ArrayList<E> GetAll();

	/**
	 * @param id
	 * @return
	 */
	public E Get(ID id);

	/**
	 * @param e
	 */
	public void Add(E e);

	/**
	 * @param e
	 */
	public void Update(E e);

	/**
	 * @param id
	 */
	public void Delete(int id);
}
