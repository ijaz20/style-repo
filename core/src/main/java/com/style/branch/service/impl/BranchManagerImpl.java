package com.style.branch.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.style.branch.dao.BranchDao;
import com.style.branch.service.BranchManager;
import com.style.exception.AppException;
import com.style.model.Branch;
import com.style.model.LabelValue;
import com.style.service.impl.GenericManagerImpl;

/**
 * Implementation of BranchManager interface.
 * 
 * @author mathi
 */
@Service("branchManager")
public class BranchManagerImpl extends GenericManagerImpl<Branch, String>
		implements BranchManager {

	private BranchDao branchDao;

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

	/**
	 * {@inheritDoc}
	 */
	public Branch saveBranch(Branch branch) throws AppException {
		return branchDao.saveBranch(branch);
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

}
