package com.ruoyi.project.receiveInfo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.project.brand.domain.TbBrand;
import com.ruoyi.project.brand.service.ITbBrandService;
import com.ruoyi.project.category.domain.TbCategory;
import com.ruoyi.project.category.service.ITbCategoryService;
import com.ruoyi.project.takeInfo.domain.TbTakeInfo;
import com.ruoyi.project.takeInfo.service.ITbTakeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.receiveInfo.mapper.TbReceiveInfoMapper;
import com.ruoyi.project.receiveInfo.domain.TbReceiveInfo;
import com.ruoyi.project.receiveInfo.service.ITbReceiveInfoService;
import com.ruoyi.common.utils.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * receiveInfoService业务层处理
 *
 * @author ruoyi
 * @date 2021-11-23
 */
@Service
public class TbReceiveInfoServiceImpl implements ITbReceiveInfoService {
    @Autowired
    private TbReceiveInfoMapper tbReceiveInfoMapper;
    @Autowired
    private ITbBrandService brandService;

    @Autowired
    private ITbCategoryService categoryService;

    @Autowired
    private ITbTakeInfoService takeInfoService;

    /**
     * 查询receiveInfo
     *
     * @param id receiveInfo主键
     * @return receiveInfo
     */
    @Override
    public TbReceiveInfo selectTbReceiveInfoById(String id) {
        return tbReceiveInfoMapper.selectTbReceiveInfoById(id);
    }

    /**
     * 查询receiveInfo列表
     *
     * @param tbReceiveInfo receiveInfo
     * @return receiveInfo
     */
    @Override
    public List<TbReceiveInfo> selectTbReceiveInfoList(TbReceiveInfo tbReceiveInfo) {
        return tbReceiveInfoMapper.selectTbReceiveInfoList(tbReceiveInfo);
    }

    /**
     * 新增receiveInfo
     *
     * @param tbReceiveInfo receiveInfo
     * @return 结果
     */
    @Override
    @Transactional
    public int insertTbReceiveInfo(TbReceiveInfo tbReceiveInfo) {
        tbReceiveInfo.setId(UUID.randomUUID().toString());
        tbReceiveInfo.setCreateBy(ShiroUtils.getSysUser().getUserName());
        tbReceiveInfo.setCreateTime(DateUtils.getNowDate());
        int result = tbReceiveInfoMapper.insertTbReceiveInfo(tbReceiveInfo);
        changeNum(tbReceiveInfo);
        return result;
    }

    /**
     * 修改receiveInfo
     *
     * @param tbReceiveInfo receiveInfo
     * @return 结果
     */
    @Override
    @Transactional
    public int updateTbReceiveInfo(TbReceiveInfo tbReceiveInfo) {
        tbReceiveInfo.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        tbReceiveInfo.setUpdateTime(DateUtils.getNowDate());
        int result = tbReceiveInfoMapper.updateTbReceiveInfo(tbReceiveInfo);
        changeNum(tbReceiveInfo);
        return result;
    }

