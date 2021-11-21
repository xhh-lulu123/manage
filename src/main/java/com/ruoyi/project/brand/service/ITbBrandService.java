package com.ruoyi.project.brand.service;

import java.util.List;
import com.ruoyi.project.brand.domain.TbBrand;

/**
 * brandService接口
 * 
 * @author ruoyi
 * @date 2021-11-21
 */
public interface ITbBrandService 
{
    /**
     * 查询brand
     * 
     * @param id brand主键
     * @return brand
     */
    public TbBrand selectTbBrandById(String id);

    /**
     * 查询brand列表
     * 
     * @param tbBrand brand
     * @return brand集合
     */
    public List<TbBrand> selectTbBrandList(TbBrand tbBrand);

    public List<TbBrand> selectTbBrandBy(String name);

    /**
     * 新增brand
     * 
     * @param tbBrand brand
     * @return 结果
     */
    public int insertTbBrand(TbBrand tbBrand);

    /**
     * 修改brand
     * 
     * @param tbBrand brand
     * @return 结果
     */
    public int updateTbBrand(TbBrand tbBrand);

    /**
     * 批量删除brand
     * 
     * @param ids 需要删除的brand主键集合
     * @return 结果
     */
    public int deleteTbBrandByIds(String ids);

    /**
     * 删除brand信息
     * 
     * @param id brand主键
     * @return 结果
     */
    public int deleteTbBrandById(String id);
}
