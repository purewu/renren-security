package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 字典控制类
 *
 * @author wudepeng
 * @since 2018/12/19
 */
//@Api(value = "字典控制类")
@RestController
public class SysDictController {


    @Autowired
    private SysDictService dictService;


//    @ApiOperation(value = "列表查询")

    @RequestMapping("/list")
    @RequiresPermissions("sys:user:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = dictService.queryPage(params);
        return R.ok().put("page", page);
    }
}
