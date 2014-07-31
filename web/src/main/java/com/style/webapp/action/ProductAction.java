package com.style.webapp.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Preparable;
import com.style.Constants;
import com.style.exception.AppException;
import com.style.model.Branch;
import com.style.model.Partner;
import com.style.model.Product;
import com.style.model.ProductCategory;
import com.style.model.ProductPrice;
import com.style.product.service.ProductManager;

/**
 * Action for facilitating User Management feature.
 * 
 * @auther mathi
 */
public class ProductAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 6776558938712115191L;
	private ProductManager productManager;
	private List<Product> products;
	private List<ProductPrice> prices;
	private List<String> brandsIds;
	private List<String> categoryIds;
	private String productId;
	private Product product;
	private String productName;
	private String branchName;
	private String categoryName;
	private String priceRange;
	private String minimumPrice;
	private String maximumPrice;
	private String productCount;
	private Partner partner;
	private Branch branch;
	private ProductCategory category;

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
			return "success";
		} catch (AppException e) {
			saveMessage(e.getMessage());
			log.error(e.getMessage(), e);
			return "error";
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
			return "success";
		} catch (AppException e) {
			saveMessage(e.getMessage());
			log.error(e.getMessage(), e);
			return "error";
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
			if (null != getProductId()) {
				product = productManager.get(getProductId());
			}
			return "success";
		} catch (AppException e) {
			saveMessage(e.getMessage());
			log.error(e.getMessage(), e);
			return "error";
		}
	}

	/**
	 * show partner for for create/update
	 * 
	 * @return
	 */
	public String showPartner() {
		log.info("show partner");
		return "success";
	}

	/**
	 * show branch for for create/update
	 * 
	 * @return
	 */
	public String showBranch() {
		log.info("show branch");
		return "success";
	}

	/**
	 * show category for for create/update
	 * 
	 * @return
	 */
	public String showCategory() {
		log.info("show category");
		return "success";
	}

	/**
	 * show product for for create/update
	 * 
	 * @return
	 */
	public String showProduct() {
		log.info("show product");
		return "success";
	}

	/**
	 * save partner
	 * 
	 * @return
	 */
	public String savePartner() {
		log.info("partner saving");
		try {
			partner = productManager.savePartner(getPartner());
			saveMessage("partner saved successfully");
			getRequest().getSession().getServletContext().setAttribute(Constants.PARTNERS, productManager.getPartners());
			getRequest().getSession().getServletContext().setAttribute(Constants.AVAILABLE_PARTNERS, productManager.getAllPartners());
			return "success";
		} catch (AppException e) {
			saveMessage(e.getMessage());
			log.error(e.getMessage(), e);
			return "error";
		}
	}

	/**
	 * save branch
	 * 
	 * @return
	 */
	public String saveBranch() {
		log.info("branch saving");
		try {
			branch = productManager.saveBranch(branch);
			saveMessage("branch saved successfully");
			getRequest().getSession().getServletContext().setAttribute(Constants.AVAILABLE_BRANCHES, productManager.getAllBranches());
			getRequest().getSession().getServletContext().setAttribute(Constants.BRANCHES, productManager.getBranches());
			return "success";
		} catch (AppException e) {
			saveMessage(e.getMessage());
			log.error(e.getMessage(), e);
			return "error";
		}
	}

	/**
	 * save category
	 * 
	 * @return
	 */
	public String saveCategory() {
		log.info("category saving");
		try {
			String[] branchIds = getRequest().getParameterValues("branches");
			if (branchIds != null && branchIds.length > 0) {
				category.setBranches(new HashSet<Branch>(productManager
						.getBranches(branchIds)));
				category = productManager.saveCategory(getCategory());
				saveMessage("category saved successfully");
				getRequest().getSession().getServletContext().setAttribute(Constants.CATEGORIES, productManager.getProductCategories());
				return "success";
			} else {
				saveMessage("select atleast one branch");
				return "error";
			}
		} catch (AppException e) {
			saveMessage(e.getMessage());
			log.error(e.getMessage(), e);
			return "error";
		}
	}

	/**
	 * save product
	 * 
	 * @return
	 */
	public String saveProduct() {
		log.info("product saving");
		Set<ProductPrice> productPrices = new HashSet<ProductPrice>();
		try {
			if (null != getPrices() && !getPrices().isEmpty()) {
				for (ProductPrice productPrice : getPrices()) {
					log.info("product price branch id is "+productPrice.getBranch().getId() +" & price is "+ productPrice.getPrice());
					productPrice.setProduct(product);
					productPrice
							.setCurrencyCode(Constants.INDIAN_CURRENCY_CODE);
					productPrices.add(productPrice);
				}
				getProduct().setProductPrices(productPrices);
			}
			product = productManager.saveProduct(getProduct());
			saveMessage("product saved successfully");
			return "success";
		} catch (AppException e) {
			saveMessage(e.getMessage());
			log.error(e.getMessage(), e);
			return "error";
		}
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public String getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(String minimumPrice) {
		this.minimumPrice = minimumPrice;
	}

	public String getMaximumPrice() {
		return maximumPrice;
	}

	public void setMaximumPrice(String maximumPrice) {
		this.maximumPrice = maximumPrice;
	}

	public String getProductCount() {
		return productCount;
	}

	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public List<ProductPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<ProductPrice> prices) {
		this.prices = prices;
	}

	public List<String> getBrandsIds() {
		return brandsIds;
	}

	public void setBrandsIds(List<String> brandsIds) {
		this.brandsIds = brandsIds;
	}

	public List<String> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<String> categoryIds) {
		this.categoryIds = categoryIds;
	}

}
