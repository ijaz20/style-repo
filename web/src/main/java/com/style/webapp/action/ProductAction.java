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
    private String productId;
    private Product product;
    private String productName;
    private String branchName;
    private String categoryName;
    private String area;
    private String priceRange;
    private String minimumPrice;
    private String maximumPrice;
    private List<String> categories;
    private List<String> brands;

    @Autowired
    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public void prepare() throws Exception {
        // TODO Auto-generated method stub
    }

    public String getAllProducts() {
        System.out.println("-----all products");
        products = productManager.getAll();
        return "success";
    }

    public String getFilteredProducts() {
        System.out.println("------"+getRequest().getParameterValues("brands[]"));
        System.out.println("------"+getRequest().getParameterValues("categories[]"));
        System.out.println("----filter products"+getBrands()+"------"+getCategories());
        products = productManager.getAllProducts(0, 11, getRequest().getParameterValues("brands[]"), getRequest().getParameterValues("categories[]"));
        
        return "success";
    }

    public String getProductDetails() {
        System.out.println("--- product");
        if (null != getProductId()) {
            product = productManager.get(getProductId());
        }
        return "success";
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

}
