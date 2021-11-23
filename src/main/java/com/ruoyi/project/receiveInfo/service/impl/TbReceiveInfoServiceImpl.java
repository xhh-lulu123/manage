package com.ruoyi.project.receiveInfo.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.uuid.UUID;
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
}
