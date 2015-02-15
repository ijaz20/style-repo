package com.style.branch.dao;

import java.util.List;

import com.style.dao.GenericDao;
import com.style.model.Branch;

/**
 * Branch Data Access Object (GenericDao) interface.
 * 
 * @author mathi
 */
public interface BranchDao extends GenericDao<Branch, String> {

	/**
	 * save branch
	 * 
	 * @param branch
	 * @return
	 */
	Branch saveBranch(Branch branch);

	/**
	 * 
	 * @param branchIds
	 * @return
	 */
	List<Branch> getBranches(String[] branchIds);

	/**
	 * Returns all the branches
	 * 
	 * @return
	 */
	List<Branch> getBranches();

	/**
	 * get branch by id
	 * 
	 * @param id
	 * @return
	 */
	Branch getBranch(String id);

	/**
	 * get partner branches
	 * 
	 * @param partnerName
	 * @return
	 */
	List<Branch> getPartnerBranches(String partnerName);
}
