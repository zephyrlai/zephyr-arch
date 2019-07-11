package cn.zephyr;

import cn.zephyr.entity.SysUser;
import cn.zephyr.mapper.SysUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-07-10 21:24
 */
public class MybatisTest {

    @Test
    public void mybatisTest01() throws Exception {
        InputStream configFile = new FileInputStream("./src/main/resources/mybatis-conf.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configFile);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysUser> sysUsers = mapper.queryList();
        System.err.println(Arrays.asList(sysUsers));
     }
}
