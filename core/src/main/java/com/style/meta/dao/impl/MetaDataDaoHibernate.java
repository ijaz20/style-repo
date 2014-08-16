package com.style.meta.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.style.dao.hibernate.GenericDaoHibernate;
import com.style.meta.dao.MetaDataDao;
import com.style.model.Area;
import com.style.model.City;

/**
 * This class interacts with Hibernate session to save/delete and retrieve meta
 * data objects.
 * 
 * @author mathi
 */
@Repository("metaDataDao")
public class MetaDataDaoHibernate extends GenericDaoHibernate<Area, String>
		implements MetaDataDao {

	public MetaDataDaoHibernate() {
		super(Area.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<City> citySuggestions(String query) {
		List<City> cities = getSession().createCriteria(City.class)
				.add(Restrictions.like("cityName", "%" + query + "%"))
				.setMaxResults(10).list();
		return cities;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Area getArea(String id) {
		List<Area> areas = getSession().createCriteria(Area.class)
				.add(Restrictions.eq("id", id)).list();
		if (areas.isEmpty()) {
			return null;
		} else {
			return areas.get(0);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Area getAreaByName(String areaName) {
		List<Area> areas = getSession().createCriteria(Area.class)
				.add(Restrictions.eq("areaName", areaName)).list();
		if (areas.isEmpty()) {
			return null;
		} else {
			return areas.get(0);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Area> areaSuggestions(String query, String cityId) {
		List<Area> areas = getSession().createCriteria(Area.class, "area")
				.add(Restrictions.like("area.areaName", "%" + query + "%"))
				.createAlias("area.city", "city")
				.add(Restrictions.eq("city.id", cityId))
				.setMaxResults(10).list();
		return areas;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public City getCity(String id) {
		List<City> cities = getSession().createCriteria(City.class)
				.add(Restrictions.eq("id", id)).list();
		if (cities.isEmpty()) {
			return null;
		} else {
			return cities.get(0);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public City getCityByName(String cityName) {
		List<City> cities = getSession().createCriteria(City.class)
				.add(Restrictions.eq("cityName", cityName)).list();
		if (cities.isEmpty()) {
			return null;
		} else {
			return cities.get(0);
		}
	}
}
