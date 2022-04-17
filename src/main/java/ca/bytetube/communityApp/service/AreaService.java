package ca.bytetube.communityApp.service;



import ca.bytetube.communityApp.entity.Area;

import java.util.List;

public interface AreaService {

	public static final String AREALISTKEY = "arealist";
	List<Area> getAreaList();



}
