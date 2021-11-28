package com.ruoyi.project.takeInfo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.brand.domain.TbBrand;
import com.ruoyi.project.brand.service.ITbBrandService;
import com.ruoyi.project.category.domain.TbCategory;
import com.ruoyi.project.category.service.ITbCategoryService;
import com.ruoyi.project.receiveInfo.domain.TbReceiveInfo;
import com.ruoyi.project.receiveInfo.service.ITbReceiveInfoService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.takeInfo.mapper.TbTakeInfoMapper;
import com.ruoyi.project.takeInfo.domain.TbTakeInfo;
import com.ruoyi.project.takeInfo.service.ITbTakeInfoService;
import com.ruoyi.common.utils.text.Convert;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.rowset.BaseRowSet;

/**
 * takeInfoService业务层处理
 *
 * @author xiaohuanghua
 * @date 2021-11-21
 */
@Service
public class TbTakeInfoServiceImpl implements ITbTakeInfoService {
    @Autowired
    private TbTakeInfoMapper tbTakeInfoMapper;
    @Autowired
    private ITbBrandService brandService;
    @Autowired
    private ITbCategoryService categoryService;
    @Autowired
    private ITbReceiveInfoService receiveInfoService;

    /**
     * 查询takeInfo
     *
     * @param id takeInfo主键
     * @return takeInfo
     */
    @Override
    public TbTakeInfo selectTbTakeInfoById(String id) {
        return tbTakeInfoMapper.selectTbTakeInfoById(id);
    }

    @Override
    public long selectTakeNumByBrandId(String brandId) {
        return tbTakeInfoMapper.selectTakeNumByBrandId(brandId);
    }

    /**
     * 查询takeInfo列表
     *
     * @param tbTakeInfo takeInfo
     * @return takeInfo
     */
    @Override
    public List<TbTakeInfo> selectTbTakeInfoList(TbTakeInfo tbTakeInfo) {
        return tbTakeInfoMapper.selectTbTakeInfoList(tbTakeInfo);
    }

    /**
     * 新增takeInfo
     *
     * @param tbTakeInfo takeInfo
     * @return 结果
     */
    @Override
    @Transactional
    public int insertTbTakeInfo(TbTakeInfo tbTakeInfo) {
        tbTakeInfo.setId(UUID.randomUUID().toString());
        tbTakeInfo.setCreateBy(ShiroUtils.getSysUser().getUserName());
        tbTakeInfo.setCreateTime(DateUtils.getNowDate());
        int result = tbTakeInfoMapper.insertTbTakeInfo(tbTakeInfo);
        changeNum(tbTakeInfo);
        return result;
    }

    /**
     * 修改takeInfo
     *
     * @param tbTakeInfo takeInfo
     * @return 结果
     */
    @Override
    @Transactional
    public int updateTbTakeInfo(TbTakeInfo tbTakeInfo) {
        tbTakeInfo.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        tbTakeInfo.setUpdateTime(DateUtils.getNowDate());
        int result = tbTakeInfoMapper.updateTbTakeInfo(tbTakeInfo);
        changeNum(tbTakeInfo);
        return result;
    }

    /**
     * 批量删除takeInfo
     *
     * @param ids 需要删除的takeInfo主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteTbTakeInfoByIds(String ids) {
        int result = 0;
        for (String id : Convert.toStrArray(ids)) {
            result = result + deleteTbTakeInfoById(id);
        }
        return result;
    }

    /**
     * 删除takeInfo信息
     *
     * @param id takeInfo主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteTbTakeInfoById(String id) {
        TbTakeInfo takeInfo = tbTakeInfoMapper.selectTbTakeInfoById(id);
        int result = tbTakeInfoMapper.deleteTbTakeInfoById(id);
        String brandId = takeInfo.getBrandId();
        long inout = brandService.selectTbBrandById(brandId).getInout();
        //领走总数
        long num = tbTakeInfoMapper.selectTakeNumByBrandId(brandId);
        TbBrand brand = new TbBrand();
        brand.setId(brandId);
        brand.setSurplus(inout - num);
        brandService.updateTbBrandInOut(brand);
        TbBrand tbBrand = brandService.selectTbBrandById(brandId);
        TbCategory category = new TbCategory();
        category.setId(tbBrand.getCategoryId());
        long l = brandService.selectTakeNumByCategoryId(tbBrand.getCategoryId());
        category.setSurplus(l);

        categoryService.updateTbCategoryInOut(category);

        return result;
    }

    @Override
    public int importData(List<TbTakeInfo> tbTakeInfos) throws Exception {
        int rows = 1;
        Date date = new Date();
        List<TbTakeInfo> list = new ArrayList<TbTakeInfo>();
        for (TbTakeInfo takeInfo : tbTakeInfos) {
            TbBrand tbBrand = new TbBrand();
            tbBrand.setName(takeInfo.getBrandName());
            TbBrand brand = brandService.selectTbBrandByName(tbBrand);
            if (brand == null) {
                throw new Exception("物品：'" + takeInfo.getBrandName() + "'在系统中未找到，先添加物品：'" + takeInfo.getBrandName() + "'再导入");
            }
            takeInfo.setBrandId(brand.getId());
            takeInfo.setId(UUID.fastUUID().toString());
            takeInfo.setCreateBy(ShiroUtils.getSysUser().getUserName());
            takeInfo.setCreateTime(date);
            list.add(takeInfo);
        }
        if (list.size() > 0) {
            rows = tbTakeInfoMapper.insertBatch(list);
        }
        for (TbTakeInfo takeInfo : list) {
            changeNum(takeInfo);
        }
        return rows;
    }

    @Transactional
    public void changeNum(TbTakeInfo takeInfo) {
        if (takeInfo.getTakeNum() != null) {
            // 存入信息
            TbTakeInfo takeInfo1 = tbTakeInfoMapper.selectTbTakeInfoById(takeInfo.getId());
            String brandId = takeInfo1.getBrandId();
            TbBrand tbBrand = new TbBrand();
            // 存入总数
            Long num = tbTakeInfoMapper.selectTakeNumByBrandId(brandId);

            TbBrand brand = brandService.selectTbBrandById(brandId);
            tbBrand.setId(brandId);
            tbBrand.setSurplus(brand.getInout() - num);
            brandService.updateTbBrandInOut(tbBrand);

            TbCategory category = new TbCategory();
            category.setId(brand.getCategoryId());
            long l1 = brandService.selectTakeNumByCategoryId(brand.getCategoryId());
            category.setSurplus(l1);

            categoryService.updateTbCategoryInOut(category);

//            brandService.selectTbBrandById()
//            brandService.selectNumByCategoryId()
        }
    }
}
