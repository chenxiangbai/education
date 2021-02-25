package com.education.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.education.domain.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 
 * @date 2020-11-20 10:16:41
 * @version 1.0
 * @description 
 */
@Mapper
@Repository
public interface PaperMapper extends BaseMapper<Paper> {

    /**
     * 建表
     * @param tableName
     * @return
     */
    @Update("CREATE TABLE paper${tableName}  (\n" +
            "  `id` int(0) NOT NULL AUTO_INCREMENT,\n" +
            "  `title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,\n" +
            "  `options` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,\n" +
            "  `space` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,\n" +
            "  `correct` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,\n" +
            "  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,\n" +
            "  PRIMARY KEY (`id`) USING BTREE\n" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;")
    void createTable(@Param("tableName") String tableName);

    /**
     * 删表
     * @param tableName
     */
    @Update("DROP TABLE IF EXISTS paper${tableName};")
    void delTable(@Param("tableName") String tableName);

    /**
     * 查表
     * @param tableName
     * @return
     */
    @Select("show TABLES like '#{tableName}'")
    String showTable(@Param("tableName") String tableName);

    /**
     * 查询数据库中类似的所有表
     * @return
     */
    @Select("select table_name from information_schema.tables where table_schema = '${TableSchema}' and table_name LIKE '${tableName}%'")
    List<String> findTables(@Param("TableSchema") String TableSchema,@Param("tableName") String tableName);

    /**
     * 查询数据库中所有表
     * @return
     */
    @Select("select table_name from information_schema.tables where table_schema = '${TableSchema}'")
    List<String> findTable(@Param("TableSchema") String TableSchema);
}
