package com.ruoyi.project.receiveInfo.mapper;

import java.util.List;
import com.ruoyi.project.receiveInfo.domain.TbReceiveInfo;
import com.ruoyi.project.takeInfo.domain.TbTakeInfo;
import org.apache.ibatis.annotations.Param;

/**
 * receiveInfoMapper接口
 * 
 * @author ruoyi
 * @date 2021-11-23
 */
public interface TbReceiveInfoMapper 
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

    Long selectNumByBrandId(@Param("brandId") String brandId);
    int insertBatch(@Param("entities") List<TbReceiveInfo> entities);
    /**
     * 修改receiveInfo
     * 
     * @param tbReceiveInfo receiveInfo
     * @return 结果
     */
    public int updateTbReceiveInfo(TbReceiveInfo tbReceiveInfo);

    /**
     * 删除receiveInfo
     * 
     * @param id receiveInfo主键
     * @return 结果
     */
    public int deleteTbReceiveInfoById(String id);

    /**
     * 批量删除receiveInfo
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbReceiveInfoByIds(String[] ids);
}
