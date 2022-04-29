package ca.bytetube.communityApp.enums;

public enum UserAwardMapStateEnum {
    SUCCESS(1, "operation success"), INNER_ERROR(-1001, "operation failed"), NULL_USERAWARD_ID(-1002,
            "UserAwardId is null"), NULL_USERAWARD_INFO(-1003, "NULL USERAWARD INFO");

    private int state;

    private String stateInfo;

    private UserAwardMapStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static UserAwardMapStateEnum stateOf(int index) {
        for (UserAwardMapStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
