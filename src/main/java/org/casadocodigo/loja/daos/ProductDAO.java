package org.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.casadocodigo.loja.model.BookType;
import org.casadocodigo.loja.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager manager;

	public void save(Product produto) {
		manager.persist(produto);
	}

	public List<Product> list() {
		return manager.createQuery(
				"select p from Product p",
				Product.class).getResultList();
	}

	public Product find(Integer id) {
		TypedQuery<Product> query = manager
				.createQuery(
						"select distinct(p) from Product p join fetch p.prices where p.id=:id",
						Product.class).setParameter("id", id);
		return query.getSingleResult();
	}

	public Product findBy(Integer id, BookType bookType) {
		TypedQuery<Product> query = manager
				.createQuery(
						"select p from Product p join fetch p.prices price where p.id = :id and price.bookType = :bookType",
						Product.class);
		query.setParameter("id", id);
		query.setParameter("bookType", bookType);
		return query.getSingleResult();
	}

	public BigDecimal sumPricesPerType(BookType bookType) {
		TypedQuery<BigDecimal> query = manager.createQuery(
				"select sum(price.value) from Product p join p.prices price where price.bookType =:bookType",
				BigDecimal.class);
		query.setParameter("bookType", bookType);
		return query.getSingleResult();
	}

}
