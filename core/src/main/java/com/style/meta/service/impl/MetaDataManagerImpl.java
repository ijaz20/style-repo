package com.style.meta.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.style.meta.dao.MetaDataDao;
import com.style.meta.service.MetaDataManager;
import com.style.model.Area;
import com.style.model.City;
import com.style.model.LabelValue;
import com.style.service.impl.GenericManagerImpl;

/**
 * Implementation of MetaDataManager interface.
 * 
 * @author mathi
 */
@Service("metaDataManager")
public class MetaDataManagerImpl extends GenericManagerImpl<Area, String>
		implements MetaDataManager {

	private MetaDataDao metaDataDao;

	@Autowired
	public MetaDataManagerImpl(MetaDataDao metaDataDao) {
		super(metaDataDao);
		this.metaDataDao = metaDataDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<City> citySuggestions(String query) {
		return metaDataDao.citySuggestions(query);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getCitySuggestions(String query) {
		List<City> cities = metaDataDao.citySuggestions(query);
		List<LabelValue> list = new ArrayList<LabelValue>();

		for (City city : cities) {
			list.add(new LabelValue(city.getCityName(), city.getId()));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getAreaSuggestions(String query, String cityId) {
		List<Area> areas = metaDataDao.areaSuggestions(query, cityId);
		List<LabelValue> list = new ArrayList<LabelValue>();

		for (Area area : areas) {
			list.add(new LabelValue(area.getAreaName(), area.getId()));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public Area getArea(String id) {
		return metaDataDao.get(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public Area getAreaByName(String areaName) {
		return metaDataDao.getAreaByName(areaName);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Area> areaSuggestions(String query, String cityId) {
		return metaDataDao.areaSuggestions(query, cityId);
	}

	/**
	 * {@inheritDoc}
	 */
	public City getCity(String id) {
		return metaDataDao.getCity(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public City getCityByName(String cityName) {
		return metaDataDao.getCityByName(cityName);
	}

}
