package cn.zephyr.framework;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MapperRegistery
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-07-22 10:54
 */
@Data
public class MapperRegistery {

    /**
     * sql 与 Mapper接口类方法的映射map
     */
    private static final Map<String,MapperDate> methodSqlMap = new HashMap<String, MapperDate>();

    public MapperRegistery() {
        // 模拟解析到的Maper.xml的内容，key是方法的id，value是sql内容及其返回值
        methodSqlMap.put("cn.zephyr.module.mapper.SysUserMapper.selectByPrimaryKey",
                new MapperDate("select * from sys_user where id = ?",SysUser.class));
    }

    /**
     * 配置sql语句与返回值（模拟mapper.xml）
     * @param <T>
     */
    @Data
    public class MapperDate<T>{
        // sql
        private String sql;
        // 返回值类型
        private Class<T> type;

        public MapperDate(String sql, Class<T> type) {
            this.sql = sql;
            this.type = type;
        }
    }

    public MapperDate get(String sqlId){
        return methodSqlMap.get(sqlId);
    }
}
