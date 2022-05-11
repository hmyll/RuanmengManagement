package edu.aynu.team.controller;


import edu.aynu.common.VO.Result;
import edu.aynu.student.dao.StudentDao;
import edu.aynu.student.entity.Student;
import edu.aynu.student.service.StudentService;
import edu.aynu.teacher.dao.TeacherDao;
import edu.aynu.teacher.entity.Teacher;
import edu.aynu.teacher.service.TeacherService;
import edu.aynu.team.entity.Team;
import edu.aynu.team.entity.TeamVO;
import edu.aynu.team.service.TeamService;
import edu.aynu.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private StudentDao studentDao;

    @GetMapping("")
    public String toStuListUI(HttpSession session){
        User user = (User)session.getAttribute("userInfo");
        if(user.getStatus()==3){
            return "/team/teamAllPassList";
        }
        else if(user.getStatus()==2){
            return "/team/teamListForTeacher";
        }
        else {
            return "/team/teamListForAdmin";
        }
    }

    @GetMapping("/allPassTeam")
    public String allPassTeam(HttpSession session){
        return "/team/teamAllPassList";
    }


    @GetMapping("/list")
    @ResponseBody
    public Result<TeamVO> list(int page,int limit,String searchName ,HttpSession session){
        User user = (User)session.getAttribute("userInfo");
        Result<TeamVO> result = teamService.findAll(page,limit,searchName,user);
        return result;
    }

    @GetMapping("/allPassList")
    @ResponseBody
    public Result<TeamVO> allPassList(int page,int limit,String searchName ,HttpSession session){
        Result<TeamVO> result = teamService.findAllPass(page,limit,searchName);
        return result;
    }


    @PostMapping("")
    @ResponseBody
    public Result<Object> save(Team team){
        teamService.save(team);
        return Result.success("新增团队成功！");
    }

    @GetMapping("/add/ui")
    public String toAddUI(Model model){
        List<Teacher> teacherList = teacherDao.findPassTeacher();
        model.addAttribute("teacherList",teacherList);
        List<Student> studentList = studentService.list();
        model.addAttribute("studentList",studentList);
        return "/team/teamAdd";
    }

    @DeleteMapping("/{ids}")
    @ResponseBody
    public Result<Object> delete(@PathVariable("ids") List<Integer> ids){
        teamService.removeByIds(ids);
        return Result.success("删除团队成功！");
    }

    @GetMapping("/editUI")
    public String getTeam(HttpSession session,Model model){
        int teamId = ((User)session.getAttribute("userInfo")).getStatusId();
        Team team = teamService.getById(teamId);
        model.addAttribute("team",team);
        List<Teacher> teacherList = teacherDao.findPassTeacher();
        model.addAttribute("teacherList",teacherList);
        List<Student> studentList = studentDao.getListByTeam(teamId);
        model.addAttribute("studentList",studentList);
        return "team/teamEdit";
    }

    @GetMapping("/examUI/{id}")
    public String teacherExamUI(@PathVariable("id") Integer id,Model model){
        Team team = teamService.getById(id);
        model.addAttribute("team",team);
        return "team/teamExam";
    }


    @PutMapping("")
    @ResponseBody
    public Result<Object> update(HttpSession session,Team team){
        User user = (User)session.getAttribute("userInfo");
        if(user.getStatus()==3){
            if(team.getAdminAdmit()!=null && team.getTeacherAdmit() != null &&team.getTeacherAdmit()+team.getTeacherAdmit()<2){
                team.setAdminAdmit(0);
                team.setTeacherAdmit(0);
            }
            teamService.updateById(team);
            return Result.success("修改团队成功！");
        }
        else {
            teamService.updateById(team);
            return Result.success("团队审核成功！");
        }
    }





}
