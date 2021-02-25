package com.education.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import com.education.domain.Relevance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author 
 * @date 2020-11-20 10:16:41
 * @version 1.0
 * @description 
 */
@Mapper
@Repository
public interface RelevanceMapper extends BaseMapper<Relevance> {
    /**
     * 建表
     * @return
     */
    @Update("CREATE TABLE `relevance`  (\n" +
            "  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,\n" +
            "  `paper` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,\n" +
            "  INDEX `user_name_index`(`name`) USING BTREE\n" +
            ") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;")
    void createTable();

    /**
     * 删表
     * @return
     */
    @Update("DROP TABLE IF EXISTS `relevance`;")
    void delTable();

    /**
     * 查表
     * @return
     */
    @Select("show TABLES like `user`")
    String showTable();
}
