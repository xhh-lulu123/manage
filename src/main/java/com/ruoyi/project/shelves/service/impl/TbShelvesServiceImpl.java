package com.ruoyi.project.shelves.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.dept.domain.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.shelves.mapper.TbShelvesMapper;
import com.ruoyi.project.shelves.domain.TbShelves;
import com.ruoyi.project.shelves.service.ITbShelvesService;
import com.ruoyi.common.utils.text.Convert;

/**
 * shelvesService业务层处理
 * 
 * @author ruoyi
 * @date 2021-11-20
 */
@Service
public class TbShelvesServiceImpl implements ITbShelvesService 
{
    @Autowired
    private TbShelvesMapper tbShelvesMapper;

    /**
     * 查询shelves
     * 
     * @param id shelves主键
     * @return shelves
     */
    @Override
    public TbShelves selectTbShelvesById(Long id)
    {
        return tbShelvesMapper.selectTbShelvesById(id);
    }

    /**
     * 查询shelves列表
     * 
     * @param tbShelves shelves
     * @return shelves
     */
    @Override
    public List<TbShelves> selectTbShelvesList(TbShelves tbShelves)
    {
        return tbShelvesMapper.selectTbShelvesList(tbShelves);
    }

    /**
     * 新增shelves
     * 
     * @param tbShelves shelves
     * @return 结果
     */
    @Override
    public int insertTbShelves(TbShelves tbShelves)
    {
        TbShelves shelves = tbShelvesMapper.selectTbShelvesById(tbShelves.getParentId());
        // 如果父节点不为"正常"状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(shelves.getStatus()))
        {
            throw new ServiceException("位置停用，不允许新增");
        }
        tbShelves.setCreateBy(ShiroUtils.getLoginName());
        tbShelves.setAncestors(shelves.getAncestors() + "," + tbShelves.getParentId());
        tbShelves.setCreateTime(DateUtils.getNowDate());
        return tbShelvesMapper.insertTbShelves(tbShelves);
    }

    /**
     * 修改shelves
     * 
     * @param tbShelves shelves
     * @return 结果
     */
    @Override
    public int updateTbShelves(TbShelves tbShelves)
    {
        tbShelves.setUpdateTime(DateUtils.getNowDate());
        return tbShelvesMapper.updateTbShelves(tbShelves);
    }

    /**
     * 批量删除shelves
     * 
     * @param ids 需要删除的shelves主键
     * @return 结果
     */
    @Override
    public int deleteTbShelvesByIds(String ids)
    {
        return tbShelvesMapper.deleteTbShelvesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除shelves信息
     * 
     * @param id shelves主键
     * @return 结果
     */
    @Override
    public int deleteTbShelvesById(Long id)
    {
        return tbShelvesMapper.deleteTbShelvesById(id);
    }

    /**
     * 查询shelves树列表
     * 
     * @return 所有shelves信息
     */
    @Override
    public List<Ztree> selectTbShelvesTree()
    {
        List<TbShelves> tbShelvesList = tbShelvesMapper.selectTbShelvesList(new TbShelves());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (TbShelves tbShelves : tbShelvesList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(tbShelves.getId());
            ztree.setpId(tbShelves.getParentId());
            ztree.setName(tbShelves.getName());
            ztree.setTitle(tbShelves.getName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
