//package com.gan.gan_video;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.gan.entity.SpringbootGanUser;
//import com.gan.mapper.SpringbootGanUserMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//import java.util.List;
//
//@SpringBootTest
//class MybatisPlusApplicationTest {
//
//    //继承了BaseMapper ,所有方法来自父类，可扩展
//    @Autowired
//    private SpringbootGanUserMapper springbootGanUserMapper;
//
//
//    /**
//     * 新增一条数据
//     */
//    @Test
//    void testSave(){
//        SpringbootGanUser user = new SpringbootGanUser();
//        user.setLoginName("13649471088");
//        user.setPasswordMd5("e10adc3949ba59abbe56e057f20f883e");
//        user.setIntroduceSign("我就是我,不一样的烟火");
//        user.setNickName("13649471088");
//        //没有设置ID却自动生成的ID
//        int result = springbootGanUserMapper.insert(user);
//        System.out.println("result = " + result);
//        System.out.println("user = " + user);
//    }
//
//    /**
//     * 更新一条数据
//     */
//    @Test
//    void testUpdate(){
//        SpringbootGanUser user = new SpringbootGanUser();
//        user.setUserId(13L);
//        user.setIntroduceSign("我就是我,不一样的烟火");
//        int i =  springbootGanUserMapper.updateById(user);//受影响的行数,参数是一个user不是id,点击看源码
//        System.out.println("i = " + i);
//    }
//
//    /**
//     * 获取一条数据
//     */
//    @Test
//    void testGetById(){
//        SpringbootGanUser user = springbootGanUserMapper.selectById(12L);
//        System.out.println(user);
//    }
//
//    //查询指定多用户
//    @Test
//    public void testSelectBatchIds() {
//        //Arrays.asList()创建了一个固定大小的集合
//        List<SpringbootGanUser> users = springbootGanUserMapper.selectBatchIds(Arrays.asList( 12, 13));//参数Collection的集合
//        users.forEach(System.out::println);
////        [Arrays.asList()详解](https://blog.csdn.net/kzadmxz/article/details/80394351)
//    }
//
//    /**
//     * 删除一条数据
//     */
//    @Test
//    void testDelete(){
////        springbootGanUserMapper.deleteById(18L);
////        springbootGanUserMapper.delete(null);//全部删除
//
//        LambdaQueryWrapper<SpringbootGanUser> lqw = new LambdaQueryWrapper<SpringbootGanUser>();
//        lqw.eq(SpringbootGanUser::getLoginName,"13649471016");
//        int i =  springbootGanUserMapper.delete(lqw);
//        System.out.println(i);
//    }
//
//    @Test
//    void testGetAll() {
//        /**
//         * 获取所有数据
//         */
////        List<SpringbootGanUser> userList = springbootGanUserMapper.selectList(null);
//
////        //方式一： 条件查询
////        QueryWrapper qw = new QueryWrapper();
////        qw.eq("login_name","13649471012");
////        List<SpringbootGanUser> userList = springbootGanUserMapper.selectList(qw);
////        System.out.println(userList);
//
//////        //方式二： Lambda条件查询
////        QueryWrapper<SpringbootGanUser> qw = new QueryWrapper<SpringbootGanUser>();
////        qw.lambda().eq(SpringbootGanUser::getLoginName,"13649471012");
////        List<SpringbootGanUser> userList = springbootGanUserMapper.selectList(qw);
////        System.out.println(userList);
//
////        //方式三： Lambda条件查询
////        LambdaQueryWrapper<SpringbootGanUser> lqw = new LambdaQueryWrapper<SpringbootGanUser>();
////        lqw.eq(SpringbootGanUser::getLoginName,"13649471012");
////        List<SpringbootGanUser> userList = springbootGanUserMapper.selectList(lqw);
////        System.out.println(userList);
//
////        多条件
//        LambdaQueryWrapper<SpringbootGanUser> lqw = new LambdaQueryWrapper<SpringbootGanUser>();
//        //10-30
////        lqw.lt(SpringbootGanUser::getUserId,15).gt(SpringbootGanUser::getUserId,10);
//        //<10  >30
//        lqw.lt(SpringbootGanUser::getUserId,14).or().gt(SpringbootGanUser::getUserId,17);
//        List<SpringbootGanUser> userList = springbootGanUserMapper.selectList(lqw);
//        System.out.println(userList);
//    }
//
//    @Test
//    void testGetByPage(){
//        //1 创建IPage分页对象,设置分页参数,1为当前页码，3为每页显示的记录数
//        IPage page = new Page(1,2);
//        //2 执行分页查询
//        springbootGanUserMapper.selectPage(page,null);
//        //3 获取分页结果
//        System.out.println("当前页码值："+page.getCurrent());
//        System.out.println("每页显示数："+page.getSize());
//        System.out.println("一共多少页："+page.getPages());
//        System.out.println("一共多少条数据："+page.getTotal());
//        System.out.println("数据："+page.getRecords());
//    }
//
//    //测试乐观锁 成功 --单线程情况
//    @Test
//    public void testOptimisticLocker() {
//        //1、查询用户信息
//        SpringbootGanUser user = springbootGanUserMapper.selectById(13L);
//        //2、修改用户信息
//        user.setIntroduceSign("我就是我,不一样的烟火");
//        user.setNickName("小猴子");
//        //3、更新操作
//        springbootGanUserMapper.updateById(user);
//    }
//
//
//    //测试乐观锁 失败
//    @Test
//    public void testOptimisticLocker2() {
//        //模拟多线程
//        SpringbootGanUser user = springbootGanUserMapper.selectById(13L);
//        user.setIntroduceSign("123我就是我,不一样的烟火");
//        user.setNickName("帅小伙111");//我们在这里对线程1修改值
//
//        //线程2插队
//        SpringbootGanUser user2 = springbootGanUserMapper.selectById(13L);
//        user2.setIntroduceSign("321我就是我,不一样的烟火");
//        user2.setNickName("帅小伙222");
//        springbootGanUserMapper.updateById(user2); //线程2抢先提交
//
//        springbootGanUserMapper.updateById(user);//线程1失败，乐观锁在这种情况下防止了脏数据存在，没有乐观锁就会有覆盖掉线程2的操作
//    }
//
//
//
//}