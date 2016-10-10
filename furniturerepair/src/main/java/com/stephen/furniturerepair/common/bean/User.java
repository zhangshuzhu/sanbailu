package com.stephen.furniturerepair.common.bean;

/**
 * Created by Stephen on 10/10/2016.
 * Emial: 895745843@qq.com
 */
public class User {

    /**
     * id : 3
     * account : zzzz123
     * password : e10adc3949ba59abbe56e057f20f883e
     * aid : 6
     * type : 0
     * name : 1111
     * nickname :
     * mobile : 1358858
     * avatar : null
     * sex : 男
     * address : hjkkoi
     * technical : null
     * expertise : null
     * prictise : null
     * cert_type : null
     * cert_no : null
     * pay_type : null
     * pay_no : null
     * create_time : 1474363412
     * update_time : 1474363412
     */

    private String id;
    private String account;
    private String password;
    private String aid;
    private String type;
    private String name;
    private String nickname;
    private String mobile;
    private String avatar;
    private String sex;
    private String address;
    private String technical;
    private String expertise;
    private String prictise;
    private String cert_type;
    private String cert_no;
    private String pay_type;
    private String pay_no;
    private String create_time;
    private String update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTechnical() {
        return technical;
    }

    public void setTechnical(String technical) {
        this.technical = technical;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getPrictise() {
        return prictise;
    }

    public void setPrictise(String prictise) {
        this.prictise = prictise;
    }

    public String getCert_type() {
        return cert_type;
    }

    public void setCert_type(String cert_type) {
        this.cert_type = cert_type;
    }

    public String getCert_no() {
        return cert_no;
    }

    public void setCert_no(String cert_no) {
        this.cert_no = cert_no;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getPay_no() {
        return pay_no;
    }

    public void setPay_no(String pay_no) {
        this.pay_no = pay_no;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", aid='" + aid + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", avatar=" + avatar +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", technical=" + technical +
                ", expertise=" + expertise +
                ", prictise=" + prictise +
                ", cert_type=" + cert_type +
                ", cert_no=" + cert_no +
                ", pay_type=" + pay_type +
                ", pay_no=" + pay_no +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}
