package edu.aynu.project.controller;


import edu.aynu.common.VO.Result;
import edu.aynu.project.dao.ProjectStudentDao;
import edu.aynu.project.entity.Project;
import edu.aynu.project.entity.ProjectVO;
import edu.aynu.project.service.ProjectService;
import edu.aynu.student.dao.StudentDao;
import edu.aynu.team.dao.TeamDao;
import edu.aynu.team.service.TeamService;
import edu.aynu.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private ProjectStudentDao psDao;

    @GetMapping("")
    public String toListUI(HttpSession session){
        int status = ((User)session.getAttribute("userInfo")).getStatus();
        if(status == 3){
            return "/project/projectListForTeam";
        }
        else if(status == 2){
            return "/project/projectListForTeacher";
        }
        else {
            return "/project/projectListForAdmin";
        }
    }

    @GetMapping("/allPassProject")
    public String allPassProjectUI(HttpSession session){
        return "/project/projectAllPassList";
    }


    @GetMapping("/list")
    @ResponseBody
    public Result<ProjectVO> list(int page, int limit, String searchName, String searchStudent, HttpSession session){
        User user = ((User)session.getAttribute("userInfo"));
        Result<ProjectVO> result = projectService.findMyProject(page,limit,searchName,searchStudent,user);
        return result;
    }

    @GetMapping("/listAllPass")
    @ResponseBody
    public Result<ProjectVO> listAllPass(int page, int limit, String searchName, String searchStudent, HttpSession session){
        User user = ((User)session.getAttribute("userInfo"));
        Result<ProjectVO> result = projectService.findAll(page,limit,searchName,searchStudent,user);
        return result;
    }

    @GetMapping("/addUIBefore")
    public String toAddUIBefore(Model model){
        model.addAttribute("teamList",teamService.list());
        return "project/projectAddBefore";
    }

    @GetMapping("/addUI")
    public String toAddUI(Model model,HttpSession session){
        int teamId = ((User)session.getAttribute("userInfo")).getStatusId();
        model.addAttribute("team",teamDao.getTeamById(teamId));
        model.addAttribute("studentList",studentDao.getListByTeam(teamId));
        return "project/projectAdd";
    }


    @PostMapping("{idArr}")
    @ResponseBody
    public Result<Object> save(HttpSession session,Project project, @PathVariable("idArr") List<Integer> idArr){
        User user = ((User)session.getAttribute("userInfo")); //用户只能看到自己上传的
        //if (user.getAdmin() == 0) project.setUserId(user.getId());
        projectService.saveProject(project,idArr);
        return Result.success("新增成果成功！");
    }


    @DeleteMapping("/{ids}")
    @ResponseBody
    public Result<Object> delete(@PathVariable("ids") List<Integer> ids){
        for (Integer id : ids) {
            psDao.deleteByProjectId(id);
        }
        projectService.removeByIds(ids);
        return Result.success("删除成果成功！");
    }

    @GetMapping("/updateUI/{id}")
    public String updateUI(@PathVariable Integer id,Model model,HttpSession session){
        Project project = projectService.getById(id);
        model.addAttribute("project",project);
        String teamName = teamDao.selectById(project.getTeamId()).getName();
        model.addAttribute("teamName",teamName);
        model.addAttribute("studentList",studentDao.getListByTeam(project.getTeamId())); //团队全部人员
        model.addAttribute("studentChecked",projectService.selectStudentsByProjectId(id));//已选人员
        User user = ((User)session.getAttribute("userInfo"));
        if(user.getStatus()==3){
            return "project/projectEdit";
        }
        else if(user.getStatus()==2){
            return "project/projectExamForTeacher";
        }
        else {
            return "project/projectExamForAdmin";
        }
    }

    @PutMapping("/update/{idArr}")
    @ResponseBody
    public Result<Object> updateProject(Project project, @PathVariable("idArr") List<Integer> idArr,HttpSession session){
        User user = ((User)session.getAttribute("userInfo"));
        if(user.getStatus()==3){
            project.setAdminAdmit(0);
            project.setTeacherAdmit(0);
            projectService.updateByProjectId(project,idArr);
            return Result.success("修改成果成功！");
        }
        else {
            projectService.updateById(project);
            return Result.success("审核成功！");
        }
    }

}
