package com.ruoyi.project.receiveInfo.service;

import java.util.List;
import com.ruoyi.project.receiveInfo.domain.TbReceiveInfo;

/**
 * receiveInfoService接口
 * 
 * @author ruoyi
 * @date 2021-11-23
 */
public interface ITbReceiveInfoService 
{
    /**
     * 查询receiveInfo
     * 
     * @param id receiveInfo主键
     * @return receiveInfo
     */
    public TbReceiveInfo selectTbReceiveInfoById(String id);

    /**
     * 查询receiveInfo列表
     * 
     * @param tbReceiveInfo receiveInfo
     * @return receiveInfo集合
     */
    public List<TbReceiveInfo> selectTbReceiveInfoList(TbReceiveInfo tbReceiveInfo);

    /**
     * 新增receiveInfo
     * 
     * @param tbReceiveInfo receiveInfo
     * @return 结果
     */
    public int insertTbReceiveInfo(TbReceiveInfo tbReceiveInfo);

    /**
     * 修改receiveInfo
     * 
     * @param tbReceiveInfo receiveInfo
     * @return 结果
     */
    public int updateTbReceiveInfo(TbReceiveInfo tbReceiveInfo);

    /**
     * 批量删除receiveInfo
     * 
     * @param ids 需要删除的receiveInfo主键集合
     * @return 结果
     */
    public int deleteTbReceiveInfoByIds(String ids);

    /**
     * 删除receiveInfo信息
     * 
     * @param id receiveInfo主键
     * @return 结果
     */
    public int deleteTbReceiveInfoById(String id);
}
