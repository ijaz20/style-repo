package com.style.webapp.action;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Preparable;
import com.style.Constants;
import com.style.branch.service.BranchManager;
import com.style.exception.AppException;
import com.style.meta.service.MetaDataManager;
import com.style.model.Area;
import com.style.model.Branch;
import com.style.model.City;
import com.style.util.StringUtil;

/**
 * Action for facilitating branch management feature.
 * 
 * @auther mathi
 */
public class BranchAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 6776558938712115191L;
	private BranchManager branchManager;
	private MetaDataManager metaDataManager;
	private List<Branch> branches;
	private Branch branch;
	private String id;
	private String brand;

	@Autowired
	public void setBranchManager(BranchManager branchManager) {
		this.branchManager = branchManager;
	}

	@Autowired
	public void setMetaDataManager(MetaDataManager metaDataManager) {
		this.metaDataManager = metaDataManager;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * show branch for for create/update
	 * 
	 * @return
	 */
	public String showBranch() {
		log.info("show branch");
		if (!StringUtil.isEmptyString(getId())) {
			branch = branchManager.getBranch(getId());
		} else {
			branch = new Branch();
			Area area = new Area();
			City city = (City) getRequest().getSession().getServletContext()
					.getAttribute(Constants.CURRENT_CITY);
			area.setCity(city);
			branch.setArea(area);
		}
		return "success";
	}

	/**
	 * show all branches
	 * 
	 * @return
	 */
	public String showBranches() {
		branches = branchManager.getBranches();
		return "success";
	}

	public String getPartnerBranches(){
		System.out.println("brand==========="+getRequest().getParameter("brand"));
		branches = branchManager.getPartnerBranches(getRequest().getParameter("brand"));
		return Constants.SUCCESS;
	}
	
	/**
	 * save branch
	 * 
	 * @return
	 */
	public String saveBranch() {
		log.info("branch saving");
		try {
			Calendar currentTime = new GregorianCalendar();
			getBranch().setModifiedTime(currentTime);
			getBranch().setModifiedBy(getRequest().getRemoteUser());
			if (StringUtil.isEmptyString(getBranch().getId())) {
				getBranch().setCreatedTime(currentTime);
				getBranch().setCreatedBy(getRequest().getRemoteUser());
			}
			branch = branchManager.saveBranch(getBranch());
			saveMessage("branch saved successfully");
			getRequest()
					.getSession()
					.getServletContext()
					.setAttribute(Constants.AVAILABLE_BRANCHES,
							branchManager.getAllBranches());
			getRequest()
					.getSession()
					.getServletContext()
					.setAttribute(Constants.BRANCHES,
							branchManager.getBranches());
			return showBranches();
		} catch (AppException e) {
			saveMessage(e.getMessage());
			log.error(e.getMessage(), e);
			return "error";
		}
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
