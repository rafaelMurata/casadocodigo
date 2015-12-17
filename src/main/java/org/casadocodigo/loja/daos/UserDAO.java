package org.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.casadocodigo.loja.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String jpql = "select u from User u where u.login = :login";
		List<User> user = em.createQuery(jpql,User.class).setParameter("login", username).getResultList();
		if(user.isEmpty()){
			throw new UsernameNotFoundException("o usuario" + username + " nao existe");
		}
		return user.get(0);
	}

}
