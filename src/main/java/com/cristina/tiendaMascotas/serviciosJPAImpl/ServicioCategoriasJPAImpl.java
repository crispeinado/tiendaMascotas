package com.cristina.tiendaMascotas.serviciosJPAImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cristina.tiendaMascotas.model.Categoria;
import com.cristina.tiendaMascotas.servicios.ServicioCategorias;

@Service
@Transactional
public class ServicioCategoriasJPAImpl implements ServicioCategorias{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Categoria> obtenerCategoria() {

		return entityManager.createQuery("select c from Categoria c").getResultList();
	}

}
