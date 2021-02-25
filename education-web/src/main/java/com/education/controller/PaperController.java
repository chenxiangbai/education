package com.education.controller;


import com.education.annoation.AspectLog;
import com.education.bean.Result;
import com.education.domain.Paper;
import com.education.service.IPaperService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2020-11-20 10:16:41
 * @description 试卷题目相关
 */
@RestController
@RequestMapping("/paper")
@ResponseBody
public class PaperController {
    @Autowired(required = false)
    IPaperService iPaperService;

    @ApiOperation("获取指定试卷中的题目")
    @AspectLog("获取指定试卷中的题目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paper", value = "试卷名英文名", defaultValue = "默认值是paper", required = false, dataType = "string", paramType = "path")
    })
    @GetMapping(path = {"/", "/{paper}"})
    public Result get(@PathVariable(value = "paper", required = false) String paper) {
        return iPaperService.getList(paper);
    }

    @ApiOperation("新建试卷")
    @AspectLog("新建试卷")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paper", value = "试卷名", defaultValue = "默认值是paper", required = false, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "list", value = "试卷题目列表", required = true)
    })
    @PostMapping(path = {"/", "/{paper}"})
    public Result add(@PathVariable(value = "paper", required = false) String paper, @RequestBody List<Paper> list) {
        return iPaperService.add(paper, list);
    }

    @ApiOperation("删除试卷题目")
    @AspectLog("删除试卷题目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paper", value = "试卷名", defaultValue = "默认值是paper", required = false, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "list", value = "试卷题目列表", required = true)
    })
    @PutMapping(path = {"/", "/{paper}"})
    public Result move(@PathVariable(value = "paper", required = false) String paper, @RequestBody List<Paper> list) {
        return iPaperService.move(paper, list);
    }

    @ApiOperation("删除试卷")
    @AspectLog("删除试卷")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paper", value = "试卷名", defaultValue = "默认值是paper", required = false, dataType = "string", paramType = "path")
    })
    @DeleteMapping(path = {"/", "/{paper}"})
    public Result del(@PathVariable(value = "paper", required = false) String paper) {
        return iPaperService.del(paper);
    }
}
