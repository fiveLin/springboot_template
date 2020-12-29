package com.example.lin.pojo.edu;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("")
public class EduPo implements Serializable {
    /**
     * 
     */
    @ApiModelProperty("")
    private Integer id;

    /**
     * 类别id
     */
    @ApiModelProperty("类别id")
    private Integer cateId;

    /**
     * 培训名
     */
    @ApiModelProperty("培训名")
    private String trainName;

    /**
     * 培训地点
     */
    @ApiModelProperty("培训地点")
    private String trainAddr;

    /**
     * 培训对象id
     */
    @ApiModelProperty("培训对象id")
    private Integer trainTarget;

    /**
     * 培训所在部门id
     */
    @ApiModelProperty("培训所在部门id")
    private Integer deptId;

    /**
     * 培训所属区域id
     */
    @ApiModelProperty("培训所属区域id")
    private Integer areaId;

    /**
     * 培训所属行业id
     */
    @ApiModelProperty("培训所属行业id")
    private Integer industryId;

    /**
     * 培训额外信息
     */
    @ApiModelProperty("培训额外信息")
    private String extraInfo;

    /**
     * 培训照片ids
     */
    @ApiModelProperty("培训照片ids")
    private String picIds;

    /**
     * 培训文件ids
     */
    @ApiModelProperty("培训文件ids")
    private String fileIds;

    /**
     * 培训开始时间
     */
    @ApiModelProperty("培训开始时间")
    private Date trainBegin;

    /**
     * 培训结束时间
     */
    @ApiModelProperty("培训结束时间")
    private Date trainEnd;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date gmtUpdate;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    /**
     * 
     */
    @ApiModelProperty("")
    private Byte isDelete;

    /**
     * 培训讲师名
     */
    @ApiModelProperty("培训讲师名")
    private String teacherNames;

    /**
     * 培训内容
     */
    @ApiModelProperty("培训内容")
    private String trainContent;

    /**
     * T_TRAIN_DETAIL
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}