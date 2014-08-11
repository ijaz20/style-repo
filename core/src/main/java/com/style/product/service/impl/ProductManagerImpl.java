package com.style.product.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.style.Constants;
import com.style.exception.AppException;
import com.style.model.Branch;
import com.style.model.LabelValue;
import com.style.model.Partner;
import com.style.model.Product;
import com.style.model.ProductCategory;
import com.style.product.dao.ProductDao;
import com.style.product.service.ProductManager;
import com.style.service.impl.GenericManagerImpl;
import com.style.util.FileUtil;

/**
 * Implementation of ProductManager interface.
 * 
 * @author mathi
 */
@Service("productManager")
public class ProductManagerImpl extends GenericManagerImpl<Product, String>
		implements ProductManager {

	private ProductDao productDao;

	@Autowired
	public ProductManagerImpl(ProductDao productDao) {
		super(productDao);
		this.productDao = productDao;
	}

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.dao = productDao;
		this.productDao = productDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Product> getAllProducts(int start, String[] brands,
			String[] categories) throws AppException {
		return productDao.getAllProducts(start, brands, categories);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ProductCategory> getAllProductCategoriess() throws AppException {
		return productDao.getAllProductCategoriess();
	}

	/**
	 * {@inheritDoc}
	 */
	public Partner savePartner(Partner partner) throws AppException {
		return productDao.savePartner(partner);
	}

	/**
	 * {@inheritDoc}
	 */
	public Branch saveBranch(Branch branch) throws AppException {
		return productDao.saveBranch(branch);
	}

	/**
	 * {@inheritDoc}
	 */
	public ProductCategory saveCategory(ProductCategory category)
			throws AppException {
		return productDao.saveCategory(category);
	}

	/**
	 * {@inheritDoc}
	 */
	public Product saveProduct(Product product, File file, String filePath) throws AppException {
		if(null != file){
			try {
				FileUtil.saveFile(file, filePath, product.getProductName().replaceAll(" ","_")+Constants.IMAGE_FORMAT);
			} catch (IOException e) {
				log.error(e.getMessage(), e);
				throw new AppException("Problem in saving product, Please check the product image file");
			}
			product.setImagePath(Constants.PRODUCT_IMAGE_PATH+product.getProductName().replaceAll(" ","_")+Constants.IMAGE_FORMAT);
		}
		return productDao.saveProduct(product);
	}

	public List<Branch> getBranches(String[] branchIds) throws AppException {
		return productDao.getBranches(branchIds);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getAllProductCategories() throws AppException {
		List<ProductCategory> categories = productDao.getProductCategories();
		List<LabelValue> list = new ArrayList<LabelValue>();

		for (ProductCategory category : categories) {
			list.add(new LabelValue(category.getCategoryName(), category
					.getId()));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getAllPartners() throws AppException {
		List<Partner> partners = productDao.getPartners();
		List<LabelValue> list = new ArrayList<LabelValue>();

		for (Partner partner : partners) {
			list.add(new LabelValue(partner.getPartnerName(), partner.getId()));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getAllBranches() throws AppException {
		List<Branch> branches = productDao.getBranches();
		List<LabelValue> list = new ArrayList<LabelValue>();

		for (Branch branch : branches) {
			list.add(new LabelValue(branch.getBranchName(), branch.getId()));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ProductCategory> getProductCategories() throws AppException {
		return productDao.getProductCategories();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Partner> getPartners() throws AppException {
		return productDao.getPartners();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Branch> getBranches() throws AppException {
		return productDao.getBranches();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Partner getPartner(String id) throws AppException{
		return productDao.getPartner(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public Branch getBranch(String id) throws AppException{
		return productDao.getBranch(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public ProductCategory getProductCategory(String id) throws AppException{
		return productDao.getProductCategory(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public Product getProduct(String id) throws AppException{
		return productDao.getProduct(id);
	}
}
