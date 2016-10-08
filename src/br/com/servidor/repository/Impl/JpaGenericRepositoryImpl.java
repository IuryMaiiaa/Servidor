package br.com.servidor.repository.Impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.servidor.repository.GenericRepository;
import enumeration.QueryType;


public class JpaGenericRepositoryImpl<T> implements GenericRepository<T> {
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Servidor");

	private static Logger log = LoggerFactory.getLogger(JpaGenericRepositoryImpl.class);

	protected EntityManager emx = entityManagerFactory.createEntityManager();

	@Override
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		
		log.debug("Setting EntityManager: {} {} ", this.getClass(), em);
		emx = em;
	}

	@Override
	public void save(T entity) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
	}

	@Override
	public void update(T entity) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public void delete(T entity) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(entity));
		em.getTransaction().commit();
	}

	@Override
	public T find(Class<T> entityClass, Object id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		T result = null;
		result = em.find(entityClass, id);
		return result;
	}

	@Override
	public List<T> find(Class<T> entityClass) {
		return find(entityClass, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(Class<T> entityClass, int firstResult, int maxResults) {
		EntityManager em = entityManagerFactory.createEntityManager();
		List<T> result = null;
		Query q = em.createQuery("select obj from "
				+ entityClass.getSimpleName() + " obj");
		if (firstResult >= 0 && maxResults >= 0) {
			q = q.setFirstResult(firstResult).setMaxResults(maxResults);
		}
		result = q.getResultList();
		return result;
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public List find(String queryName, Map<String, Object> namedParams) {
		return find(QueryType.NAMED, queryName, namedParams);
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public List find(QueryType type, String query,
			Map<String, Object> namedParams) {
		return find(type, query, namedParams, -1, -1);
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public List find(String queryName, Map<String, Object> namedParams,
			int firstResult, int maxResults) {
		return find(QueryType.NAMED, queryName, namedParams, firstResult,
				maxResults);
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public List find(QueryType type, String query,
			Map<String, Object> namedParams, int firstResult, int maxResults) {
		List result = null;
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q;
		if (type == QueryType.JPQL) {
			q = em.createQuery(query);
		} else if (type == QueryType.NATIVE) {
			q = em.createNativeQuery(query);
		} else if (type == QueryType.NAMED) {
			q = em.createNamedQuery(query);
		} else {
			throw new IllegalArgumentException("Invalid query type: " + type);
		}

		setNamedParameters(q, namedParams);

		if (firstResult >= 0 && maxResults >= 0) {
			q = q.setFirstResult(firstResult).setMaxResults(maxResults);
		}

		result = q.getResultList();

		return result;
	}

	private void setNamedParameters(Query q, Map<String, Object> namedParams) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		if (namedParams != null) {
			log.debug("Named parameters: {}", namedParams);
			Set<String> keys = namedParams.keySet();
			for (String key : keys) {
				q.setParameter(key, namedParams.get(key));
			}
		}
		em.getTransaction().commit();
	}
	
	@Override
	public Object findFirst(String queryName, Map<String, Object> namedParams) {
		return findFirst(QueryType.NAMED, queryName, namedParams);
	}

	@Override
	public Object findFirst(QueryType type, String query, Map<String, Object> namedParams) {

		@SuppressWarnings("rawtypes")
		List result = find(type, query, namedParams, 0, 1);
		return result == null || result.size() == 0 ? null : result.get(0);
	}

	public int executeUpdate(String sql, Map<String, Object> namedParams) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNativeQuery(sql);
		setNamedParameters(q, namedParams);
		return q.executeUpdate();
	}
	
}
