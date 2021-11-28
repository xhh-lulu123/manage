package com.ruoyi.project.category.service;

import java.util.List;
import com.ruoyi.project.category.domain.TbCategory;
import org.springframework.stereotype.Service;

/**
 * categoryService接口
 * 
 * @author ruoyi
 * @date 2021-11-21
 */
public interface ITbCategoryService 
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
    public int insertTbCategory(TbCategory tbCategory) throws Exception;

    /**
     * 修改category
     * 
     * @param tbCategory category
     * @return 结果
     */
    public int updateTbCategory(TbCategory tbCategory) throws Exception;

    public int updateTbCategoryTotal(TbCategory tbCategory);

    int updateTbCategoryInOut(TbCategory tbCategory);

    /**
     * 批量删除category
     * 
     * @param ids 需要删除的category主键集合
     * @return 结果
     */
    public int deleteTbCategoryByIds(String ids) throws Exception;

    /**
     * 删除category信息
     * 
     * @param id category主键
     * @return 结果
     */
    public int deleteTbCategoryById(String id);

    TbCategory selectTbCategoryByName(TbCategory category);
}
