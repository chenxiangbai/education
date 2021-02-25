package com.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.bean.Result;
import com.education.domain.Relevance;
import com.education.mapper.RelevanceMapper;
import com.education.service.IRelevanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 
 * @date 2020-11-20 10:16:41
 * @version 1.0
 * @description 关系
 */
@Service("RelevanceServiceImpl")
@Primary
public class RelevanceServiceImpl extends ServiceImpl<RelevanceMapper, Relevance> implements IRelevanceService {
    @Autowired
    HttpServletRequest request;

    @Override
    public Result paperList() {
        QueryWrapper<Relevance> wrapper = new QueryWrapper<>();
        Relevance relevance = new Relevance();
        //relevance.setName(String.valueOf(request.getSession().getAttribute("user")));
        wrapper.setEntity(relevance);
        return new Result(baseMapper.selectList(wrapper));
    }

    @Override
    public Result<Boolean> add(List<Relevance> relevance) {
        for(Relevance relevance1:relevance){
            relevance1.setName(String.valueOf(request.getSession().getAttribute("user")));
            baseMapper.insert(relevance1);
        }
        return new Result("新增成功");
    }

    @Override
    public Result<Boolean> move(List<Relevance> relevance) {
        QueryWrapper<Relevance> wrapper = new QueryWrapper<>();
        for(Relevance relevance1:relevance){
            relevance1.setName(String.valueOf(request.getSession().getAttribute("user")));
            wrapper.setEntity(relevance1);
            baseMapper.delete(wrapper);
        }
        return new Result("删除成功");
    }
}
