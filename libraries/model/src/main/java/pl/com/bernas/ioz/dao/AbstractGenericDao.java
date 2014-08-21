package pl.com.bernas.ioz.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;

import pl.com.bernas.ioz.model.IozEntity;

@SuppressWarnings("unchecked")
public abstract class AbstractGenericDao<T extends IozEntity, PK extends Serializable> implements GenericDao<T, Long> {

	// protected static Log log = LogFactory.getLog(AbstractGenericDao.class);
	private Class<T> clazz;
	
	protected EntityManager primaryEm;
	
	public AbstractGenericDao(Class<T> clazz) {
		this.clazz = clazz;
	} 

	public T findById(Long pk) {
		return primaryEm.find(this.clazz, pk);
	}

	public List<T> getAll() {
		// log.debug("get all records for " + clazz.getSimpleName());
		// return (List<T>)
		// getHibernateTemplate().find("from "+clazz.getSimpleName());

		return null;
	}

	public List<T> getByCriteria(CriteriaQuery<?>... criterions) {
		// if(criterions==null || criterions.length==0){
		// return getAll();
		// }
		// Criteria criteria = getSession().createCriteria(clazz);
		// for(Criterion c:criterions){
		// criteria.add(c);
		// }
		// log.debug("get all records by criteria for " +
		// clazz.getSimpleName());
		// return (List<T>) criteria.list();

		return null;
	}

	public List<T> getByCriteria(CriteriaQuery<?>[] criterions, Order... orders) {
		// if(orders==null || orders.length==0){
		// return getByCriteria(criterions);
		// }
		// Criteria criteria = getSession().createCriteria(clazz);
		// for(Criterion c:criterions){
		// criteria.add(c);
		// }
		// for(Order order:orders){
		// criteria.addOrder(order);
		// }
		// log.debug("get all records by criteria and order for " +
		// clazz.getSimpleName());
		// return (List<T>) criteria.list();

		return null;
	}

	public Long insert(T t) {
		primaryEm.persist(t);

		return t.getId();
	}

	public void update(T t) {
		// log.debug("update object " + t.toString());
		// getHibernateTemplate().update(t);
	}

	public void delete(T t) {
		// log.debug("delete object " + t.toString());
		// getHibernateTemplate().delete(t);
	}

	protected T executeQueryWithUniqueResult(String query, QueryParam... params) {
		return (T) new QueryExecutor(primaryEm, query, params, true).execute();
	}

	protected List<T> executeQueryWithMultipleResult(String query, QueryParam... params) {
		// return (List<T>) getHibernateTemplate().execute(new
		// QueryExecutor(query, params, false));

		return null;
	}

	private class QueryExecutor {

		private EntityManager em;
		private String queryString;
		private QueryParam[] params;
		private boolean uniqueResult;

		QueryExecutor(EntityManager em, String queryString, QueryParam[] params, boolean uniqueResult) {
			this.em = em;
			this.queryString = queryString;
			this.params = params;
			this.uniqueResult = uniqueResult;
		}

		@SuppressWarnings("rawtypes")
		public Object execute() {
			Query query = em.createQuery(queryString);
			
			if (params != null && params.length > 0) {
				for (QueryParam param : params) {
					if (param.value instanceof Collection) {
						query.setParameter(param.name, (Collection) param.value);
					} else {
						query.setParameter(param.name, param.value);
					}
				}
			}
			
			if (uniqueResult) {
				List results = query.getResultList();
				if (results.isEmpty())
					return null;
				else if (results.size() == 1)
					return results.get(0);
				throw new NonUniqueResultException();
			} else {
				return query.getResultList();
			}
		}
	}
}
