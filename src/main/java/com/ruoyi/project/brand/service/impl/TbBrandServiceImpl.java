package com.ruoyi.project.brand.service.impl;

import java.util.*;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.project.category.domain.TbCategory;
import com.ruoyi.project.category.service.ITbCategoryService;
import com.ruoyi.project.receiveInfo.domain.TbReceiveInfo;
import com.ruoyi.project.receiveInfo.service.ITbReceiveInfoService;
import com.ruoyi.project.shelves.domain.TbShelves;
import com.ruoyi.project.shelves.service.ITbShelvesService;
import com.ruoyi.project.takeInfo.domain.TbTakeInfo;
import com.ruoyi.project.takeInfo.service.ITbTakeInfoService;
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

    @Autowired
    private ITbTakeInfoService tbTakeInfoService;
    @Autowired
    private ITbReceiveInfoService receiveInfoService;

    @Autowired
    private ITbShelvesService shelvesService;

    @Override
    public long selectNumByCategoryId(String cagtegoryId) {
        return tbBrandMapper.selectNumByCategoryId(cagtegoryId);
    }

    @Override
    public long selectTakeNumByCategoryId(String cagtegoryId) {
        return tbBrandMapper.selectTakeNumByCategoryId(cagtegoryId);
    }

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
        TbCategory category = new TbCategory();
        category.setId(tbBrand.getCategoryId());
        changeNum(category);
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
        int rows =  tbBrandMapper.updateTbBrand(tbBrand);
        TbCategory category = new TbCategory();
        category.setId(tbBrandMapper.selectTbBrandById(tbBrand.getId()).getCategoryId());
        changeNum(category);
        return rows;
    }
    @Override
    public int updateTbBrandInOut(TbBrand tbBrand) {
        return tbBrandMapper.updateTbBrandInOut(tbBrand);
    }

    /**
     * 批量删除brand
     * 
     * @param ids 需要删除的brand主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTbBrandByIds(String ids) throws Exception {
        String[] idStr = Convert.toStrArray(ids);
        List<String> categoryIds =  new ArrayList<>();
        for (String id : idStr) {
            TbTakeInfo takeInfo = new TbTakeInfo();
            takeInfo.setBrandId(id);
            List<TbTakeInfo> takeInfoList = tbTakeInfoService.selectTbTakeInfoList(takeInfo);
            if (takeInfoList!=null && takeInfoList.size()>0){
                TbBrand brand = tbBrandMapper.selectTbBrandById(id);
                String brandName = brand.getName();
                throw new Exception(brandName+"被引用，请先删除"+brandName+"下的领走物品");
            }
            TbReceiveInfo receiveInfo = new TbReceiveInfo();
            receiveInfo.setBrandId(id);
            List<TbReceiveInfo> receiveInfoList = receiveInfoService.selectTbReceiveInfoList(receiveInfo);
            if (receiveInfoList!=null && receiveInfoList.size()>0){
                TbBrand brand = tbBrandMapper.selectTbBrandById(id);
                String brandName = brand.getName();
                throw new Exception(brandName+"被引用，请先删除"+brandName+"下的存入物品");
            }
            categoryIds.add(tbBrandMapper.selectTbBrandById(id).getCategoryId());
        }
        int rows =  tbBrandMapper.deleteTbBrandByIds(Convert.toStrArray(ids));
        for (String id : categoryIds) {
            TbCategory category = new TbCategory();
            category.setId(id);
            changeNum(category);
        }
        return rows;
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
        if (tbBrand!=null){
            throw new Exception(brand.getName()+"已存在，请勿重复添加！！");
        }
    }

    public int importData(List<TbBrand> tbBrands) throws Exception {
        int rows = 1;
        Date date = new Date();
        List<TbBrand> list = new ArrayList<TbBrand>();
        Set<String> set = new HashSet<>();
        for (TbBrand tbBrand : tbBrands) {
            TbCategory category = new TbCategory();
            category.setName(tbBrand.getCategoryName());
            TbCategory tbCategory = categoryService.selectTbCategoryByName(category);
            if (tbCategory==null){
                throw new Exception("种类：'" + tbBrand.getCategoryName() + "'在系统中未找到，先添加种类：'"+tbBrand.getCategoryName()+"'再导入");
            }
            TbShelves shelves = shelvesService.selectTbShelvesByName(tbBrand.getShelvesName());
//            List<TbTakeInfo> takeInfoList = selectByMcAndGkdw(tKyglGfxgwxx.getMc(),tKyglGfxgwxx.getGkdw());
//            List<TKyglGfxgwxx> tKyglGfxgwxxes = selectTKyglGfxgwxxList(tKyglGfxgwxx);
//
//            //校验是否有相同记录存在表中
//            if (gfxgwxxs.size() > 0) {
//                throw new ServiceException("岗位+归口单位：" + tKyglGfxgwxx.getMc() +"&"+tKyglGfxgwxx.getGkdw()+
//                "填写的信息已有相同记录，请核对数据");
//            }
            tbBrand.setCategoryId(tbCategory.getId());
            if (shelves!=null){
                tbBrand.setShelvesId(shelves.getId());
            }
            tbBrand.setId(UUID.fastUUID().toString());
//            takeInfo.setCreateBy(ShiroUtils.getSysUser().getUserName());
            tbBrand.setCreateTime(date);
            list.add(tbBrand);
            set.add(tbCategory.getId());
        }
        if (list.size() > 0) {
            rows = tbBrandMapper.insertBatch(list);
        }
        if (set.size()>0){
            for (String categoryId : set) {
                TbCategory category = new TbCategory();
                category.setId(categoryId);
                changeNum(category);
            }
        }
        return rows;
    }
    public void changeNum(TbCategory category){
        long l = tbBrandMapper.selectCountByCategoryId(category.getId());
        category.setTotal(l);
        categoryService.updateTbCategoryTotal(category);
    }
}
