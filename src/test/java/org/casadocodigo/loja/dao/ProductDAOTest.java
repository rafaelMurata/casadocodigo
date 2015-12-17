package org.casadocodigo.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.casadocodigo.loja.builder.ProductBuilder;
import org.casadocodigo.loja.conf.DataSourceConfigurationTest;
import org.casadocodigo.loja.conf.JPAConfiguration;
import org.casadocodigo.loja.daos.ProductDAO;
import org.casadocodigo.loja.model.BookType;
import org.casadocodigo.loja.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProductDAO.class,JPAConfiguration.class,DataSourceConfigurationTest.class })
@ActiveProfiles("test")
public class ProductDAOTest {

	@Autowired
	private ProductDAO productDAO;

	@Transactional
	@Test
	public void shouldSumAllPricesOfEachBookPerType(){
		List<Product> printedBooks = ProductBuilder.newProduct(BookType.PRINTED, BigDecimal.TEN).more(4).buidAll();
		printedBooks.stream().forEach(productDAO::save);
		
		List<Product> ebook = ProductBuilder.newProduct(BookType.EBOOK, BigDecimal.TEN).more(4).buidAll();
		printedBooks.stream().forEach(productDAO::save);
		
		BigDecimal value = productDAO.sumPricesPerType(BookType.PRINTED);
		
		Assert.assertEquals(new BigDecimal(50).setScale(2), value);
	}
}
