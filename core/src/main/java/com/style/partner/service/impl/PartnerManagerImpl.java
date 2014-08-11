package com.style.partner.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.style.Constants;
import com.style.exception.AppException;
import com.style.model.LabelValue;
import com.style.model.Partner;
import com.style.partner.dao.PartnerDao;
import com.style.partner.service.PartnerManager;
import com.style.service.impl.GenericManagerImpl;
import com.style.util.FileUtil;

/**
 * Implementation of PartnerManager interface.
 * 
 * @author mathi
 */
@Service("partnerManager")
public class PartnerManagerImpl extends GenericManagerImpl<Partner, String>
		implements PartnerManager {

	private PartnerDao partnerDao;

	@Autowired
	public PartnerManagerImpl(PartnerDao partnerDao) {
		super(partnerDao);
		this.partnerDao = partnerDao;
	}

	@Autowired
	public void setPartnerDao(PartnerDao partnerDao) {
		this.dao = partnerDao;
		this.partnerDao = partnerDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public Partner savePartner(Partner partner, File file, String filePath)
			throws AppException {
		if(null != file){
			try {
				FileUtil.saveFile(file, filePath, partner.getPartnerName());
			} catch (IOException e) {
				log.error(e.getMessage(), e);
				throw new AppException("Problem in saving partner, Please check the logo file");
			}
			partner.setImagePath(Constants.PARTNER_IMAGE_PATH+partner.getPartnerName().replaceAll(" ","_")+Constants.IMAGE_FORMAT);
		}
		return partnerDao.savePartner(partner);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getAllPartners() throws AppException {
		List<Partner> partners = partnerDao.getPartners();
		List<LabelValue> list = new ArrayList<LabelValue>();

		for (Partner partner : partners) {
			list.add(new LabelValue(partner.getPartnerName(), partner.getId()));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Partner> getPartners() throws AppException {
		return partnerDao.getPartners();
	}

	/**
	 * {@inheritDoc}
	 */
	public Partner getPartner(String id) throws AppException {
		return partnerDao.getPartner(id);
	}

}
