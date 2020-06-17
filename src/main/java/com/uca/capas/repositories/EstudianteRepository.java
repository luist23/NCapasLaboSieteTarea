package com.uca.capas.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uca.capas.domain.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
	
	public List<Estudiante> findByNombre(String cadena) throws DataAccessException;
	
	public List<Estudiante> findByApellidoStartingWith(String cadena) throws DataAccessException;
	
	@Query(nativeQuery=true, value="SELECT * FROM public.estudiante")
	public List<Estudiante> finAll() throws DataAccessException;
	
	
	@Query(nativeQuery=true, value="SELECT * FROM public.estudiante WHERE nombre = ?1")
	public List<Estudiante> mostrarPorNombre(String cadena) throws DataAccessException;
	
	@Query(nativeQuery=true, value="SELECT nombre, apellido FROM public.estudiante")
	public List<Object[]> pruebaDTO() throws DataAccessException;
}
