package edu.aynu;

import edu.aynu.notice.service.NoticeService;
import edu.aynu.project.dao.ProjectDao;
import edu.aynu.project.dao.ProjectStudentDao;
import edu.aynu.project.service.ProjectService;
import edu.aynu.student.dao.StudentDao;
import edu.aynu.teacher.dao.TeacherDao;
import edu.aynu.team.dao.TeamDao;
import edu.aynu.team.service.TeamService;
import edu.aynu.user.dao.UserDao;
import edu.aynu.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootTest
class ApplicationTests {

    @Autowired
    public UserService userService;

    @Autowired
    public UserDao userDao;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ProjectStudentDao psDao;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private ProjectDao projectDao;

    @Test
    void Test17(){
        System.out.println(projectDao.getCountByStudentId(1));
    }

    @Test
    void Teat16(){
        System.out.println(teamDao.getTeamNameByTeacherId(1));
    }

    @Test
    void Test15(){
        System.out.println(teacherDao.getIdByTeamId(1));
    }

    @Test
    void Test13(){
        System.out.println(projectService.selectStudentsByProjectId(1));
    }

    @Test
    void Test14(){
        System.out.println(teamService.getBarVO());
    }

    @Test
    void Test11(){
        System.out.println(psDao.selectListByProjectId(1));
    }

    @Test
    void Test10(){
        System.out.println(psDao.selectById(1));
    }



    @Test
    void contextLoads() {
        System.out.println(userService.list());
    }

    @Test
    void test1(){
        //md5 sprint提供 加密加盐
//        String s = DigestUtils.md5DigestAsHex("12345".getBytes());
//        System.out.println(s);

        //spring安全框架提供的加密方法，可自动加盐
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        final String encode1 = passwordEncoder.encode("123");
        final String encode2 = passwordEncoder.encode("123");
        System.out.println(encode1);
        System.out.println(encode2);
        boolean match1 = passwordEncoder.matches("123","$2a$10$Umh/Td3ecHz.S.fsJUCvqezox3OGWwC.2PRf0VlpaQFTjWk8HbCai");
        boolean match2 = passwordEncoder.matches("123","$2a$10$cDap/b5mZhhF1FCiIeoaqOLlfX/62RnVP3oWvBFWFjDpSN8agIlGa");
        System.out.println(match1);
        System.out.println(match2);
    }

    @Test
    void Encode(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("111"));
    }

    @Test
    void Test2() {
        System.out.println(userDao.findTeamNameAndNum());
    }


    @Test
    void Test4(){
        System.out.println(userDao.findByLonginName(1,"admin1"));
    }

    @Test
    void Test5(){
        userDao.updatePasswordById("$2a$10$NU6pX1pMNC/5t7ERnknfaOo8JWJwkA7IxgIXVddrTTtsi7PDK6.eO",1);
    }

    @Test
    void Test6(){
        System.out.println(userService.findAll(1,5,""));
    }

    @Test
    void Test7(){
        System.out.println(new Date());
    }

    @Test
    void Test8(){
        noticeService.addNoticeCount(1);
    }
}
