package com.ruoyi.project.brand.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.project.category.domain.TbCategory;
import com.ruoyi.project.category.service.ITbCategoryService;
import com.ruoyi.project.takeInfo.domain.TbTakeInfo;
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
@Service("brandService")
public class TbBrandServiceImpl implements ITbBrandService 
{
    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Autowired
    private ITbCategoryService categoryService;

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

    @Override
    public List<TbBrand> selectTbBrandByCategoryId(String categoryId) {
        return tbBrandMapper.selectTbBrandByCategoryId(categoryId);
    }

    /**
     * 查询brand列表
     *
     * @param
     * @return brand
     */
    @Override
    public TbBrand selectTbBrandByName(TbBrand tbBrand)
    {
        return tbBrandMapper.selectTbBrandByName(tbBrand);
    }


    /**
     * 新增brand
     * 
     * @param tbBrand brand
     * @return 结果
     */
    @Transactional
    @Override
    public int insertTbBrand(TbBrand tbBrand) throws Exception {
        checkName(tbBrand);
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
    public int updateTbBrand(TbBrand tbBrand) throws Exception {
        checkName(tbBrand);
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

    public void checkName(TbBrand brand) throws Exception {
        TbBrand tbBrand = tbBrandMapper.selectTbBrandByName(brand);
        if (brand!=null){
            throw new Exception(brand.getName()+"已存在，请勿重复添加！！");
        }
    }

    public int importData(List<TbBrand> tbBrands) throws Exception {
        int rows = 1;
        Date date = new Date();
        List<TbBrand> list = new ArrayList<TbBrand>();
        for (TbBrand tbBrand : tbBrands) {
            TbCategory category = new TbCategory();
            category.setName(tbBrand.getCategoryName());
            TbCategory tbCategory = categoryService.selectTbCategoryByName(category);
            if (tbCategory==null){
                throw new Exception("物品：'" + tbBrand.getCategoryName() + "'在系统中未找到，先添加物品：'"+tbBrand.getCategoryName()+"'再导入");
            }
//            List<TbTakeInfo> takeInfoList = selectByMcAndGkdw(tKyglGfxgwxx.getMc(),tKyglGfxgwxx.getGkdw());
//            List<TKyglGfxgwxx> tKyglGfxgwxxes = selectTKyglGfxgwxxList(tKyglGfxgwxx);
//
//            //校验是否有相同记录存在表中
//            if (gfxgwxxs.size() > 0) {
//                throw new ServiceException("岗位+归口单位：" + tKyglGfxgwxx.getMc() +"&"+tKyglGfxgwxx.getGkdw()+
//                "填写的信息已有相同记录，请核对数据");
//            }
            tbBrand.setCategoryId(category.getId());
            tbBrand.setId(UUID.fastUUID().toString());
//            takeInfo.setCreateBy(ShiroUtils.getSysUser().getUserName());
            tbBrand.setCreateTime(date);
            list.add(tbBrand);
        }
        if (list.size() > 0) {
            rows = tbBrandMapper.insertBatch(list);
        }
        return rows;
    }
}
