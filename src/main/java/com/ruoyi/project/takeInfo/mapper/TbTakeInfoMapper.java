package com.ruoyi.project.takeInfo.mapper;

import java.util.List;
import com.ruoyi.project.takeInfo.domain.TbTakeInfo;
import org.apache.ibatis.annotations.Param;

/**
 * takeInfoMapper接口
 * 
 * @author xiaohuanghua
 * @date 2021-11-21
 */
public interface TbTakeInfoMapper 
{
    /**
     * 查询takeInfo
     * 
     * @param id takeInfo主键
     * @return takeInfo
     */
    public TbTakeInfo selectTbTakeInfoById(String id);

    /**
     * 查询takeInfo列表
     * 
     * @param tbTakeInfo takeInfo
     * @return takeInfo集合
     */
    public List<TbTakeInfo> selectTbTakeInfoList(TbTakeInfo tbTakeInfo);

    /**
     * 新增takeInfo
     * 
     * @param tbTakeInfo takeInfo
     * @return 结果
     */
    public int insertTbTakeInfo(TbTakeInfo tbTakeInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbTakeInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TbTakeInfo> entities);

    /**
     * 修改takeInfo
     * 
     * @param tbTakeInfo takeInfo
     * @return 结果
     */
    public int updateTbTakeInfo(TbTakeInfo tbTakeInfo);

    /**
     * 删除takeInfo
     * 
     * @param id takeInfo主键
     * @return 结果
     */
    public int deleteTbTakeInfoById(String id);

    /**
     * 批量删除takeInfo
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbTakeInfoByIds(String[] ids);
}
