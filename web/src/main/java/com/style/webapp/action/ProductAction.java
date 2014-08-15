package com.style.webapp.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Preparable;
import com.style.Constants;
import com.style.exception.AppException;
import com.style.model.Product;
import com.style.model.ProductPrice;
import com.style.product.service.ProductManager;
import com.style.util.StringUtil;

/**
 * Action for facilitating product Management feature.
 * 
 * @auther mathi
 */
public class ProductAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 6776558938712115191L;
	private ProductManager productManager;
	private List<Product> products;
	private Product product;
	private String id;
	private String productCount;
	private List<ProductPrice> prices;
	private File file;
	private String filePath;


	@Autowired
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * get all products
	 * 
	 * @return
	 */
	public String getAllProducts() {
		log.info("getting all products");
		try {
			products = productManager.getAllProducts(0, null, null);
			return SUCCESS;
		} catch (AppException e) {
			saveMessage(e.getMessage());
			log.error(e.getMessage(), e);
			return ERROR;
		}
	}

	/**
	 * get filtered product with limit
	 * 
	 * @return
	 */
	public String getFilteredProducts() {
		log.info("getting filtered products");
		try {
			products = productManager.getAllProducts(Integer
					.parseInt(getProductCount()), getRequest()
					.getParameterValues("brands[]"), getRequest()
					.getParameterValues("categories[]"));
			removeDuplicates();
			return SUCCESS;
		} catch (AppException e) {
			saveMessage(e.getMessage());
			log.error(e.getMessage(), e);
			return ERROR;
		}
	}

	/**
	 * remove duplicate products
	 */
	private void removeDuplicates() {
		if (getProducts() != null && !getProducts().isEmpty()) {
			products = new ArrayList<Product>(new HashSet<Product>(
					getProducts()));
		}
	}

	/**
	 * get product details by id
	 * 
	 * @return
	 */
	public String getProductDetails() {
		log.info("getting product details");
		try {
			if (null != getId()) {
				product = productManager.get(getId());
			}
			return SUCCESS;
		} catch (AppException e) {
			saveMessage(e.getMessage());
			log.error(e.getMessage(), e);
			return ERROR;
		}
	}

	/**
	 * show product for for create/update
	 * 
	 * @return
	 */
	public String showProduct() {
		log.info("show product");
		if (!StringUtil.isEmptyString(getId())) {
			product = productManager.getProduct(getId());
		}
		return SUCCESS;
	}

	/**
	 * show all products
	 * 
	 * @return
	 */
	public String showProducts() {
		products = productManager.getAll();
		return SUCCESS;
	}

	/**
	 * save product
	 * 
	 * @return
	 */
	public String saveProduct() {
		log.info("product saving");
		try {
			filePath = ServletActionContext.getServletContext().getRealPath(Constants.PRODUCT_IMAGE_PATH);
			if (null != getPrices() && !getPrices().isEmpty()) {
				for (ProductPrice productPrice : getPrices()) {
					if(null != productPrice){
						log.info("product price branch id is "
								+ productPrice.getBranch().getId() + " & price is "
								+ productPrice.getPrice());
						productPrice.setProduct(product);
						productPrice
								.setCurrencyCode(Constants.INDIAN_CURRENCY_CODE);
						getProduct().getProductPrices().add(productPrice);
					}
				}
			}
			Calendar currentTime = new GregorianCalendar();
			getProduct().setModifiedTime(currentTime);
			getProduct().setModifiedBy(getRequest().getRemoteUser());
			if(StringUtil.isEmptyString(getProduct().getId())){
				getProduct().setCreatedTime(currentTime);
				getProduct().setCreatedBy(getRequest().getRemoteUser());
			}
			product = productManager.saveProduct(getProduct(), file, filePath+"/");
			saveMessage("product saved successfully");
			return showProducts();
		} catch (AppException e) {
			saveMessage(e.getMessage());
			log.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String getProductById(){
		log.info("getting product details");
		if(!StringUtil.isEmptyString(getId())){
			try {
				product = productManager.get(getId());
			} catch (AppException e){
				log.error(e.getMessage(), e);
				saveMessage("Problem in getting product");
			}
		} else {
			saveMessage("product id is empty");
			return "error";
		}
		return SUCCESS;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductCount() {
		return productCount;
	}

	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}

	public List<ProductPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<ProductPrice> prices) {
		this.prices = prices;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
