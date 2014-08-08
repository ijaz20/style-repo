package com.style.webapp.action;

import java.io.File;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Preparable;
import com.style.Constants;
import com.style.exception.AppException;
import com.style.model.Partner;
import com.style.partner.service.PartnerManager;
import com.style.util.StringUtil;

/**
 * Action for facilitating Partner Management feature.
 * 
 * @auther mathi
 */
public class PartnerAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 6776558938712115191L;
	private PartnerManager partnerManager;
	private List<Partner> partners;
	private String id;
	private Partner partner;
	private File file;
	private String filePath;

	@Autowired
	public void setPartnerManager(PartnerManager partnerManager) {
		this.partnerManager = partnerManager;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * show partner for for create/update
	 * 
	 * @return
	 */
	public String showPartner() {
		log.info("show partner");
		if (!StringUtil.isEmptyString(getId())) {
			partner = partnerManager.getPartner(getId());
		}
		return "success";
	}

	/**
	 * show all partners
	 * 
	 * @return
	 */
	public String showPartners() {
		partners = partnerManager.getPartners();
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
			filePath = ServletActionContext.getServletContext().getRealPath("images");
			partner = partnerManager.savePartner(getPartner(), file, filePath+"/partner/");
			saveMessage("partner saved successfully");
			getRequest()
					.getSession()
					.getServletContext()
					.setAttribute(Constants.PARTNERS,
							partnerManager.getPartners());
			getRequest()
					.getSession()
					.getServletContext()
					.setAttribute(Constants.AVAILABLE_PARTNERS,
							partnerManager.getAllPartners());
			return showPartners();
		} catch (AppException e) {
			saveMessage(e.getMessage());
			log.error(e.getMessage(), e);
			return "error";
		}
	}

	public List<Partner> getPartners() {
		return partners;
	}

	public void setPartners(List<Partner> partners) {
		this.partners = partners;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
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
