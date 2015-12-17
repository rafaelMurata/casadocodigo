package org.casadocodigo.loja.controllers;

import org.casadocodigo.loja.daos.ProductDAO;
import org.casadocodigo.loja.model.BookType;
import org.casadocodigo.loja.model.Product;
import org.casadocodigo.loja.model.ShoppingCart;
import org.casadocodigo.loja.model.ShoppingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ShoppingCart shoppingCart;
	

	@RequestMapping(method=RequestMethod.POST)	
	public ModelAndView add(Integer productId,@RequestParam BookType bookType){
		ShoppingItem item = createItem(productId, bookType);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/shopping");
	}

	private ShoppingItem createItem(Integer productId, BookType bookType) {
		Product product = productDAO.find(productId);
		ShoppingItem item = new ShoppingItem(product,bookType);
		return item;
	}
	@RequestMapping(method=RequestMethod.GET)
	public String items(){
		return "shoppingCart/items";
	}
	
}
