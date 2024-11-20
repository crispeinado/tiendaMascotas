package com.cristina.tiendaMascotas.serviciosJPAImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//import org.hibernate.Criteria;
//import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cristina.tiendaMascotas.constantesSQL.ConstantesSQL;
import com.cristina.tiendaMascotas.model.Usuario;
import com.cristina.tiendaMascotas.servicios.ServicioUsuarios;
import com.cristina.tiendaMascotas.utilidades.Utilidades;

@Service
@Transactional
public class ServicioUsuariosJPAimpl implements ServicioUsuarios{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void registrarUsuario(Usuario u) {
		entityManager.persist(u);
	}

	@Override
	public Usuario obtenerUsuarioPorEmailyPass(String email, String pass) {
		
		//las consultas con query son con jpql
		Query query = entityManager.createQuery("select u from Usuario u where u.email = :email and u.pass = :pass");
		query.setParameter("email",email);
		query.setParameter("pass", pass);
		
		List<Usuario> resultado = query.getResultList();
		
		if (resultado.size() == 0){
			return null;
		}else {
			return resultado.get(0);
		}
	}

	@Override
	public boolean comprobarEmailExiste(String email) {

		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_ID_USUARIO_POR_EMAIL);
		query.setParameter("email", email);
		List<Map<String, Object>> res = Utilidades.procesaNativeQuery(query);
		
		if(res.size()>0) {
			return true;
		} else {
			return false;
		}
	}
	
}
