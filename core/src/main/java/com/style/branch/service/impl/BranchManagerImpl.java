package com.style.branch.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.style.Constants;
import com.style.branch.dao.BranchDao;
import com.style.branch.service.BranchManager;
import com.style.exception.AppException;
import com.style.model.Branch;
import com.style.model.LabelValue;
import com.style.model.User;
import com.style.service.UserExistsException;
import com.style.service.UserManager;
import com.style.service.impl.GenericManagerImpl;
import com.style.util.StringUtil;

/**
 * Implementation of BranchManager interface.
 * 
 * @author mathi
 */
@Service("branchManager")
public class BranchManagerImpl extends GenericManagerImpl<Branch, String>
		implements BranchManager {

	private BranchDao branchDao;
	private UserManager userManager;
	
	@Autowired
	public BranchManagerImpl(BranchDao branchDao) {
		super(branchDao);
		this.branchDao = branchDao;
	}

	@Autowired
	public void setBranchDao(BranchDao branchDao) {
		this.dao = branchDao;
		this.branchDao = branchDao;
	}

	@Autowired
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * {@inheritDoc}
	 */
	public Branch saveBranch(Branch branch) throws AppException {
		if (!StringUtil.isEmptyString(branch.getUsername()) && !StringUtil.isEmptyString(branch.getUsername())){
			User user = new User(branch.getUsername(), branch.getPassword());
			user.setUserBranch(branch);
			userManager.saveBranchProfile(user);
		}
		
		if(!StringUtil.isEmptyString(branch.getId())){
			return branchDao.saveBranch(branch);
		} else {
			return saveBranch(branch);
		}
		
	}

	public List<Branch> getBranches(String[] branchIds) throws AppException {
		return branchDao.getBranches(branchIds);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getAllBranches() throws AppException {
		List<Branch> branches = branchDao.getBranches();
		List<LabelValue> list = new ArrayList<LabelValue>();

		for (Branch branch : branches) {
			list.add(new LabelValue(branch.getBranchName(), branch.getId()));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Branch> getBranches() throws AppException {
		return branchDao.getBranches();
	}

	/**
	 * {@inheritDoc}
	 */
	public Branch getBranch(String id) throws AppException {
		return branchDao.getBranch(id);
	}

	public List<Branch> getPartnerBranches(String partnerName){
		return branchDao.getPartnerBranches(partnerName);
	}
}
