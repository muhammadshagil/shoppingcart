package com.store.shoppingcart;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.model.Product;
import com.store.services.ProductService;

@RestController
public class AppController {
	@Autowired
	private ProductService productService;

//	@Autowired
//	public void setProductService(ProductService productService) {
//		this.productService = productService;
//	}
	
	@RequestMapping(value="/api")
	public List<String> list(){
		List<String> newList = new ArrayList<String>();
		newList.add("test");
		return newList;
	}
	
	@RequestMapping(value = "/products/list", produces= MediaType.APPLICATION_JSON)
    public List<Product> listProducts(){
        return productService.listAll();
    }
	
	@RequestMapping(value="/products/import", method=RequestMethod.GET)
	public String saveProductList() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Product >> mapType = new TypeReference<List<Product >>() {};
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("products.json");
//		InputStream is = TypeReference.class.getResourceAsStream("products.json");
			List<Product> productList = mapper.readValue(is, mapType);
			if(null != productList){
				productList.stream().forEach(productService::saveOrUpdate);
			}
			return "Products are imported";
	}
	
	@RequestMapping(value = "/products/deleteAll", produces= MediaType.APPLICATION_JSON)
    public String deleteAllProducts(){
        productService.deleteAll();
        return "All products are deleted";
    }
}
