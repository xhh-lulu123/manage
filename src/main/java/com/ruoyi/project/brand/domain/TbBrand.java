package com.ruoyi.project.brand.domain;

import java.util.List;

import com.ruoyi.project.category.domain.TbCategory;
import com.ruoyi.project.shelves.domain.TbShelves;
import com.ruoyi.project.system.dept.domain.Dept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * brand对象 tb_brand
 * 
 * @author ruoyi
 * @date 2021-11-21
 */
public class TbBrand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String id;

    /**  */
    private String categoryId;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String categoryName;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 位置id */
    private Long shelvesId;

    /** 位置名称 */
    @Excel(name = "位置名称")
    private String shelvesName;

    /** 总数 */
    @Excel(name = "总数")
    private Long total;

    /** 流水总数 */
    @Excel(name = "流水总数")
    private Long inout;

    /** 剩余总数 */
    @Excel(name = "剩余总数")
    private Long surplus;

    private TbShelves shelves;

    /** category信息 */
    private List<TbCategory> tbCategoryList;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setCategoryId(String categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getCategoryId()
    {
        return categoryId;
    }
    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }
    public void setShelvesId(Long shelvesId)
    {
        this.shelvesId = shelvesId;
    }

    public Long getShelvesId()
    {
        return shelvesId;
    }
    public void setShelvesName(String shelvesName)
    {
        this.shelvesName = shelvesName;
    }

    public String getShelvesName()
    {
        return shelvesName;
    }
    public void setTotal(Long total)
    {
        this.total = total;
    }

    public Long getTotal()
    {
        return total;
    }
    public void setInout(Long inout)
    {
        this.inout = inout;
    }

    public Long getInout()
    {
        return inout;
    }
    public void setSurplus(Long surplus)
    {
        this.surplus = surplus;
    }

    public Long getSurplus()
    {
        return surplus;
    }

    public List<TbCategory> getTbCategoryList()
    {
        return tbCategoryList;
    }

    public void setTbCategoryList(List<TbCategory> tbCategoryList)
    {
        this.tbCategoryList = tbCategoryList;
    }

    public TbShelves getShelves() {
        if (shelves == null)
        {
            shelves = new TbShelves();
        }
        return shelves;
    }

    public void setShelves(TbShelves shelves) {
        this.shelves = shelves;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("categoryId", getCategoryId())
            .append("categoryName", getCategoryName())
            .append("name", getName())
            .append("unit", getUnit())
            .append("shelvesId", getShelvesId())
            .append("shelvesName", getShelvesName())
            .append("total", getTotal())
            .append("inout", getInout())
            .append("surplus", getSurplus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("tbCategoryList", getTbCategoryList())
            .toString();
    }
}
