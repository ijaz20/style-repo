package com.style.branch.service;

import java.util.List;

import com.style.exception.AppException;
import com.style.model.Branch;
import com.style.model.LabelValue;
import com.style.service.GenericManager;

/**
 * BranchManager.java Service Interface to handle communication between web and
 * persistence layer.
 * 
 * @author mathi
 */
public interface BranchManager extends GenericManager<Branch, String> {

	/**
	 * save branch
	 * 
	 * @param branch
	 * @return
	 */
	Branch saveBranch(Branch branch) throws AppException;

	/**
	 * 
	 * @param branchIds
	 * @return
	 */
	List<Branch> getBranches(String[] branchIds) throws AppException;

	/**
	 * Returns all the branches
	 * 
	 * @return
	 */
	List<LabelValue> getAllBranches() throws AppException;

	/**
	 * Returns all the branches
	 * 
	 * @return
	 */
	List<Branch> getBranches() throws AppException;

	/**
	 * get branch by id
	 * 
	 * @param id
	 * @return
	 */
	Branch getBranch(String id) throws AppException;
	
	/**
	 * get partner branches
	 * 
	 * @param partnerName
	 * @return
	 */
	List<Branch> getPartnerBranches(String partnerName);

}
