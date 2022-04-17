package ca.bytetube.communityApp.enums;

public enum ShopStateEnum {
	CHECK(0, "pending"),
	OFFLINE(-1, "illegal store"),
	SUCCESS(1, "operation success"),
	PASS(2, "authentication success"),
	INNER_ERROR(-1001, "internal system error"),
	NULL_SHOPID(-1002, "ShopId is null"),
	NULL_SHOP(-1003, "shop info is null");
	private int state;
	private String stateInfo;

	private ShopStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	/**
	 * 依据传入的state返回相应的enum值
	 */
	public static ShopStateEnum stateOf(int state) {
		for (ShopStateEnum stateEnum : values()) {
			if (stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

}
