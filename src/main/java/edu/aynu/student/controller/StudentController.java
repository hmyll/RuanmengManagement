package edu.aynu.student.controller;


import edu.aynu.common.VO.Result;
import edu.aynu.student.dao.StudentDao;
import edu.aynu.student.entity.Student;
import edu.aynu.student.entity.StudentVO;
import edu.aynu.student.service.StudentService;
import edu.aynu.teacher.service.TeacherService;
import edu.aynu.team.dao.TeamDao;
import edu.aynu.team.entity.Team;
import edu.aynu.team.service.TeamService;
import edu.aynu.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private StudentDao studentDao;

    @GetMapping("")
    public String toStuListUI(){
        return "/student/studentList";
    }

    @GetMapping("listReadOnly")
    public String listReadOnly(){
        return "/student/studentListReadOnly";
    }

    @GetMapping("/list")
    @ResponseBody
    public Result<StudentVO> list(int page, int limit, String searchName, String searchTeam, HttpSession session){
        User user = (User)session.getAttribute("userInfo");
        Result<StudentVO> result = studentService.findAll(page,limit,searchName,searchTeam,user);
        return result;
    }

    @PostMapping("")
    @ResponseBody
    public Result<Object> save(HttpSession session,Student student){
        if(studentDao.findByStuId(student.getStuId())!=null){
            return Result.fail("该成员已在其他团队！");
        }
        int teamId = ((User)session.getAttribute("userInfo")).getStatusId();
        student.setTeamId(teamId);
        studentService.save(student);
        return Result.success("新增成员成功！");
    }

    @GetMapping("/add/ui")
    public String toAddUI(Model model){
        List<Team> teamList = teamService.list();
        model.addAttribute("teamList",teamList);
        return "/student/studentAdd";
    }

    @DeleteMapping("/{ids}")
    @ResponseBody
    public Result<Object> delete(@PathVariable("ids")List<Integer> ids){
        studentService.removeByIds(ids);
        return Result.success("删除成员成功！");
    }

    @GetMapping("{id}")
    public String getStudent(@PathVariable Integer id,Model model){
        Student student = studentService.getById(id);
        model.addAttribute("student",student);
        model.addAttribute("teamList",teamService.list());
        return "student/studentEdit";
    }

    @PutMapping("")
    @ResponseBody
    public Result<Object> updateStudent(Student student){
        Student stu1 = studentDao.findByStuId(student.getStuId());
        if(stu1.getId()!=student.getId()){ //该学号已被其他人使用
            return Result.fail("该成员已在其他团队！");
        }
        studentService.updateById(student);
        return Result.success("修改成员成功！");
    }
}
