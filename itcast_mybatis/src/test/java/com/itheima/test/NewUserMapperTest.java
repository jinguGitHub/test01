package com.itheima.test;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mybatis.mapper.NewUserMapper;
import com.itheima.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.itheima.test
 * @date 2018/2/5
 */
public class NewUserMapperTest {
    private NewUserMapper newUserMapper;

    @Before
    public void init() throws Exception{
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 参数为true，设置事务是自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        this.newUserMapper = sqlSession.getMapper(NewUserMapper.class);
    }
    @Test
    public void testSelectOne(){
        User user = new User();
        user.setId(1l);
        user = this.newUserMapper.selectOne(user);
        System.out.println(user);
    }
    @Test
    public void testSelect(){
        User user = new User();
        user.setSex(1);
        // 查询所有数据   参数为null  查询某个条件就带上对应的参数即可
        List<User> list = this.newUserMapper.select(null);
        System.out.println(list);
    }
    @Test
    public void testSelectCount(){
        User user = new User();
        user.setSex(1);
        // 查询所有数据   参数为null  查询某个条件就带上对应的参数即可
        int i = this.newUserMapper.selectCount(null);
        System.out.println(i+"3333333333333333333");
    }
    @Test
    public void testInsert() {
        User user = new User();
        user.setId(null);
        user.setUserName("caocao1");
        user.setName("曹操");
        user.setSex(11);

        this.newUserMapper.insert(user);

        System.out.println(user);
    }
    @Test
    public void testSelsctByPrimaryKey(){
        User user = this.newUserMapper.selectByPrimaryKey(1l);
        System.out.println(user);
    }
    @Test
    public void testInsertSelective() {
        User user = new User();
        user.setId(null);
        user.setUserName("caocao");
        user.setName("曹操");
        user.setSex(1);

        this.newUserMapper.insertSelective(user);

        System.out.println(user);
    }
    @Test
    public void testDelete() {
        User param = new User();
        param.setUserName("caocao1");

        this.newUserMapper.delete(param);
    }

    @Test
    public void testDeleteByPrimaryKey() {
        fail("Not yet implemented");
    }
    @Test
    public void testUpdateByPrimaryKey() {
        User user = new User();
        user.setId(11l);
        user.setUserName("caocao2");

        this.newUserMapper.updateByPrimaryKey(user);
    }
    @Test
    public void testUpdateByPrimaryKeySelective() {
        User user = new User();
        user.setId(11l);
        user.setUserName("caocao1");

        this.newUserMapper.updateByPrimaryKeySelective(user);
    }


    // ---上面方法参数一般是user这个javabean，只能做单条件查询------
    // 以下使用example条件查询
    @Test
    public void testSelectCountByExample() {
        // 创建example,构造器为JavaBean的class
        Example example = new Example(User.class);

        // 设置条件
        // 获取设置条件的  对象
        Example.Criteria criteria = example.createCriteria();
        // 设置
        List<Object> ids = new ArrayList<Object>();
        ids.add(1l);
        ids.add(2l);
        ids.add(3l);

        criteria.andIn("id", ids);

        int count = this.newUserMapper.selectCountByExample(example);
        System.out.println(count);
    }
    @Test
    public void testDeleteByExample() {
        fail("Not yet implemented");
    }
    @Test
    public void testSelectByExample() {
        // 创建example,构造器为JavaBean的class
        Example example = new Example(User.class);

        // 设置条件
        // 获取设置条件的对象
        Example.Criteria criteria = example.createCriteria();
        // 设置
        List<Object> ids = new ArrayList<>();
        ids.add(1l);
        ids.add(2l);

        criteria.andIn("id", ids);

        List<User> list = this.newUserMapper.selectByExample(example);

        for (User user : list) {
            System.out.println(user);
        }
    }
    @Test
    public void testUpdateByExampleSelective() {
        // 设置更新条件
        Example example = new Example(User.class);
        // 声明条件的list
        List<Object> ids = new ArrayList<>();
        ids.add(1l);
        ids.add(2l);
        // 设置条件
        example.createCriteria().andIn("id", ids);

        // 设置修改的数据
        User user = new User();
        user.setName("刘备");

        this.newUserMapper.updateByExampleSelective(user, example);
    }
    @Test
    public void testUpdateByExample() {
        fail("Not yet implemented");
    }
    @Test
    public void testQueryUserByPage(){
        Map<String, Integer> map = new HashMap<>();
        map.put("page",1);
        map.put("rows",4);
        List<User> users = this.newUserMapper.queryUserByPage(map);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /*在mapper.xml中集成分页插件功能测试*/
    @Test
    public void testQueryUserByPageHelper(){
        PageHelper.startPage(1,4);

        List<User> list = this.newUserMapper.select(null);
        for (User user : list) {
            System.out.println(user);
        }

        //分页信息使用PageInfo
        PageInfo<User> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo.getPages());
        System.out.println(pageInfo.getTotal());
    }
}
