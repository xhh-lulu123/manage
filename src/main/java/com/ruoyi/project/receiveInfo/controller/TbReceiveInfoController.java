package com.ruoyi.project.receiveInfo.controller;

import java.util.List;
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
import com.ruoyi.project.receiveInfo.domain.TbReceiveInfo;
import com.ruoyi.project.receiveInfo.service.ITbReceiveInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * receiveInfoController
 * 
 * @author ruoyi
 * @date 2021-11-23
 */
@Controller
@RequestMapping("/project/receiveInfo")
public class TbReceiveInfoController extends BaseController
{
    private String prefix = "project/receiveInfo";

    @Autowired
    private ITbReceiveInfoService tbReceiveInfoService;

    @RequiresPermissions("project:receiveInfo:view")
    @GetMapping()
    public String receiveInfo()
    {
        return prefix + "/receiveInfo";
    }

    /**
     * 查询receiveInfo列表
     */
    @RequiresPermissions("project:receiveInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbReceiveInfo tbReceiveInfo)
    {
        startPage();
        List<TbReceiveInfo> list = tbReceiveInfoService.selectTbReceiveInfoList(tbReceiveInfo);
        return getDataTable(list);
    }

    /**
     * 导出receiveInfo列表
     */
    @RequiresPermissions("project:receiveInfo:export")
    @Log(title = "receiveInfo", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbReceiveInfo tbReceiveInfo)
    {
        List<TbReceiveInfo> list = tbReceiveInfoService.selectTbReceiveInfoList(tbReceiveInfo);
        ExcelUtil<TbReceiveInfo> util = new ExcelUtil<TbReceiveInfo>(TbReceiveInfo.class);
        return util.exportExcel(list, "receiveInfo数据");
    }

    /**
     * 新增receiveInfo
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存receiveInfo
     */
    @RequiresPermissions("project:receiveInfo:add")
    @Log(title = "receiveInfo", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbReceiveInfo tbReceiveInfo)
    {
        return toAjax(tbReceiveInfoService.insertTbReceiveInfo(tbReceiveInfo));
    }

    /**
     * 修改receiveInfo
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        TbReceiveInfo tbReceiveInfo = tbReceiveInfoService.selectTbReceiveInfoById(id);
        mmap.put("tbReceiveInfo", tbReceiveInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存receiveInfo
     */
    @RequiresPermissions("project:receiveInfo:edit")
    @Log(title = "receiveInfo", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbReceiveInfo tbReceiveInfo)
    {
        return toAjax(tbReceiveInfoService.updateTbReceiveInfo(tbReceiveInfo));
    }

    /**
     * 删除receiveInfo
     */
    @RequiresPermissions("project:receiveInfo:remove")
    @Log(title = "receiveInfo", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbReceiveInfoService.deleteTbReceiveInfoByIds(ids));
    }
}
