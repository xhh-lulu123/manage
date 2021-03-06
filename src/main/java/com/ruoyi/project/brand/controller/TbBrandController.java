package com.ruoyi.project.brand.controller;

import java.util.List;

import com.ruoyi.project.category.domain.TbCategory;
import com.ruoyi.project.category.service.ITbCategoryService;
import com.ruoyi.project.receiveInfo.domain.TbReceiveInfo;
import com.ruoyi.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.brand.domain.TbBrand;
import com.ruoyi.project.brand.service.ITbBrandService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * brandController
 * 
 * @author ruoyi
 * @date 2021-11-21
 */
@Controller
@RequestMapping("/project/brand")
public class TbBrandController extends BaseController
{
    private String prefix = "project/brand";

    @Autowired
    private ITbCategoryService tbCategoryService;
    @Autowired
    private ITbBrandService tbBrandService;

    @RequiresPermissions("project:brand:view")
    @GetMapping()
    public String brand()
    {
        return prefix + "/brand";
    }

    /**
     *
     */
    @RequiresPermissions("project:brand:list")
    @PostMapping("/categoryList")
    @ResponseBody
    public AjaxResult list()
    {
        List<TbCategory> list = tbCategoryService.selectTbCategoryList(null);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 查询brand列表
     */
    @RequiresPermissions("project:brand:list")
    @PostMapping("/listByCategoryId")
    @ResponseBody
    public AjaxResult listByCategoryId(TbBrand brand)
    {
        startPage();
        List<TbBrand> list = tbBrandService.selectTbBrandByCategoryId(brand.getCategoryId());
        return AjaxResult.success(list);
    }

    /**
     * 查询brand列表
     */
    @RequiresPermissions("project:brand:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbBrand tbBrand)
    {
        startPage();
        List<TbBrand> list = tbBrandService.selectTbBrandList(tbBrand);
        return getDataTable(list);
    }

    /**
     * 导出brand列表
     */
    @RequiresPermissions("project:brand:export")
    @Log(title = "brand", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbBrand tbBrand)
    {
        List<TbBrand> list = tbBrandService.selectTbBrandList(tbBrand);
        ExcelUtil<TbBrand> util = new ExcelUtil<TbBrand>(TbBrand.class);
        return util.exportExcel(list, "brand数据");
    }

    /**
     * 新增brand
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存brand
     */
    @RequiresPermissions("project:brand:add")
    @Log(title = "brand", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbBrand tbBrand) throws Exception {
        return toAjax(tbBrandService.insertTbBrand(tbBrand));
    }

    /**
     * 修改brand
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        TbBrand tbBrand = tbBrandService.selectTbBrandById(id);
        mmap.put("tbBrand", tbBrand);
        return prefix + "/edit";
    }

    /**
     * 修改保存brand
     */
    @RequiresPermissions("project:brand:edit")
    @Log(title = "brand", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbBrand tbBrand) throws Exception {
        return toAjax(tbBrandService.updateTbBrand(tbBrand));
    }

    /**
     * 删除brand
     */
    @RequiresPermissions("project:brand:remove")
    @Log(title = "brand", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids) throws Exception {
        return toAjax(tbBrandService.deleteTbBrandByIds(ids));
    }

    @Log(title = "brand", businessType = BusinessType.IMPORT)
    @PostMapping( "/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<TbBrand> excelUtil = new ExcelUtil<>(TbBrand.class);
        List<TbBrand> list = excelUtil.importExcel(file.getInputStream());
        return toAjax(tbBrandService.importData(list));
    }

    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<TbBrand> util = new ExcelUtil<TbBrand>(TbBrand.class);
        return util.importTemplateExcel("物品数据");
    }
}
