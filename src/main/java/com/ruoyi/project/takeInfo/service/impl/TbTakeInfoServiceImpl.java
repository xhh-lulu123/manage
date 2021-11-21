package com.ruoyi.project.takeInfo.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.takeInfo.mapper.TbTakeInfoMapper;
import com.ruoyi.project.takeInfo.domain.TbTakeInfo;
import com.ruoyi.project.takeInfo.service.ITbTakeInfoService;
import com.ruoyi.common.utils.text.Convert;

/**
 * takeInfoService业务层处理
 * 
 * @author xiaohuanghua
 * @date 2021-11-21
 */
@Service
public class TbTakeInfoServiceImpl implements ITbTakeInfoService 
{
    @Autowired
    private TbTakeInfoMapper tbTakeInfoMapper;

    /**
     * 查询takeInfo
     * 
     * @param id takeInfo主键
     * @return takeInfo
     */
    @Override
    public TbTakeInfo selectTbTakeInfoById(String id)
    {
        return tbTakeInfoMapper.selectTbTakeInfoById(id);
    }

    /**
     * 查询takeInfo列表
     * 
     * @param tbTakeInfo takeInfo
     * @return takeInfo
     */
    @Override
    public List<TbTakeInfo> selectTbTakeInfoList(TbTakeInfo tbTakeInfo)
    {
        return tbTakeInfoMapper.selectTbTakeInfoList(tbTakeInfo);
    }

    /**
     * 新增takeInfo
     * 
     * @param tbTakeInfo takeInfo
     * @return 结果
     */
    @Override
    public int insertTbTakeInfo(TbTakeInfo tbTakeInfo)
    {
        tbTakeInfo.setId(UUID.randomUUID().toString());
        tbTakeInfo.setCreateBy(ShiroUtils.getSysUser().getUserName());
        tbTakeInfo.setCreateTime(DateUtils.getNowDate());
        return tbTakeInfoMapper.insertTbTakeInfo(tbTakeInfo);
    }

    /**
     * 修改takeInfo
     * 
     * @param tbTakeInfo takeInfo
     * @return 结果
     */
    @Override
    public int updateTbTakeInfo(TbTakeInfo tbTakeInfo)
    {
        tbTakeInfo.setCreateBy(ShiroUtils.getSysUser().getUserName());
        tbTakeInfo.setUpdateTime(DateUtils.getNowDate());
        return tbTakeInfoMapper.updateTbTakeInfo(tbTakeInfo);
    }

    /**
     * 批量删除takeInfo
     * 
     * @param ids 需要删除的takeInfo主键
     * @return 结果
     */
    @Override
    public int deleteTbTakeInfoByIds(String ids)
    {
        return tbTakeInfoMapper.deleteTbTakeInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除takeInfo信息
     * 
     * @param id takeInfo主键
     * @return 结果
     */
    @Override
    public int deleteTbTakeInfoById(String id)
    {
        return tbTakeInfoMapper.deleteTbTakeInfoById(id);
    }
}
