package com.ruoyi.project.shelves.service;

import java.util.List;
import com.ruoyi.project.shelves.domain.TbShelves;
import com.ruoyi.framework.web.domain.Ztree;

/**
 * shelvesService接口
 * 
 * @author ruoyi
 * @date 2021-11-20
 */
public interface ITbShelvesService 
{
    /**
     * 查询shelves
     * 
     * @param id shelves主键
     * @return shelves
     */
    public TbShelves selectTbShelvesById(Long id);

    /**
     * 查询shelves列表
     * 
     * @param tbShelves shelves
     * @return shelves集合
     */
    public List<TbShelves> selectTbShelvesList(TbShelves tbShelves);

    /**
     * 新增shelves
     * 
     * @param tbShelves shelves
     * @return 结果
     */
    public int insertTbShelves(TbShelves tbShelves);

    /**
     * 修改shelves
     * 
     * @param tbShelves shelves
     * @return 结果
     */
    public int updateTbShelves(TbShelves tbShelves);

    /**
     * 批量删除shelves
     * 
     * @param ids 需要删除的shelves主键集合
     * @return 结果
     */
    public int deleteTbShelvesByIds(String ids);

    /**
     * 删除shelves信息
     * 
     * @param id shelves主键
     * @return 结果
     */
    public int deleteTbShelvesById(Long id);

    /**
     * 查询shelves树列表
     * 
     * @return 所有shelves信息
     */
    public List<Ztree> selectTbShelvesTree();
}
