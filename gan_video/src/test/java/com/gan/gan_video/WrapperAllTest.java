//package com.gan.gan_video;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.gan.entity.SpringbootGanUser;
//import com.gan.mapper.SpringbootGanUserMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//import java.util.Arrays;
//import java.util.List;
//
//
//@SpringBootTest
//public class WrapperAllTest {
//    @Autowired
//    private SpringbootGanUserMapper springbootGanUserMapper;
//
//    @Test
//    void contextLoads() {
//        //查询一个复杂的，比如查询用户nick_name、邮箱不为空，年龄大于20的用户
//        QueryWrapper<SpringbootGanUser> wrapper = new QueryWrapper<>(); //首先新建一个 QueryWrapper
//        //链式编程
//        wrapper.isNotNull("nick_name")
//                .eq("login_name", "13649471017")
//                .ge("user_id", 17);  //g
////        eq相等   ne不相等，   gt大于，    lt小于         ge大于等于       le 小于等于
////        equal/ not equal/ greater than/ less than/ less than or equal/ great than or equal/
//        springbootGanUserMapper.selectList(wrapper).forEach(System.out::println);
////        ----------查询单个
//        SpringbootGanUser user = springbootGanUserMapper.selectOne(wrapper); //出现多个结果会报错，查询一个
//        System.out.println("user = " + user);
//    }
//
//    @Test
//    void test2() {
//        //查询区间内的记录
//        QueryWrapper<SpringbootGanUser> wrapper = new QueryWrapper<>();
//        wrapper.between("user_id",14,16);
//        List<SpringbootGanUser> user = springbootGanUserMapper.selectList(wrapper);
//        System.out.println("user = " + user);
////        Integer count = springbootGanUserMapper.selectCount(wrapper);
////        System.out.println("count = " + count);
//
//    }
//
//    @Test
//    void test3() {
//        //模糊查询
//        QueryWrapper<SpringbootGanUser> wrapper = new QueryWrapper<>();
//        wrapper.like("name",99)         //  名字中 存在 99
//                .notLike("name",6)      //  名字中 不存在 6
//                .likeRight("email",2)   //  邮箱 最右边是m  %m
//                .likeLeft("email","m"); //  邮箱 最左边是2  2%
//        springbootGanUserMapper.selectMaps(wrapper);
//    }
//
//    @Test
//    void test4() {
//        //子查询
//        QueryWrapper<SpringbootGanUser> wrapper = new QueryWrapper<>();
//        wrapper.inSql("user_id","select user_id from springboot_gan_user_token where user_id = 13");
//        List<Object> objects = springbootGanUserMapper.selectObjs(wrapper);//返回值是Object列表
//        objects.forEach(System.out::println);
//        System.out.println(objects);
//    }
//
//    @Test
//    void test5() {
//        //分组排序
//        QueryWrapper<SpringbootGanUser> wrapper = new QueryWrapper<>();
//        wrapper.orderByAsc("id");  //根据id升序排列
//        springbootGanUserMapper.selectList(wrapper).forEach(System.out::println);
//    }
//
//    @Test
//    void test6() {
//        //排序
//        QueryWrapper<SpringbootGanUser> wrapper = new QueryWrapper<>();
//        wrapper.orderByAsc("id");  //根据id升序排列
//        springbootGanUserMapper.selectList(wrapper).forEach(System.out::println);
//    }
//
//    @Test
//    void test7() {
//        //分组排序
//        QueryWrapper<SpringbootGanUser> wrapper = new QueryWrapper<>();
//        wrapper.groupBy("version").having("version = 1");
//        springbootGanUserMapper.selectList(wrapper).forEach(System.out::println);
//    }
//
//
//
//    //查询 nick_name 包含字符 136494710 ，并且 user_id 要小于 15 ，只需要输出 nick_name 、user_id 即可
//    @Test
//    public void selectList(){
//        QueryWrapper<SpringbootGanUser> userQueryWrapper = new QueryWrapper<>();
////        QueryWrapper<User> userQueryWrapper = Wrappers.query(); 和上面一样的效果
//        userQueryWrapper.select("nick_name", "user_id").like("nick_name" , "136494710").lt("user_id" , 15);
////        userQueryWrapper.like("username" , "k").lt("age" , 35).select("username", "age");
////        把select()放在最后面也可以，但我一般喜欢放在最前面，和sql语法保持一致
//        List<SpringbootGanUser> userList = springbootGanUserMapper.selectList(userQueryWrapper);
//        userList.forEach(System.out::println);
//    }
//
//    @Test
//    public void selectList2(){
//        QueryWrapper<SpringbootGanUser> userQueryWrapper = new QueryWrapper<>();
////        QueryWrapper<User> userQueryWrapper = Wrappers.query(); 和上面一样的效果
//        userQueryWrapper.select(SpringbootGanUser.class , e->!e.getColumn().equals("password_md5")).like("nick_name" , "136494710")
//                .between("user_id" , 12 , 15)
//                .isNotNull("password_md5");
//        List<SpringbootGanUser> userList = springbootGanUserMapper.selectList(userQueryWrapper);
//        userList.forEach(System.out::println);
//    }
//
//    @Test
//    public void selectList3(){
//        String nick_name = "136494710";
//        List<Integer> user_idList = Arrays.asList(12 , 13 , 14);
//        List<SpringbootGanUser> userList = springbootGanUserMapper.selectList(condition(nick_name , user_idList));
//        userList.forEach(System.out::println);
//    }
//
//    public QueryWrapper<SpringbootGanUser> condition(String nick_name , List<Integer> user_idList){
//        QueryWrapper<SpringbootGanUser> userQueryWrapper = new QueryWrapper<>();
////        if(!StringUtils.isEmpty(username)){
////            userQueryWrapper.like("username" , username);
////        }
////        if(!CollectionUtils.isEmpty(ageList)){
////            userQueryWrapper.in("age" , ageList);
////        }
//        userQueryWrapper.like(!StringUtils.isEmpty(nick_name) , "nick_name" , nick_name)
//                .in(!CollectionUtils.isEmpty(user_idList) , "user_id" , user_idList);
//        return userQueryWrapper;
//    }
//
//}
