package com.ruoyi.project.category.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * category对象 tb_category
 * 
 * @author ruoyi
 * @date 2021-11-21
 */
public class TbCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 总数 */
    @Excel(name = "总数")
    private Long total;

    /** 流水总数 */
    @Excel(name = "流水总数")
    private Long inout;

    /** 剩余总数 */
    @Excel(name = "剩余总数")
    private Long surplus;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("total", getTotal())
            .append("inout", getInout())
            .append("surplus", getSurplus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
