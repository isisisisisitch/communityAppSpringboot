package ca.bytetube.communityApp.enums;

/**
 * 使用枚举表述常量数据字典
 */
public enum PersonInfoStateEnum {

	SUCCESS(0, "Created successfully"), INNER_ERROR(-1001, "operation failed"), EMPTY(-1002, "person info empty");

	private int state;

	private String stateInfo;

	private PersonInfoStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static PersonInfoStateEnum stateOf(int index) {
		for (PersonInfoStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}