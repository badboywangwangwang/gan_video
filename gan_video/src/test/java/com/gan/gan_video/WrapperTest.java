//package com.gan.gan_video;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
//public class WrapperTest {
//    @Autowired
//    private SpringbootGanUserMapper springbootGanUserMapper;
//
//    /**
//     * 名字包含136494710并且user_id小于15
//     * <p>
//     * WHERE name LIKE '%136494710%' AND user_id < 15
//     */
//    @Test
//    public void selectByWrapperOne() {
//        QueryWrapper<SpringbootGanUser> wrapper = new QueryWrapper();
//        wrapper.like("nick_name", "136494710").lt("user_id", 15);
//        List<SpringbootGanUser> users = springbootGanUserMapper.selectList(wrapper);
//        users.forEach(System.out::println);
//    }
//
//    /**
//     * 名字包含136494710
//     * user_id大于14小于17
//     * login_name不能为空
//     * <p>
//     * WHERE name LIKE '%136494710%' AND user_id BETWEEN 14 AND 17 AND login_name IS NOT NULL
//     */
//    @Test
//    public void selectByWrapperTwo() {
//        QueryWrapper<SpringbootGanUser> wrapper = Wrappers.query();
//        wrapper.like("nick_name", "136494710").between("user_id", 14, 17).isNotNull("login_name");
//        List<SpringbootGanUser> users = springbootGanUserMapper.selectList(wrapper);
//        users.forEach(System.out::println);
//    }
//
//    /**
//     * 名字为13649471014
//     * 或者user_id大于等于14
//     * 按照年龄降序排序，年龄相同按照id升序排序
//     * <p>
//     * WHERE name LIKE '13649471014%' OR user_id >= 14 ORDER BY login_name DESC , user_id ASC
//     */
//    @Test
//    public void selectByWrapperThree() {
//        QueryWrapper<SpringbootGanUser> wrapper = Wrappers.query();
//        wrapper.likeRight("login_name", "13649471014").or()
//                .ge("user_id", 14).orderByDesc("login_name").orderByAsc("user_id");
//        List<SpringbootGanUser> users = springbootGanUserMapper.selectList(wrapper);
//        users.forEach(System.out::println);
//    }
//
//    /**
//     * 查询创建时间为2022年08月03
//     * 并且user_name有www
//     * <p>
//     * WHERE date_format(create_time,'%Y-%m-%d') = '2022-08-03' AND user_id IN (select user_id from springboot_gan_user_address where user_name like 'www%')
//     */
//    @Test
//    public void selectByWrapperFour() {
//        QueryWrapper<SpringbootGanUser> wrapper = Wrappers.query();
//        wrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}", "2022-08-03")
//                .inSql("user_id", "select user_id from springboot_gan_user_address where user_name like 'www%'");
//        List<SpringbootGanUser> users = springbootGanUserMapper.selectList(wrapper);
//        users.forEach(System.out::println);
//    }
//
//    /**
//     * 查询nick_name 包含 136494710
//     * 并且user_id小于16或者login_name不为空
//     * <p>
//     * WHERE name LIKE '136494710%' AND ( user_id < 16 OR login_name IS NOT NULL )
//     */
//    @Test
//    public void selectByWrapperFive() {
//        QueryWrapper<SpringbootGanUser> wrapper = Wrappers.query();
//        wrapper.likeRight("nick_name", "136494710").and(qw -> qw.lt("user_id", 16).or().isNotNull("login_name"));
//        List<SpringbootGanUser> users = springbootGanUserMapper.selectList(wrapper);
//        users.forEach(System.out::println);
//    }
//
//    /**
//     * 查询 nick_name 包含 136494710
//     * 并且（或者.or）user_id大于14 、user_id小于16、login_name 不能为空
//     * <p>
//     * WHERE nick_name LIKE ? OR ( user_id BETWEEN ? AND ? AND login_name IS NOT NULL )
//     */
//    @Test
//    public void selectByWrapperSix() {
//        QueryWrapper<SpringbootGanUser> wrapper = Wrappers.query();
//        wrapper.likeRight("nick_name", "136494710").and(
//                qw -> qw.between("user_id", 14, 16).isNotNull("login_name")
//        );
//        List<SpringbootGanUser> users = springbootGanUserMapper.selectList(wrapper);
//        users.forEach(System.out::println);
//    }
//
//    /**
//     * (user_id 小于15或者 login_name 不为空) 并且 nick_name 136494710
//     * WHERE ( user_id < 40 OR login_name IS NOT NULL ) AND nick_name LIKE '136494710%'
//     */
//    @Test
//    public void selectByWrapperSeven() {
//        QueryWrapper<SpringbootGanUser> wrapper = Wrappers.query();
//        wrapper.nested(qw -> qw.lt("user_id", 15).or().isNotNull("login_name"))
//                .likeRight("nick_name", "136494710");
//        List<SpringbootGanUser> users = springbootGanUserMapper.selectList(wrapper);
//        users.forEach(System.out::println);
//    }
//
//    /**
//     * 查询 user_id 为12、14、19
//     * WHERE user_id IN (?,?,?)
//     */
//    @Test
//    public void selectByWrapperEight() {
//        QueryWrapper<SpringbootGanUser> wrapper = Wrappers.query();
//        wrapper.in("user_id", Arrays.asList(12, 14, 19));
//        List<SpringbootGanUser> users = springbootGanUserMapper.selectList(wrapper);
//        users.forEach(System.out::println);
//    }
//
//    /**
//     * 查询一条数据
//     * limit 1
//     */
//    @Test
//    public void selectByWrapperNine() {
//        QueryWrapper<SpringbootGanUser> wrapper = Wrappers.query();
//        wrapper.in("user_id", Arrays.asList(15, 14, 19)).last("limit 1");
//        List<SpringbootGanUser> users = springbootGanUserMapper.selectList(wrapper);
//        users.forEach(System.out::println);
//    }
//
//}
