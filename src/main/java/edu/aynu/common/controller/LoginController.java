package edu.aynu.common.controller;

import com.wf.captcha.utils.CaptchaUtil;
import edu.aynu.common.VO.BarVO;
import edu.aynu.common.VO.PieVO;
import edu.aynu.project.dao.ProjectDao;
import edu.aynu.student.dao.StudentDao;
import edu.aynu.teacher.dao.TeacherDao;
import edu.aynu.team.dao.TeamDao;
import edu.aynu.team.service.TeamService;
import edu.aynu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private TeacherDao teacherDao;


    @Autowired
    private UserService userService;

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private TeamService teamService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping({"/index",""})
    public String index(HttpServletRequest request,HttpSession session){
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome(Model model){
        model.addAttribute("studentNum",studentDao.selectCount(null));
        model.addAttribute("teamNum",teamDao.getCountByPassed());
        model.addAttribute("teacherNum",(teacherDao.findPassTeacher()).size());
        model.addAttribute("projectNum",projectDao.selectPassCount());
        return "welcome";
    }

    @RequestMapping("/chart")
    @ResponseBody
    public List<PieVO> getChart(){
        return userService.getPieVO();
    }

    @RequestMapping("/barChart")
    @ResponseBody
    public BarVO getBarChart(){
        return teamService.getBarVO();
    }

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }
}
