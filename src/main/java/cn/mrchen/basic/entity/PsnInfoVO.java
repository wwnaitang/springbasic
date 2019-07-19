package cn.mrchen.basic.entity;

import java.util.Date;

public class PsnInfoVO {

    private String pkPsnInfo;
    private String code;
    private String name;
    private Date birthday;
    private Integer age;

    public PsnInfoVO(String pkPsnInfo, String code, String name, Date birthday, Integer age) {
        this.pkPsnInfo = pkPsnInfo;
        this.code = code;
        this.name = name;
        this.birthday = birthday;
        this.age = age;
    }

    public PsnInfoVO() {
    }

    public String getPkPsnInfo() {
        return pkPsnInfo;
    }

    public void setPkPsnInfo(String pkPsnInfo) {
        this.pkPsnInfo = pkPsnInfo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
