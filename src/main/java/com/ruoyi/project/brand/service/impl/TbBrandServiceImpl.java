package com.ruoyi.project.brand.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.project.brand.mapper.TbBrandMapper;
import com.ruoyi.project.brand.domain.TbBrand;
import com.ruoyi.project.brand.service.ITbBrandService;
import com.ruoyi.common.utils.text.Convert;

/**
 * brandService业务层处理
 * 
 * @author ruoyi
 * @date 2021-11-21
 */
@Service
public class TbBrandServiceImpl implements ITbBrandService 
{
    @Autowired
    private TbBrandMapper tbBrandMapper;

    /**
     * 查询brand
     * 
     * @param id brand主键
     * @return brand
     */
    @Override
    public TbBrand selectTbBrandById(String id)
    {
        return tbBrandMapper.selectTbBrandById(id);
    }

    /**
     * 查询brand列表
     * 
     * @param tbBrand brand
     * @return brand
     */
    @Override
    public List<TbBrand> selectTbBrandList(TbBrand tbBrand)
    {
        return tbBrandMapper.selectTbBrandList(tbBrand);
    }

    /**
     * 新增brand
     * 
     * @param tbBrand brand
     * @return 结果
     */
    @Transactional
    @Override
    public int insertTbBrand(TbBrand tbBrand)
    {
        tbBrand.setId(UUID.randomUUID().toString());
        tbBrand.setCreateBy(ShiroUtils.getSysUser().getUserName());
        tbBrand.setCreateTime(DateUtils.getNowDate());
        int rows = tbBrandMapper.insertTbBrand(tbBrand);
        return rows;
    }

    /**
     * 修改brand
     * 
     * @param tbBrand brand
     * @return 结果
     */
    @Transactional
    @Override
    public int updateTbBrand(TbBrand tbBrand)
    {
        tbBrand.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        tbBrand.setUpdateTime(DateUtils.getNowDate());
        return tbBrandMapper.updateTbBrand(tbBrand);
    }

    /**
     * 批量删除brand
     * 
     * @param ids 需要删除的brand主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTbBrandByIds(String ids)
    {
        return tbBrandMapper.deleteTbBrandByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除brand信息
     * 
     * @param id brand主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTbBrandById(String id)
    {
        return tbBrandMapper.deleteTbBrandById(id);
    }
}
