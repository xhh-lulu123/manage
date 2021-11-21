package com.ruoyi.project.brand.mapper;

import java.util.List;
import com.ruoyi.project.brand.domain.TbBrand;

/**
 * brandMapper接口
 * 
 * @author ruoyi
 * @date 2021-11-21
 */
public interface TbBrandMapper 
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
     * 删除brand
     * 
     * @param id brand主键
     * @return 结果
     */
    public int deleteTbBrandById(String id);

    /**
     * 批量删除brand
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbBrandByIds(String[] ids);

}
