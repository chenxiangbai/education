package com.education.controller;


import com.education.annoation.AspectLog;
import com.education.bean.Result;
import com.education.domain.Relevance;
import com.education.service.IRelevanceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 
 * @date 2020-11-20 10:16:41
 * @version 1.0
 * @description 试卷相关
 */
@RestController
@RequestMapping("/relevance")
@ResponseBody
public class RelevanceController {

    @Autowired(required = false)
    IRelevanceService iRelevanceService;

    @ApiOperation("获取试卷列表")
    @AspectLog("获取试卷列表")
    @GetMapping
    public Result get(){
        return iRelevanceService.paperList();
    }

    @ApiOperation("新增试卷")
    @AspectLog("新增试卷")
    @ApiImplicitParam(name = "relevanceList", value = "试卷列表", required = true)
    @PostMapping
    public Result add(@RequestBody List<Relevance> relevanceList){
        return iRelevanceService.add(relevanceList);
    }

    @ApiOperation("删除指定试卷")
    @AspectLog("删除指定试卷")
    @ApiImplicitParam(name = "relevanceList", value = "试卷列表", required = true)
    @DeleteMapping
    public Result move(@RequestBody List<Relevance> relevanceList){
        return iRelevanceService.move(relevanceList);
    }
}
