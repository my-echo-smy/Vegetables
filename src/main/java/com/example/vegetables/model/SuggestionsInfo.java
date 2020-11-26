package com.example.vegetables.model;

import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

@TableName("suggestions_info")
public class SuggestionsInfo {
    private String staffNo;
    private String employeeName;
    private String orgUnit;
    private String positionName;
    private Date inputDatetime;
    private String checktype;
    private String dayTime;

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Date getInputDatetime() {
        return inputDatetime;
    }

    public void setInputDatetime(Date inputDatetime) {
        this.inputDatetime = inputDatetime;
    }

    public String getChecktype() {
        return checktype;
    }

    public void setChecktype(String checktype) {
        this.checktype = checktype;
    }
}
