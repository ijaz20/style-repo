package com.style.branch.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.style.branch.dao.BranchDao;
import com.style.dao.hibernate.GenericDaoHibernate;
import com.style.exception.AppException;
import com.style.model.Branch;

/**
 * This class interacts with Hibernate session to save/delete and retrieve
 * Branch objects.
 * 
 * @author mathi
 */
@Repository("branchDao")
public class BranchDaoHibernate extends GenericDaoHibernate<Branch, String>
		implements BranchDao {

	public BranchDaoHibernate() {
		super(Branch.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Branch> getBranches(String[] branchIds) {
		try {
			List<Branch> branchList = (List<Branch>) getSession()
					.createQuery(
							"select branch from com.style.model.Branch as branch where branch.id in (:branchIds)")
					.setParameterList("branchIds", branchIds).list();
			return branchList;
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Branch saveBranch(Branch branch) {
		try {
			return (Branch) getSession().merge(branch);
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Branch> getBranches() {
		try {
			log.debug("Retrieving all branch names...");
			return getSession().createCriteria(Branch.class).list();
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Branch getBranch(String id) {
		try {
			List<Branch> branches = getSession().createCriteria(Branch.class)
					.add(Restrictions.eq("id", id)).list();
			if (branches.isEmpty()) {
				return null;
			} else {
				return branches.get(0);
			}
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage(), e);
		}
	}

}
