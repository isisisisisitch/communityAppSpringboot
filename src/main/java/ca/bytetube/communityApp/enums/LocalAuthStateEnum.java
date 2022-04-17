package ca.bytetube.communityApp.enums;

public enum LocalAuthStateEnum {
	LOGINFAIL(-1, "pwd or username is wrong"), SUCCESS(0, "operate successfully"), NULL_AUTH_INFO(-1006,
			"registration info null"), ONLY_ONE_ACCOUNT(-1007,"only one acount");

	private int state;

	private String stateInfo;

	private LocalAuthStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static LocalAuthStateEnum stateOf(int index) {
		for (LocalAuthStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
