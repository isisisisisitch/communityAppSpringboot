package ca.bytetube.communityApp.enums;

public enum UserShopMapStateEnum {
    SUCCESS(1, "operation success"), INNER_ERROR(-1001, "operation failed"), NULL_USERSHOP_ID(-1002, "UserShopId is null"), NULL_USERSHOP_INFO(-1003,
            "NULL USERSHOP INFO");

    private int state;

    private String stateInfo;

    private UserShopMapStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static UserShopMapStateEnum stateOf(int index) {
        for (UserShopMapStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
