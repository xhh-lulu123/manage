package com.ruoyi.project.category.controller;

import java.util.List;

import com.ruoyi.project.brand.domain.TbBrand;
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
import com.ruoyi.project.category.domain.TbCategory;
import com.ruoyi.project.category.service.ITbCategoryService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * categoryController
 * 
 * @author ruoyi
 * @date 2021-11-21
 */
@Controller
@RequestMapping("/project/category")
public class TbCategoryController extends BaseController
{
    private String prefix = "project/category";

    @Autowired
    private ITbCategoryService tbCategoryService;

    @RequiresPermissions("project:category:view")
    @GetMapping()
    public String category()
    {
        return prefix + "/category";
    }

    /**
     * 查询category列表
     */
    @RequiresPermissions("project:category:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbCategory tbCategory)
    {
        startPage();
        List<TbCategory> list = tbCategoryService.selectTbCategoryList(tbCategory);
        return getDataTable(list);
    }

    /**
     * 查询category列表
     */
    @RequiresPermissions("project:category:list")
    @PostMapping("/allList")
    @ResponseBody
    public AjaxResult list()
    {
        List<TbCategory> list = tbCategoryService.selectTbCategoryList(null);
        return AjaxResult.success(list);
    }

    /**
     * 导出category列表
     */
    @RequiresPermissions("project:category:export")
    @Log(title = "category", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbCategory tbCategory)
    {
        List<TbCategory> list = tbCategoryService.selectTbCategoryList(tbCategory);
        ExcelUtil<TbCategory> util = new ExcelUtil<TbCategory>(TbCategory.class);
        return util.exportExcel(list, "category数据");
    }

    /**
     * 新增category
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存category
     */
    @RequiresPermissions("project:category:add")
    @Log(title = "category", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbCategory tbCategory) throws Exception {
        return toAjax(tbCategoryService.insertTbCategory(tbCategory));
    }

    /**
     * 修改category
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        TbCategory tbCategory = tbCategoryService.selectTbCategoryById(id);
        mmap.put("tbCategory", tbCategory);
        return prefix + "/edit";
    }

    /**
     * 修改保存category
     */
    @RequiresPermissions("project:category:edit")
    @Log(title = "category", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbCategory tbCategory) throws Exception {
        return toAjax(tbCategoryService.updateTbCategory(tbCategory));
    }

    /**
     * 删除category
     */
    @RequiresPermissions("project:category:remove")
    @Log(title = "category", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids) throws Exception {
        return toAjax(tbCategoryService.deleteTbCategoryByIds(ids));
    }

}