    /**
     * 批量删除receiveInfo
     *
     * @param ids 需要删除的receiveInfo主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteTbReceiveInfoByIds(String ids) {
        int result = 0;
        for (String id : Convert.toStrArray(ids)) {
            result = result + deleteTbReceiveInfoById(id);
        }
        return result;
    }

    /**
     * 删除receiveInfo信息
     *
     * @param id receiveInfo主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteTbReceiveInfoById(String id) {
        TbReceiveInfo receiveInfo = tbReceiveInfoMapper.selectTbReceiveInfoById(id);
        int result = tbReceiveInfoMapper.deleteTbReceiveInfoById(id);
        TbBrand tbBrand = brandService.selectTbBrandById(receiveInfo.getBrandId());
        // 存入总数
        long num = tbReceiveInfoMapper.selectNumByBrandId(receiveInfo.getBrandId());
        TbBrand brand = new TbBrand();
        brand.setId(receiveInfo.getBrandId());
        brand.setInout(num);
        // 领走总数
        long takeNum = takeInfoService.selectTakeNumByBrandId(receiveInfo.getBrandId());
        brand.setSurplus(num - takeNum);
        brandService.updateTbBrandInOut(brand);
        TbCategory category = new TbCategory();
        category.setId(tbBrand.getCategoryId());
        long l = brandService.selectNumByCategoryId(tbBrand.getCategoryId());
        long l2 = brandService.selectTakeNumByCategoryId(tbBrand.getCategoryId());
        category.setInout(l);
        category.setSurplus(l2);

        categoryService.updateTbCategoryInOut(category);

        return result;
    }

    @Override
    @Transactional
    public int importData(List<TbReceiveInfo> tbReceiveInfos) throws Exception {
        int rows = 1;
        Date date = new Date();
        List<TbReceiveInfo> list = new ArrayList<TbReceiveInfo>();
        for (TbReceiveInfo receiveInfo : tbReceiveInfos) {
            TbBrand tbBrand = new TbBrand();
            tbBrand.setName(receiveInfo.getBrandName());
            TbBrand brand = brandService.selectTbBrandByName(tbBrand);
            if (brand == null) {
                throw new Exception("物品：'" + receiveInfo.getBrandName() + "'在系统中未找到，先添加物品：'" + receiveInfo.getBrandName() + "'再导入");
            }
//            List<TbTakeInfo> takeInfoList = selectByMcAndGkdw(tKyglGfxgwxx.getMc(),tKyglGfxgwxx.getGkdw());
//            List<TKyglGfxgwxx> tKyglGfxgwxxes = selectTKyglGfxgwxxList(tKyglGfxgwxx);
//
//            //校验是否有相同记录存在表中
//            if (gfxgwxxs.size() > 0) {
//                throw new ServiceException("岗位+归口单位：" + tKyglGfxgwxx.getMc() +"&"+tKyglGfxgwxx.getGkdw()+
//                "填写的信息已有相同记录，请核对数据");
//            }
            receiveInfo.setBrandId(brand.getId());
            receiveInfo.setId(UUID.fastUUID().toString());
            receiveInfo.setCreateBy(ShiroUtils.getSysUser().getUserName());
            receiveInfo.setCreateTime(date);
            receiveInfo.setId(UUID.randomUUID().toString());
            list.add(receiveInfo);
        }
        if (list.size() > 0) {
            rows = tbReceiveInfoMapper.insertBatch(list);
            list.forEach(e -> {
                changeNum(e);
            });
        }
        return rows;
    }


    @Transactional
    public void changeNum(TbReceiveInfo receiveInfo) {
        if (receiveInfo.getReceiveNum() != null) {
            // 存入信息
            TbReceiveInfo receiveInfo1 = tbReceiveInfoMapper.selectTbReceiveInfoById(receiveInfo.getId());
            TbBrand tbBrand = new TbBrand();
            // 存入总数
            Long num = tbReceiveInfoMapper.selectNumByBrandId(receiveInfo1.getBrandId());
            // 领走总数
            long takeNum = takeInfoService.selectTakeNumByBrandId(receiveInfo1.getBrandId());
            tbBrand.setInout(num);
            tbBrand.setSurplus(num - takeNum);
            tbBrand.setId(receiveInfo1.getBrandId());
            brandService.updateTbBrandInOut(tbBrand);

            TbBrand brand = brandService.selectTbBrandById(receiveInfo1.getBrandId());
            TbCategory category = new TbCategory();
            category.setId(brand.getCategoryId());
            long l = brandService.selectNumByCategoryId(brand.getCategoryId());
            long l2 = brandService.selectTakeNumByCategoryId(brand.getCategoryId());
            category.setInout(l);
            category.setSurplus(l2);

            categoryService.updateTbCategoryInOut(category);

//            brandService.selectTbBrandById()
//            brandService.selectNumByCategoryId()
        }
    }
}
