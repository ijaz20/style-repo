package com.style.webapp.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.style.Constants;
import com.style.meta.service.MetaDataManager;
import com.style.model.Area;
import com.style.model.City;
import com.style.model.LabelValue;

/**
 * Action for facilitating meta data Management feature.
 * 
 * @auther mathi
 */

public class MetaDataAction extends BaseAction {

	private static final long serialVersionUID = 6776558938712115191L;
	private MetaDataManager metaDataManager;
	private List<LabelValue> cities;
	private List<LabelValue> areas;
	private String id;
	private Area area;
	private City city;
	private String areaId;
	private String cityId;
	private String countryCode;
	private String query;

	@Autowired
	public void setMetaDataManager(MetaDataManager metaDataManager) {
		this.metaDataManager = metaDataManager;
	}

	/**
	 * get area suggestions
	 * 
	 * @return
	 */
	public String getAreaSuggestions() {
		areas = metaDataManager.getAreaSuggestions(getQuery(), getCityId());
		return Constants.SUCCESS;
	}

	/**
	 * get city suggestions
	 * 
	 * @return
	 */
	public String getCitySuggestions() {
		cities = metaDataManager.getCitySuggestions(getQuery());
		return Constants.SUCCESS;
	}

	public List<LabelValue> getCities() {
		return cities;
	}

	public void setCities(List<LabelValue> cities) {
		this.cities = cities;
	}

	public List<LabelValue> getAreas() {
		return areas;
	}

	public void setAreas(List<LabelValue> areas) {
		this.areas = areas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}
