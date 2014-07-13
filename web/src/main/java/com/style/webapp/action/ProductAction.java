package com.style.webapp.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Preparable;
import com.style.model.Product;
import com.style.product.service.ProductManager;

public class ProductAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 6776558938712115191L;
    private List<Product> products;
    private ProductManager productManager;
    
    @Autowired
    public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
    
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}

	public String getAllProducts(){
		products = productManager.getAll();
		return "success";
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
