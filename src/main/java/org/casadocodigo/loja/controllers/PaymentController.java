package org.casadocodigo.loja.controllers;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.casadocodigo.loja.model.PaymentData;
import org.casadocodigo.loja.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private ShoppingCart shoppingCart;
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public Callable<String> checkout(){
		return () -> {
			BigDecimal total = shoppingCart.getTotal();
			
			String uriToPay =
					"http://book-payment.herokuapp.com/payment";
			try{
				final HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<PaymentData> request = new HttpEntity<>(new PaymentData(total), headers);
				
				String response = restTemplate.postForObject(uriToPay,request , String.class);
				System.out.println(response);
				return "redirect:/produtos";				
			} catch (HttpServerErrorException exception){
				System.out.println("Ocorreu um erro ao criar o Pagamento: " + exception.getMessage());
				exception.printStackTrace();
				return "redirect:/shopping";
			}
		};
	}
}
