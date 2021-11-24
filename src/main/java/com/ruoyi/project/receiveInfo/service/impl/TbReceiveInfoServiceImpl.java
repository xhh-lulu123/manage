package com.ruoyi.project.receiveInfo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.project.brand.domain.TbBrand;
import com.ruoyi.project.brand.service.ITbBrandService;
import com.ruoyi.project.takeInfo.domain.TbTakeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.receiveInfo.mapper.TbReceiveInfoMapper;
import com.ruoyi.project.receiveInfo.domain.TbReceiveInfo;
import com.ruoyi.project.receiveInfo.service.ITbReceiveInfoService;
import com.ruoyi.common.utils.text.Convert;

/**
 * receiveInfoService业务层处理
 * 
 * @author ruoyi
 * @date 2021-11-23
 */
@Service
public class TbReceiveInfoServiceImpl implements ITbReceiveInfoService 
{
    @Autowired
    private TbReceiveInfoMapper tbReceiveInfoMapper;
    @Autowired
    private ITbBrandService brandService;

    /**
     * 查询receiveInfo
     * 
     * @param id receiveInfo主键
     * @return receiveInfo
     */
    @Override
    public TbReceiveInfo selectTbReceiveInfoById(String id)
    {
        return tbReceiveInfoMapper.selectTbReceiveInfoById(id);
    }

    /**
     * 查询receiveInfo列表
     * 
     * @param tbReceiveInfo receiveInfo
     * @return receiveInfo
     */
    @Override
    public List<TbReceiveInfo> selectTbReceiveInfoList(TbReceiveInfo tbReceiveInfo)
    {
        return tbReceiveInfoMapper.selectTbReceiveInfoList(tbReceiveInfo);
    }

    /**
     * 新增receiveInfo
     * 
     * @param tbReceiveInfo receiveInfo
     * @return 结果
     */
    @Override
    public int insertTbReceiveInfo(TbReceiveInfo tbReceiveInfo)
    {
        tbReceiveInfo.setId(UUID.randomUUID().toString());
        tbReceiveInfo.setCreateBy(ShiroUtils.getSysUser().getUserName());
        tbReceiveInfo.setCreateTime(DateUtils.getNowDate());
        return tbReceiveInfoMapper.insertTbReceiveInfo(tbReceiveInfo);
    }

    /**
     * 修改receiveInfo
     * 
     * @param tbReceiveInfo receiveInfo
     * @return 结果
     */
    @Override
    public int updateTbReceiveInfo(TbReceiveInfo tbReceiveInfo)
    {
        tbReceiveInfo.setCreateBy(ShiroUtils.getSysUser().getUserName());
        tbReceiveInfo.setUpdateTime(DateUtils.getNowDate());
        return tbReceiveInfoMapper.updateTbReceiveInfo(tbReceiveInfo);
    }

    /**
     * 批量删除receiveInfo
     * 
     * @param ids 需要删除的receiveInfo主键
     * @return 结果
     */
    @Override
    public int deleteTbReceiveInfoByIds(String ids)
    {
        return tbReceiveInfoMapper.deleteTbReceiveInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除receiveInfo信息
     * 
     * @param id receiveInfo主键
     * @return 结果
     */
    @Override
    public int deleteTbReceiveInfoById(String id)
    {
        return tbReceiveInfoMapper.deleteTbReceiveInfoById(id);
    }

    @Override
    public int importData(List<TbReceiveInfo> tbReceiveInfos) throws Exception {
        int rows = 1;
        Date date = new Date();
        List<TbReceiveInfo> list = new ArrayList<TbReceiveInfo>();
        for (TbReceiveInfo receiveInfo : tbReceiveInfos) {
            TbBrand tbBrand = new TbBrand();
            tbBrand.setName(receiveInfo.getBrandName());
            TbBrand brand = brandService.selectTbBrandByName(tbBrand);
            if (brand==null){
                throw new Exception("物品：'" + receiveInfo.getBrandName() + "'在系统中未找到，先添加物品：'"+receiveInfo.getBrandName()+"'再导入");
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
//            takeInfo.setCreateBy(ShiroUtils.getSysUser().getUserName());
            receiveInfo.setCreateTime(date);
            list.add(receiveInfo);
        }
        if (list.size() > 0) {
            rows = tbReceiveInfoMapper.insertBatch(list);
        }
        return rows;
    }
}
