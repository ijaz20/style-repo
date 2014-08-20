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
import com.style.model.ProductPrice;
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
	public Product saveProduct(Product product, File file, String filePath)
			throws AppException {
		if (null != file) {
			try {
				FileUtil.saveFile(file, filePath, product.getProductName()
						.replaceAll(" ", "_") + Constants.IMAGE_FORMAT);
			} catch (IOException e) {
				log.error(e.getMessage(), e);
				throw new AppException(
						"Problem in saving product, Please check the product image file");
			}
			product.setImagePath(Constants.PRODUCT_IMAGE_PATH
					+ product.getProductName().replaceAll(" ", "_")
					+ Constants.IMAGE_FORMAT);
		}
		return productDao.saveProduct(product);
	}

	/**
	 * {@inheritDoc}
	 */
	public Product getProduct(String id) throws AppException {
		return productDao.getProduct(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<ProductPrice> getPrices(List<String> priceIds) throws AppException{
		return productDao.getPrices(priceIds);
	}
}
