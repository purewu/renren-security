package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.entity.SysRoleEntity;
import io.renren.modules.sys.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 字典控制类
 * @author wudepeng
 * @since 2018/12/19
 */
@Api(value = "字典控制类")
@RestController
@RequestMapping("/sys/dict")
public class SysDictController {


    @Autowired
    private SysDictService dictService;


    @ApiOperation(value = "列表查询")
    @RequestMapping("/list")
    @RequiresPermissions("sys:dict:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = dictService.queryPage(params);
        return R.ok().put("page", page);
    }

    @ApiOperation(value = "新增")
    @RequestMapping("/save")
    @RequiresPermissions("sys:dict:save")
    public R save(@RequestBody SysDictEntity entity){
        ValidatorUtils.validateEntity(entity);
        dictService.insert(entity);
        return R.ok();
    }

    @ApiOperation(value = "字典信息")
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:dict:info")
    public R info(@PathVariable("id") Long id){
        SysDictEntity dict = dictService.selectById(id);

        return R.ok().put("dict", dict);
    }

    @ApiOperation(value = "修改")
    @RequestMapping("/update")
    @RequiresPermissions("sys:dict:update")
    public R update(@RequestBody SysDictEntity entity){
        ValidatorUtils.validateEntity(entity);
        dictService.updateAllColumnById(entity);

        return R.ok();
    }

    @ApiOperation(value = "删除")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dict:delete")
    public R deleteByDictId(@RequestBody Long[] ids){
        dictService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
