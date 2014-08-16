package com.style.meta.service;

import java.util.List;

import com.style.model.Area;
import com.style.model.City;
import com.style.model.LabelValue;
import com.style.service.GenericManager;

/**
 * MetaDataManager Service Interface to handle communication between web and
 * persistence layer.
 * 
 * @author mathi
 */
public interface MetaDataManager extends GenericManager<Area, String> {

	/**
	 * auto complete area suggestion
	 * 
	 * @param query
	 * @return
	 */
	List<City> citySuggestions(String query);

	/**
	 * get area by id
	 * 
	 * @param id
	 * @return
	 */
	Area getArea(String id);

	/**
	 * get area by name
	 * 
	 * @param areaName
	 * @return
	 */
	Area getAreaByName(String areaName);

	List<LabelValue> getCitySuggestions(String query);
	
	List<LabelValue> getAreaSuggestions(String query, String cityId);
	
	/**
	 * auto complete city suggestion
	 * 
	 * @param query
	 * @return
	 */
	List<Area> areaSuggestions(String query, String cityId);

	/**
	 * get city by id
	 * 
	 * @param id
	 * @return
	 */
	City getCity(String id);

	/**
	 * get city by name
	 * 
	 * @param cityName
	 * @return
	 */
	City getCityByName(String cityName);

}
