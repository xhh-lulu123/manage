package com.ruoyi.project.takeInfo.controller;

import java.io.IOException;
import java.util.List;

import com.ruoyi.project.receiveInfo.domain.TbReceiveInfo;
import com.ruoyi.project.system.user.domain.User;
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
import com.ruoyi.project.takeInfo.domain.TbTakeInfo;
import com.ruoyi.project.takeInfo.service.ITbTakeInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * takeInfoController
 * 
 * @author xiaohuanghua
 * @date 2021-11-21
 */
@Controller
@RequestMapping("/project/takeInfo")
public class TbTakeInfoController extends BaseController
{
    private String prefix = "project/takeInfo";

    @Autowired
    private ITbTakeInfoService tbTakeInfoService;

    @RequiresPermissions("project:takeInfo:view")
    @GetMapping()
    public String takeInfo()
    {
        return prefix + "/takeInfo";
    }

    /**
     * 查询takeInfo列表
     */
    @RequiresPermissions("project:takeInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbTakeInfo tbTakeInfo)
    {
        startPage();
        List<TbTakeInfo> list = tbTakeInfoService.selectTbTakeInfoList(tbTakeInfo);
        return getDataTable(list);
    }

    /**
     * 导出takeInfo列表
     */
    @RequiresPermissions("project:takeInfo:export")
    @Log(title = "takeInfo", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbTakeInfo tbTakeInfo)
    {
        List<TbTakeInfo> list = tbTakeInfoService.selectTbTakeInfoList(tbTakeInfo);
        ExcelUtil<TbTakeInfo> util = new ExcelUtil<TbTakeInfo>(TbTakeInfo.class);
        return util.exportExcel(list, "takeInfo数据");
    }

    /**
     * 新增takeInfo
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存takeInfo
     */
    @RequiresPermissions("project:takeInfo:add")
    @Log(title = "takeInfo", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbTakeInfo tbTakeInfo)
    {
        return toAjax(tbTakeInfoService.insertTbTakeInfo(tbTakeInfo));
    }

    /**
     * 修改takeInfo
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        TbTakeInfo tbTakeInfo = tbTakeInfoService.selectTbTakeInfoById(id);
        mmap.put("tbTakeInfo", tbTakeInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存takeInfo
     */
    @RequiresPermissions("project:takeInfo:edit")
    @Log(title = "takeInfo", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbTakeInfo tbTakeInfo)
    {
        return toAjax(tbTakeInfoService.updateTbTakeInfo(tbTakeInfo));
    }

    /**
     * 删除takeInfo
     */
    @RequiresPermissions("project:takeInfo:remove")
    @Log(title = "takeInfo", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbTakeInfoService.deleteTbTakeInfoByIds(ids));
    }

    @Log(title = "takeInfo", businessType = BusinessType.IMPORT)
    @PostMapping( "/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<TbTakeInfo> excelUtil = new ExcelUtil<>(TbTakeInfo.class);
        List<TbTakeInfo> list = excelUtil.importExcel(file.getInputStream());
        return toAjax(tbTakeInfoService.importData(list));
    }
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<TbTakeInfo> util = new ExcelUtil<TbTakeInfo>(TbTakeInfo.class);
        return util.importTemplateExcel("领走数据");
    }

}
