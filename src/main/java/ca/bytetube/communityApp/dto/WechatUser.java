package ca.bytetube.communityApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 微信用户实体类
 */
public class WechatUser implements Serializable {

    private static final long serialVersionUID = -4684067645282292327L;

    // openId,标识该公众号下面的该用户的唯一Id
    @JsonProperty("openid")
    private String openId;

    @JsonProperty("nickname")
    private String nickName;

    @JsonProperty("sex")
    private int sex;

    @JsonProperty("province")
    private String province;

    @JsonProperty("city")
    private String city;

    @JsonProperty("country")
    private String country;

    // 头像图片地址
    @JsonProperty("headimgurl")
    private String headimgurl;


    @JsonProperty("language")
    private String language;

    // 用户权限，这里没什么作用
    @JsonProperty("privilege")
    private String[] privilege;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String[] getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String[] privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return "openId:" + this.getOpenId() + ",nikename:" + this.getNickName();
    }
}
