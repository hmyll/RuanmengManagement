package edu.aynu.teacher.controller;


import edu.aynu.common.VO.Result;
import edu.aynu.teacher.entity.Teacher;
import edu.aynu.teacher.entity.TeacherVO;
import edu.aynu.teacher.service.TeacherService;
import edu.aynu.team.service.TeamService;
import edu.aynu.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("")
    public String toStuListUI(HttpSession session){
        User user  = (User)session.getAttribute("userInfo");
        if(user.getStatus() == 1 ){
            return "/teacher/teacherListForAdmin";
        }
        else {
            return "/teacher/teacherList";
        }

    }

    @GetMapping("/list")
    @ResponseBody
    public Result<TeacherVO> list(int page, int limit, String searchName, String searchTeam,HttpSession session){
        User user  = (User)session.getAttribute("userInfo");
        Result<TeacherVO> result = teacherService.findAll(page,limit,searchName,searchTeam,user);
        return result;
    }

    @PostMapping("/teacherAdd")
    @ResponseBody
    public Result<Object> save(Teacher teacher){
        teacherService.save(teacher);
        return Result.success("注册成功！");
    }

    @GetMapping("/add/ui")
    public String toAddUI(Model model){
        return "/teacher/teacherAdd";
    }

    @DeleteMapping("/{ids}")
    @ResponseBody
    public Result<Object> delete(@PathVariable("ids") List<Integer> ids){
        teacherService.removeByIds(ids);
        return Result.success("删除指导老师成功！");
    }

//    @GetMapping("{id}")
//    public String getTeacher(@PathVariable Integer id,Model model){
//        Teacher teacher = teacherService.getById(id);
//        model.addAttribute("teacher",teacher);
//        model.addAttribute("teamList",teamService.list());
//        return "teacher/teacherEdit";
//    }
    @GetMapping("/editUI")
    public String getTeacher(HttpSession session,Model model){
        int teacherId = ((User)session.getAttribute("userInfo")).getStatusId();
        Teacher teacher = teacherService.getById(teacherId);
        model.addAttribute("teacher",teacher);
        //model.addAttribute("teamList",teamService.list());
        return "teacher/teacherEdit";
    }

    @GetMapping("/examUI/{id}")
    public String examUI(HttpSession session,Model model,@PathVariable("id") Integer id){
        Teacher teacher = teacherService.getById(id);
        model.addAttribute("teacher",teacher);
        return "teacher/teacherExam";
    }


    @PutMapping("")
    @ResponseBody
    public Result<Object> update(Teacher teacher,HttpSession session){
        User user  = (User)session.getAttribute("userInfo");
        if(user.getStatus() == 2){
            Teacher teacher1 = teacherService.getById(teacher.getId());
            if(teacher1.getAdminAdmit()==-1){
                teacher.setAdminAdmit(0);
            }
            teacherService.updateById(teacher);
            return Result.success("修改成功！");
        }
        else {
            teacherService.updateById(teacher);
            return Result.success("审核成功！");
        }
    }

}
