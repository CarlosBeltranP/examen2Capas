package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Categoria;

public interface CategoriaService {
	public List<Categoria> findAll() throws DataAccessException;

	public Categoria findOne(Integer code) throws DataAccessException;
	
	public void save(Categoria c) throws DataAccessException;

}
