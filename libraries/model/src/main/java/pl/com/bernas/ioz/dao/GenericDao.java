package pl.com.bernas.ioz.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;

import pl.com.bernas.ioz.model.IozEntity;

public interface GenericDao<T extends IozEntity, PK extends Serializable> {

	PK insert(T t);

	void update(T t);

	void delete(T t);

	T findById(PK pk);

	List<T> getAll();

	List<T> getByCriteria(CriteriaQuery<?>... criteria);

	List<T> getByCriteria(CriteriaQuery<?>[] criteria, Order... order);

}
