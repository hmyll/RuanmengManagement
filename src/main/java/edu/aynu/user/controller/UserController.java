package edu.aynu.user.controller;


import com.wf.captcha.utils.CaptchaUtil;
import edu.aynu.common.VO.Result;
import edu.aynu.teacher.dao.TeacherDao;
import edu.aynu.teacher.entity.Teacher;
import edu.aynu.teacher.service.TeacherService;
import edu.aynu.team.entity.Team;
import edu.aynu.team.service.TeamService;
import edu.aynu.user.entity.User;
import edu.aynu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeacherDao teacherDao;


    @PostMapping("/login")
    @ResponseBody
    public Result login(User param,String autoLogin, @RequestParam("captcha") String captcha, HttpServletRequest request, HttpServletResponse response,HttpSession session){
        //验证码验证
        if (!CaptchaUtil.ver(captcha, request)) {
            return Result.fail("验证码错误!");
        }

        User user = userService.Login(param);
        if(user != null){
            user.setPassword(null);
            session.setAttribute("userInfo",user);
            if(user.getStatus() == 1){      //管理员
                user.setName("管理员");
            }
            else if(user.getStatus() == 2){ //导师
                Teacher teacher = teacherService.getById(user.getStatusId());
                if(teacher.getAdminAdmit()==1){
                    user.setName(teacher.getName());
                }
                else {          //审核
                    return Result.examTeacher(teacher.getName());
                }
                user.setName(teacherService.getById(user.getStatusId()).getName());
            }
            else {                          //团队
                Team team = teamService.getById(user.getStatusId());
                if(team.getTeacherAdmit()==1&&team.getAdminAdmit()==1){
                    user.setName(team.getName());
                }
                else {          //审核
                    return Result.examTeam(team.getName());
                }
            }

            if(autoLogin != null){
                addCookie(param,request,response);
            }
            return Result.success();
        }
        return Result.fail("用户名或密码错误!");
    }

    private void addCookie(User user, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie1 = new Cookie("RuangMeng_LoginName",user.getLoginName());
        Cookie cookie2 = new Cookie("RuangMeng_Password",user.getPassword());
        //保存3天
        cookie1.setMaxAge(60*60*24*3);
        cookie2.setMaxAge(60*60*24*3);
        //cookie路径：当前目录下
        cookie1.setPath(request.getContextPath()+"/");
        cookie2.setPath(request.getContextPath()+"/");
        //把定义好的cookie返回客户端
        response.addCookie(cookie1);
        response.addCookie(cookie2);
    }

    @GetMapping("/examTeamUI")
    public String examTeamUI(HttpSession session){
        User user = (User)session.getAttribute("userInfo");
        session.setAttribute("teamInfo",teamService.getById(user.getStatusId()));
        return "user/examTeam";
    }

    @GetMapping("/examTeacherUI")
    public String examTeacherUI(HttpSession session){
        User user = (User)session.getAttribute("userInfo");
        session.setAttribute("teacherInfo",teacherService.getById(user.getStatusId()));
        return "user/examTeacher";
    }

    @GetMapping("/userListUI")
    public String userListUI(){
        return "user/userList";
    }

    @GetMapping("/userList")
    @ResponseBody
    public Result<User> list(int page, int limit, String searchName ){
        Result<User> result = userService.findAll(page,limit,searchName);
        return result;
    }

    @DeleteMapping("/{ids}")
    @ResponseBody
    public Result<Object> delete(@PathVariable("ids") List<Integer> ids){
        userService.removeByIds(ids);
        return Result.success("删除用户成功！");
    }

    @GetMapping("/settingUI")
    public String settingUI(Model model,HttpSession session){
        User user = (User)session.getAttribute("userInfo");
        model.addAttribute("user",user);
        return "user/userSetting";
    }

    @PutMapping("/setting")
    @ResponseBody
    public Result<Object> setting(User user,HttpSession session){
        int flag = userService.findLoginNameCount(user);    //查找用了此登录名称的个数
        if(flag == 0){
            userService.updateById(user);
            User user2 = userService.getById(user.getId());
            user2.setPassword(null);
            session.setAttribute("userInfo",user2);
            return Result.success("修改账户成功！");
        }
        else {
            return Result.fail("登录名称已被使用，请重新输入！");
        }
    }
    @GetMapping("/updatePasswordUI")
    public String updatePasswordUI(){
        return "user/userPassword";
    }

    @PutMapping("/updatePassword")
    @ResponseBody
    public Result<Object> updatePassword(String oldPassword,String newPassword,String againPassword,HttpSession session){
        if(!newPassword.equals(againPassword)){
            return Result.fail("两次输入的新密码不一样，请确认！");
        }
        int userId = ((User)session.getAttribute("userInfo")).getId();
        return userService.updatePassword(oldPassword,newPassword,userId);
    }


    @PostMapping("teamAdd")
    @ResponseBody
    public Result<Object> save(HttpSession session, Team team, User user, String again_Password){
        if(!user.getPassword().equals(again_Password)){
            return Result.fail("两次输入的新密码不一样，请确认！");
        }
        teamService.save(team);
        //user.setStatus(3);
        user.setStatusId(team.getId());
        user.setRegisterTime(new Date());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        int flag = userService.findLoginNameCount(user.getLoginName());
        if(flag == 0){
            userService.save(user);
            User user2 = userService.getById(user.getId());
            user2.setName(team.getName());
            user2.setPassword(null);
            session.setAttribute("userInfo",user2);
            return Result.success("注册成功！");
        }
        else {
            return Result.fail("登录名称已被使用，请重新输入！");
        }
    }

    @GetMapping("/addTeamUI")
    public String toAddUI(Model model){
        List<Teacher> teacherList = teacherDao.findPassTeacher();
        model.addAttribute("teacherList",teacherList);
        return "/team/teamAdd";
    }

    @PostMapping("/teacherAdd")
    @ResponseBody
    public Result<Object> saveTeacher(HttpSession session,Teacher teacher, User user, String again_Password){
        if(!user.getPassword().equals(again_Password)){
            return Result.fail("两次输入的新密码不一样，请确认！");
        }
        teacherService.save(teacher);
        //user.setStatus(3);
        user.setStatusId(teacher.getId());
        user.setRegisterTime(new Date());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        int flag = userService.findLoginNameCount(user.getLoginName());
        if(flag == 0){
            userService.save(user);
            User user2 = userService.getById(user.getId());
            user2.setName(teacher.getName());
            user2.setPassword(null);
            session.setAttribute("userInfo",user2);
            return Result.success("注册成功！");
        }
        else {
            return Result.fail("登录名称已被使用，请重新输入！");
        }
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        session.invalidate();
        Cookie cookie1 = new Cookie("RuangMeng_LoginName",null);//cookie名字要相同
        Cookie cookie2 = new Cookie("RuangMeng_Password",null);
        cookie1.setMaxAge(0); //设置它的生存时间为0
        cookie2.setMaxAge(0);
        cookie1.setPath(request.getContextPath()+"/");  // 相同路径
        cookie2.setPath(request.getContextPath()+"/");  // 相同路径
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        return "login";
    }

    @GetMapping("/download")
    public String fileDownLoad(HttpServletResponse response) throws Exception {
        File file = new File("D:\\upload\\大学生创新创业孵化基地团队入驻申请表.doc");
        //File file = new File("src\\resources\\templates\\aynu.png");
        if (!file.exists()) {
            return "下载文件不存在";
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        // 设置编码格式
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + URLEncoder.encode(file.getName(), "UTF-8"));
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        byte[] buff = new byte[1024];
        OutputStream os = response.getOutputStream();
        int i = 0;
        while ((i = bis.read(buff)) != -1) {
            os.write(buff, 0, i);
            os.flush();
        }
        bis.close();
        os.close();
        return "下载成功";
    }

}
