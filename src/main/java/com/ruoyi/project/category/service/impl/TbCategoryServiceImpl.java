package com.ruoyi.project.category.service.impl;

import java.util.Arrays;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.project.brand.domain.TbBrand;
import com.ruoyi.project.brand.service.ITbBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.category.mapper.TbCategoryMapper;
import com.ruoyi.project.category.domain.TbCategory;
import com.ruoyi.project.category.service.ITbCategoryService;
import com.ruoyi.common.utils.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * categoryService业务层处理
 * 
 * @author ruoyi
 * @date 2021-11-21
 */
@Service("categoryService")
public class TbCategoryServiceImpl implements ITbCategoryService 
{
    @Autowired
    private TbCategoryMapper tbCategoryMapper;
    @Autowired
    private ITbBrandService brandService;

    /**
     * 查询category
     * 
     * @param id category主键
     * @return category
     */
    @Override
    public TbCategory selectTbCategoryById(String id)
    {
        return tbCategoryMapper.selectTbCategoryById(id);
    }

    /**
     * 查询category列表
     * 
     * @param tbCategory category
     * @return category
     */
    @Override
    public List<TbCategory> selectTbCategoryList(TbCategory tbCategory)
    {
        return tbCategoryMapper.selectTbCategoryList(tbCategory);
    }

    /**
     * 新增category
     * 
     * @param tbCategory category
     * @return 结果
     */
    @Override
    public int insertTbCategory(TbCategory tbCategory) throws Exception {
        checkName(tbCategory);
        tbCategory.setId(UUID.randomUUID().toString());
        tbCategory.setCreateBy(ShiroUtils.getLoginName());
        tbCategory.setCreateTime(DateUtils.getNowDate());
        return tbCategoryMapper.insertTbCategory(tbCategory);
    }

    /**
     * 修改category
     * 
     * @param tbCategory category
     * @return 结果
     */
    @Override
    public int updateTbCategory(TbCategory tbCategory) throws Exception {
        checkName(tbCategory);
        tbCategory.setCreateBy(ShiroUtils.getLoginName());
        tbCategory.setUpdateTime(DateUtils.getNowDate());
        return tbCategoryMapper.updateTbCategory(tbCategory);
    }

    @Override
    public int updateTbCategoryTotal(TbCategory tbCategory) {
        return tbCategoryMapper.updateTbCategory(tbCategory);
    }

    @Override
    public int updateTbCategoryInOut(TbCategory tbCategory) {
        return tbCategoryMapper.updateTbCategoryInOut(tbCategory);
    }

    /**
     * 批量删除category
     * 
     * @param ids 需要删除的category主键
     * @return 结果
     */
    @Override
    public int deleteTbCategoryByIds(String ids) throws Exception {
        List<String> idList = Arrays.asList(Convert.toStrArray(ids));
        for (String id : idList) {
            TbBrand brand = new TbBrand();
            brand.setCategoryId(id);
            List<TbBrand> tbBrands = brandService.selectTbBrandList(brand);
            if (tbBrands!=null && tbBrands.size()>0){
                TbCategory category = selectTbCategoryById(id);
                throw new Exception(category.getName()+"被引用，请先删除"+category.getName()+"下的物品");
            }
        }
        return tbCategoryMapper.deleteTbCategoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除category信息
     * 
     * @param id category主键
     * @return 结果
     */
    @Override
    public int deleteTbCategoryById(String id)
    {
        return tbCategoryMapper.deleteTbCategoryById(id);
    }

    @Override
    public TbCategory selectTbCategoryByName(TbCategory category) {
        return tbCategoryMapper.selectTbCategoryByName(category);
    }

    public void checkName(TbCategory category) throws Exception {
        TbCategory tbCategory = tbCategoryMapper.selectTbCategoryByName(category);
        if (tbCategory!=null){
            throw new Exception(category.getName()+"已存在，请勿重复添加！！");
        }
    }
}
