package com.ruoyi.project.takeInfo.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * takeInfo对象 tb_take_info
 * 
 * @author xiaohuanghua
 * @date 2021-11-21
 */
public class TbTakeInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String id;

    /**  */
    @Excel(name = "")
    private String brandId;

    /**  */
    @Excel(name = "")
    private Long shelvesId;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String categoryName;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String categoryId;

    /** 名称 */
    @Excel(name = "物品名称")
    private String brandName;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;


    /** 位置名称 */
    @Excel(name = "位置名称")
    private String shelvesName;

    /** 领走人 */
    @Excel(name = "领走人")
    private String takeBy;

    /** 领走时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "领走时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginTime;

    /** 领走时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "领走时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 领走时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "领走时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date takeTime;

    /** 领走数量 */
    @Excel(name = "领走数量")
    private Long takeNum;

    /** 流向 */
    @Excel(name = "流向")
    private String takeTo;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Long getShelvesId() {
        return shelvesId;
    }

    public void setShelvesId(Long shelvesId) {
        this.shelvesId = shelvesId;
    }

    public Date getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getShelvesName() {
        return shelvesName;
    }

    public void setShelvesName(String shelvesName) {
        this.shelvesName = shelvesName;
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
    public void setTakeBy(String takeBy)
    {
        this.takeBy = takeBy;
    }

    public String getTakeBy()
    {
        return takeBy;
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

    public void setTakeNum(Long takeNum)
    {
        this.takeNum = takeNum;
    }

    public Long getTakeNum()
    {
        return takeNum;
    }
    public void setTakeTo(String takeTo)
    {
        this.takeTo = takeTo;
    }

    public String getTakeTo()
    {
        return takeTo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("brandId", getBrandId())
            .append("takeBy", getTakeBy())
            .append("takeNum", getTakeNum())
            .append("takeTo", getTakeTo())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
