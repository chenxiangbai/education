package com.education.service;

import com.education.bean.Result;
import com.education.domain.Paper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2020-11-20 10:16:41
 * @description
 */
public interface IPaperService extends IService<Paper> {
    /**
     * 查询试卷
     *
     * @param paper
     * @return
     */
    Result<List<Paper>> getList(String paper);

    /**
     * 新增试卷
     *
     * @param paper  试卷名
     * @param papers
     * @return
     */
    Result add(String paper, List<Paper> papers);

    /**
     * 删除试卷中的题目
     *
     * @param paper  试卷名
     * @param papers
     * @return
     */
    Result move(String paper, List<Paper> papers);

    /**
     * 删除试卷
     * @param paper
     * @return
     */
    Result del(String paper);
}
