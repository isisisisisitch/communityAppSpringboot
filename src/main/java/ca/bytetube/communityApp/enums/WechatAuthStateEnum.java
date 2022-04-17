package ca.bytetube.communityApp.enums;

public enum WechatAuthStateEnum {
	LOGINFAIL(-1, "openId error"), SUCCESS(0, "Created successfully"), NULL_AUTH_INFO(-1006, "NULL AUTH INFO");

	private int state;

	private String stateInfo;

	private WechatAuthStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static WechatAuthStateEnum stateOf(int index) {
		for (WechatAuthStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
