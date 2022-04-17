package ca.bytetube.communityApp.enums;

public enum HeadLineStateEnum {
	SUCCESS(0, "Created successfully"), INNER_ERROR(-1001, "operation failed"), EMPTY(-1002, "headline empty");

	private int state;

	private String stateInfo;

	private HeadLineStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static HeadLineStateEnum stateOf(int index) {
		for (HeadLineStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
