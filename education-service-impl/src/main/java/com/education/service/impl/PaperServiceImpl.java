package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.education.bean.Result;
import com.education.bean.ResultCode;
import com.education.config.mybatis.MyBatisPlusConfig;
import com.education.domain.Paper;
import com.education.domain.Relevance;
import com.education.mapper.PaperMapper;
import com.education.mapper.RelevanceMapper;
import com.education.service.IPaperService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2020-11-20 10:16:41
 * @description
 */
@Slf4j
@Service("PaperServiceImpl")
@Primary
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements IPaperService {
    @Value("${spring.datasource.name}")
    private String schema;

    @Autowired
    HttpServletRequest request;

    @Autowired
    RelevanceMapper relevanceMapper;

    @Autowired
    PaperMapper paperMapper;

    @Override
    public Result<List<Paper>> getList(String paper) {
        setPaper(paper);
        return new Result(baseMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Result add(String paper, List<Paper> papers) {
        List<String> list;
        if (StringUtils.isEmpty(paper)) {
            if (papers == null || papers.size() == 0 || StringUtils.isEmpty(papers.get(0).getPaperName())) {
                return new Result(ResultCode.ERROR, "保存失败");
            }

            log.info("schema:" + schema);
            list = paperMapper.findTables(schema, "paper");
            log.info("已有数据表:" + list.toString());
            String uid = java.util.UUID.randomUUID().toString().replaceAll("-", "");
            paper = "paper" + uid;
            baseMapper.createTable(uid);
            log.info("建表" + paper + "成功");
            Relevance relevance = new Relevance();
            relevance.setName(papers.get(0).getPaperName());
            relevance.setPaper(paper);
            relevanceMapper.insert(relevance);
        }
        setPaper(paper);
        for (Paper paper1 : papers) {
            baseMapper.deleteById(paper1.getId());
            baseMapper.insert(paper1);
        }
        return new Result("新增成功");
    }

    @Override
    public Result move(String paper, List<Paper> papers) {
        setPaper(paper);
        for (Paper paper1 : papers) {
            baseMapper.deleteById(paper1.getId());
        }
        return new Result("删除成功");
    }

    @Override
    public Result del(String paper) {
        log.info("删除表：" + paper);
        paperMapper.delTable(paper);
        QueryWrapper wrapper = new QueryWrapper<Relevance>();
        Relevance relevance = new Relevance();
        relevance.setPaper(paper);
        wrapper.setEntity(relevance);
        relevanceMapper.delete(wrapper);
        return new Result("删除成功");
    }

    private void setPaper(String paper) {
        if (paper != null) {
            MyBatisPlusConfig.tableNameLocal.set(paper);
        } else {
            MyBatisPlusConfig.tableNameLocal.set((String) request.getSession().getAttribute("paper"));
        }
    }
}
