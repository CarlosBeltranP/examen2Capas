package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Libro;

public interface LibroService {
	public List<Libro> findAll() throws DataAccessException;

	public Libro findOne(Integer codigo) throws DataAccessException;

	public void save(Libro c) throws DataAccessException;

	int delete(Libro c) throws DataAccessException;

}
