package com.ruoyi.project.category.mapper;

import java.util.List;
import com.ruoyi.project.category.domain.TbCategory;

/**
 * categoryMapper接口
 * 
 * @author ruoyi
 * @date 2021-11-21
 */
public interface TbCategoryMapper 
{
    /**
     * 查询category
     * 
     * @param id category主键
     * @return category
     */
    public TbCategory selectTbCategoryById(String id);

    /**
     * 查询category列表
     * 
     * @param tbCategory category
     * @return category集合
     */
    public List<TbCategory> selectTbCategoryList(TbCategory tbCategory);

    /**
     * 新增category
     * 
     * @param tbCategory category
     * @return 结果
     */
    public int insertTbCategory(TbCategory tbCategory);

    /**
     * 修改category
     * 
     * @param tbCategory category
     * @return 结果
     */
    public int updateTbCategory(TbCategory tbCategory);

    /**
     * 删除category
     * 
     * @param id category主键
     * @return 结果
     */
    public int deleteTbCategoryById(String id);

    /**
     * 批量删除category
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbCategoryByIds(String[] ids);

    TbCategory selectTbCategoryByName(TbCategory category);
}
