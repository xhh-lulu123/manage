package com.ruoyi.project.shelves.controller;

import java.util.List;

import com.ruoyi.project.system.dept.domain.Dept;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.shelves.domain.TbShelves;
import com.ruoyi.project.shelves.service.ITbShelvesService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.Ztree;

/**
 * shelvesController
 * 
 * @author ruoyi
 * @date 2021-11-20
 */
@Controller
@RequestMapping("/project/shelves")
public class TbShelvesController extends BaseController
{
    private String prefix = "project/shelves";

    @Autowired
    private ITbShelvesService tbShelvesService;

    @RequiresPermissions("project:shelves:view")
    @GetMapping()
    public String shelves()
    {
        return prefix + "/shelves";
    }


    /**
     * 查询shelves树列表
     */
    @RequiresPermissions("project:shelves:list")
    @PostMapping("/list")
    @ResponseBody
    public List<TbShelves> list(TbShelves tbShelves)
    {
        List<TbShelves> list = tbShelvesService.selectTbShelvesList(tbShelves);
        return list;
    }

    /**
     * 导出shelves列表
     */
    @RequiresPermissions("project:shelves:export")
    @Log(title = "shelves", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbShelves tbShelves)
    {
        List<TbShelves> list = tbShelvesService.selectTbShelvesList(tbShelves);
        ExcelUtil<TbShelves> util = new ExcelUtil<TbShelves>(TbShelves.class);
        return util.exportExcel(list, "shelves数据");
    }

    /**
     * 新增shelves
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("tbShelves", tbShelvesService.selectTbShelvesById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存shelves
     */
    @RequiresPermissions("project:shelves:add")
    @Log(title = "shelves", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbShelves tbShelves)
    {
        return toAjax(tbShelvesService.insertTbShelves(tbShelves));
    }

    /**
     * 修改shelves
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbShelves tbShelves = tbShelvesService.selectTbShelvesById(id);
        mmap.put("tbShelves", tbShelves);
        return prefix + "/edit";
    }

    /**
     * 修改保存shelves
     */
    @RequiresPermissions("project:shelves:edit")
    @Log(title = "shelves", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbShelves tbShelves)
    {
        return toAjax(tbShelvesService.updateTbShelves(tbShelves));
    }

    /**
     * 删除
     */
    @RequiresPermissions("project:shelves:remove")
    @Log(title = "shelves", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        return toAjax(tbShelvesService.deleteTbShelvesById(id));
    }

    /**
     * 选择shelves树
     */
    @GetMapping(value = { "/selectShelvesTree/{id}", "/selectShelvesTree/" })
    public String selectShelvesTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("tbShelves", tbShelvesService.selectTbShelvesById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载shelves树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = tbShelvesService.selectTbShelvesTree();
        return ztrees;
    }
}
