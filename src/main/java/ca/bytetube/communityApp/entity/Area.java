package ca.bytetube.communityApp.entity;

import java.util.Date;

public class Area {
	//为什么使用包装类型而不是基本数据类型？ 答案：基本数据类型有默认值，可能会影响结果
	private Integer areaId;

	private String areaName;
	// 权重，越大越排前显示
	private Integer priority;

	private Date createTime;

	private Date lastEditTime;

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

}
