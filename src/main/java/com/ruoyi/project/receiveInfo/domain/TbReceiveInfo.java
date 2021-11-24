package com.ruoyi.project.receiveInfo.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * receiveInfo对象 tb_receive_info
 * 
 * @author ruoyi
 * @date 2021-11-23
 */
public class TbReceiveInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String id;

    /**  */

    private String brandId;
    /**  */

    private Long shelvesId;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String categoryName;

    /** 类别名称 */

    private String categoryId;

    /** 商品名称 */
    @Excel(name = "物品名称")
    private String brandName;


    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 存入人 */
    @Excel(name = "存入人")
    private String receiveBy;

    /** 存入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "存入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiveTime;

    /** 存入数量 */
    @Excel(name = "存入数量")
    private Long receiveNum;

    /** 存入来源 */
    @Excel(name = "存入来源")
    private String receiveTo;
    /** 位置名称 */
    @Excel(name = "位置名称")
    private String shelvesName;

    /** 领走时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")

    private Date beginTime;

    /** 领走时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")

    private Date endTime;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }

    public String getBrandId()
    {
        return brandId;
    }
    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }

    public String getBrandName()
    {
        return brandName;
    }
    public void setReceiveBy(String receiveBy)
    {
        this.receiveBy = receiveBy;
    }

    public String getReceiveBy()
    {
        return receiveBy;
    }
    public void setReceiveTime(Date receiveTime)
    {
        this.receiveTime = receiveTime;
    }

    public Date getReceiveTime()
    {
        return receiveTime;
    }
    public void setReceiveNum(Long receiveNum)
    {
        this.receiveNum = receiveNum;
    }

    public Long getReceiveNum()
    {
        return receiveNum;
    }
    public void setReceiveTo(String receiveTo)
    {
        this.receiveTo = receiveTo;
    }

    public String getReceiveTo()
    {
        return receiveTo;
    }

    public Long getShelvesId() {
        return shelvesId;
    }

    public void setShelvesId(Long shelvesId) {
        this.shelvesId = shelvesId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getShelvesName() {
        return shelvesName;
    }

    public void setShelvesName(String shelvesName) {
        this.shelvesName = shelvesName;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("brandId", getBrandId())
            .append("brandName", getBrandName())
            .append("receiveBy", getReceiveBy())
            .append("receiveTime", getReceiveTime())
            .append("receiveNum", getReceiveNum())
            .append("receiveTo", getReceiveTo())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
