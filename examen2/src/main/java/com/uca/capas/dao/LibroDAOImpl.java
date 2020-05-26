package com.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uca.capas.domain.Libro;


@Repository
public class LibroDAOImpl implements LibroDAO{
	@PersistenceContext(unitName = "capas")
	EntityManager entityManager;

	@Override
	public List<Libro> findAll() throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from cat_libro");
		Query query = entityManager.createNativeQuery(sb.toString(), Libro.class);
		List<Libro> res = query.getResultList();
		return res;
	}

	@Override
	public Libro findOne(Integer codigo) throws DataAccessException {
		Libro c = entityManager.find(Libro.class, codigo);
		return c;
	}
	//Guardar contribuyente
	@Override
	@Transactional
	public void save(Libro c) throws DataAccessException {
		
		if(c.getLibro_id() == null) { 
			entityManager.persist(c); 
		}
		else { 
			entityManager.merge(c); 
		}
		
	}
	
	//Eliminar contribuyente
	@Override
	@Transactional
	public int delete(Libro c) throws DataAccessException {
		try {
			entityManager.remove(entityManager.contains(c) ? c : entityManager.merge(c));
			entityManager.flush();	
			return 1;
		}catch(Throwable ex) {
			ex.printStackTrace();
			return 1;
		}
	}
	

}
