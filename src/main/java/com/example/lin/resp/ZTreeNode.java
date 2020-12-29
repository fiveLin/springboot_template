/**
 *
 * Project Name: scooper-core
 * File Name: ZTree.java
 * Package Name: cn.showclear.www.json
 * Description:
 * Copyright: Copyright (c) 2016
 * Company: scooper
 * @version 0.0.1
 * @author zhengkai
 * @date 2016年8月9日上午10:58:50
 */
package com.example.lin.resp;

import java.io.Serializable;

/**
 * 树型结构实体
 * Description:
 * @version 0.0.1
 * @author zhengkai
 * @date 2016年8月9日上午10:58:50
 */
public class ZTreeNode implements Serializable {

    private static final long serialVersionUID = -5599152087438972267L;

    /** 节点id */
    private Integer id;
    /** 父节点id */
    private Integer pId;
    /** 节点名称 */
    private String name;
    /** 是否是父节点 */
    private boolean isIsParent;

    private boolean checked = false;

    /** 子节点数量 */
    private Long childNum;

    /** 数据类型 example="dept"*/
    private String dataType;

    /** 自定义数据 example="331082.000001"*/
    private String data;


    /**
     *
     * Title:  ZTree构造函数
     * @Description
     * @param id            节点id
     * @param pId           父节点id
     * @param name          节点名称
     * @param isParent      是否是父节点
     */
    public ZTreeNode(Integer id, Integer pId, String name, boolean isIsParent) {
        super();
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.isIsParent = isIsParent;
    }



    /**
     * 权限
     * @param id
     * @param pId
     * @param name
     * @param checked
     */
    public ZTreeNode(Integer id, Integer pId, boolean checked, String name) {
        super();
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.checked = checked;
    }



    /**
     * @param id
     * @param pId
     * @param name
     * @param isIsParent
     * @param dataType
     */
    public ZTreeNode(Integer id, Integer pId, String name, boolean isIsParent, String dataType) {
        super();
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.isIsParent = isIsParent;
        this.dataType = dataType;
    }



    /**
     * @param id
     * @param pId
     * @param name
     * @param isIsParent
     * @param dataType
     * @param data
     */
    public ZTreeNode(Integer id, Integer pId, String name, boolean isIsParent, String dataType, String data) {
        super();
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.isIsParent = isIsParent;
        this.dataType = dataType;
        this.data = data;
    }



    /**
     * @param id
     * @param pId
     * @param name
     * @param isIsParent
     * @param childNum
     * @param dataType
     * @param data
     */
    public ZTreeNode(Integer id, Integer pId, String name, boolean isIsParent, Long childNum, String dataType,
                     String data) {
        super();
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.isIsParent = isIsParent;
        this.childNum = childNum;
        this.dataType = dataType;
        this.data = data;
    }



    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the pid
     */
    public Integer getPId() {
        return pId;
    }
    /**
     * @param pid the pid to set
     */
    public void setPId(Integer pId) {
        this.pId = pId;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the isParent
     */
    public boolean isIsParent() {
        return isIsParent;
    }
    /**
     * @param isParent the isParent to set
     */
    public void setIsParent(boolean isParent) {
        this.isIsParent = isParent;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Long getChildNum() {
        return childNum;
    }

    public void setChildNum(Long childNum) {
        this.childNum = childNum;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
