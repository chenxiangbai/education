package com.education.service;

import com.education.bean.Result;
import com.education.domain.Relevance;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 
 * @date 2020-11-20 10:16:41
 * @version 1.0
 * @description 关系表
 */
public interface IRelevanceService extends IService<Relevance> {
    /**
     * 列表
     * @return
     */
    Result<List<Integer>> paperList();

    /**
     * 新增
     * @param relevance
     * @return
     */
    Result<Boolean> add(List<Relevance> relevance);

    /**
     * 删除
     * @param relevance
     * @return
     */
    Result<Boolean> move(List<Relevance> relevance);
}
