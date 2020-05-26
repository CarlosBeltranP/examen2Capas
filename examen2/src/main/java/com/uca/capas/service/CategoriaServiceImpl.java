package com.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.uca.capas.dao.CategoriaDAO;
import com.uca.capas.domain.Categoria;


@Service
public class CategoriaServiceImpl implements CategoriaService {
	@Autowired
	CategoriaDAO categoriaDAO;

	@Override
	public List<Categoria> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return categoriaDAO.findAll();
	}

	@Override
	public Categoria findOne(Integer code) throws DataAccessException {
		// TODO Auto-generated method stub
		return categoriaDAO.findOne(code);
	}

	@Override
	public void save(Categoria c) throws DataAccessException {
		categoriaDAO.save(c);
		
	}
	

}
