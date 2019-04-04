package com.example.demo.domain;

import java.io.Serializable;


/**
 * 部门管理
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:28:36
 */
public class DeptDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private String deptId;
    //上级部门ID，一级部门为0
    private String parentId;
    //部门名称
    private String name;
    //单位性质
    private String dwxz;
    //单位性质编号
    private String dwxzCode;
    //单位类型（外）
    private String dwlx;
    //发文单号
    private String fwdh;

    public String getFwdh() {
        return fwdh;
    }

    public void setFwdh(String fwdh) {
        this.fwdh = fwdh;
    }

    //组织机构代码
    private String zzjgdm;
    //地址
    private String address;
    //联系人
    private String lxr;
    //联系方式
    private String phone;
    //排序
    private Integer orderNum;
    //是否删除  -1：已删除  0：正常
    private Integer delFlag;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 设置：部门名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：部门名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取：排序
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置：是否删除  -1：已删除  0：正常
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取：是否删除  -1：已删除  0：正常
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    public String getZzjgdm() {
        return zzjgdm;
    }

    public void setZzjgdm(String zzjgdm) {
        this.zzjgdm = zzjgdm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDwxz() {
        return dwxz;
    }

    public void setDwxz(String dwxz) {
        this.dwxz = dwxz;
    }

    public String getDwlx() {
        return dwlx;
    }

    public void setDwlx(String dwlx) {
        this.dwlx = dwlx;
    }


    @Override
    public String toString() {
        return super.toString();
    }

	public String getDwxzCode() {
		return dwxzCode;
	}

	public void setDwxzCode(String dwxzCode) {
		this.dwxzCode = dwxzCode;
	}
    
    
}
