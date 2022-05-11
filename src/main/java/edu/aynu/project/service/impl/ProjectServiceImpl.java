package edu.aynu.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.aynu.common.VO.Result;
import edu.aynu.project.dao.ProjectDao;
import edu.aynu.project.dao.ProjectStudentDao;
import edu.aynu.project.entity.Project;
import edu.aynu.project.entity.ProjectStudent;
import edu.aynu.project.entity.ProjectVO;
import edu.aynu.project.service.ProjectService;
import edu.aynu.student.dao.StudentDao;
import edu.aynu.teacher.dao.TeacherDao;
import edu.aynu.team.dao.TeamDao;
import edu.aynu.team.entity.Team;
import edu.aynu.user.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, Project> implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private ProjectStudentDao psDao;

    @Autowired
    private StudentDao studentDao;

    @Override
    public Result<ProjectVO> findMyProject(int page, int limit, String searchName, String searchStudent, User user) { //查看自己的
        LambdaQueryWrapper<Project> lqm = new LambdaQueryWrapper();
        lqm.like(Strings.isNotEmpty(searchName), Project::getName,searchName);
        IPage<Project> iPage = projectDao.selectPage(new Page<Project>(page ,limit) ,lqm);
        List<Project> projectList = iPage.getRecords();
        ArrayList<ProjectVO> projectVOS = new ArrayList<>();
        for (Project project : projectList) {
            if(user.getStatus()==3 && project.getTeamId() != user.getStatusId())  continue;
            if(user.getStatus()==2 && teacherDao.getIdByTeamId(project.getTeamId()) != user.getStatusId()) continue;
            if(user.getStatus()==1 && project.getTeacherAdmit()!=1) continue;
            ProjectVO projectVO = new ProjectVO();
            BeanUtils.copyProperties(project,projectVO);
            final Team team = teamDao.getTeamById(project.getTeamId());
            if(team == null) continue;
            projectVO.setTeam(team.getName());
            projectVO.setTeacher(teacherDao.selectById(team.getTeacherId()).getName());
            projectVO.setStatusId(user.getStatusId());
            StringBuffer students = new StringBuffer();
            final List<ProjectStudent> projectStudents = psDao.selectListByProjectId(project.getId());
            Boolean flag = false;
            for (ProjectStudent ps : projectStudents) {
                if(students.length()>0) students.append(',');
                String studentName = studentDao.selectById(ps.getStudentId()).getName();
                students.append(studentName);
                if(searchStudent != null && studentName.contains(searchStudent)) flag = true;
            }
            projectVO.setStudents(new String(students));
            if (flag == true || searchName == null) projectVOS.add(projectVO); //搜索框为空或者这个成果包含了这个名字
        }
        return new Result<ProjectVO>(0,"", (long) projectVOS.size(),projectVOS);
    }


    @Override
    public Result<ProjectVO> findAll(int page, int limit, String searchName, String searchStudent, User user) {
        LambdaQueryWrapper<Project> lqm = new LambdaQueryWrapper();
        lqm.like(Strings.isNotEmpty(searchName), Project::getName,searchName);
        IPage<Project> iPage = projectDao.selectPage(new Page<Project>(page ,limit) ,lqm);
        List<Project> projectList = iPage.getRecords();
        ArrayList<ProjectVO> projectVOS = new ArrayList<>();
        for (Project project : projectList) {
            if(project.getAdminAdmit()+project.getTeacherAdmit()<2) continue;
            ProjectVO projectVO = new ProjectVO();
            BeanUtils.copyProperties(project,projectVO);
            final Team team = teamDao.getTeamById(project.getTeamId());
            if(team == null) continue;
            projectVO.setTeam(team.getName());
            projectVO.setTeacher(teacherDao.selectById(team.getTeacherId()).getName());
            projectVO.setStatusId(user.getStatusId());
            StringBuffer students = new StringBuffer();
            final List<ProjectStudent> projectStudents = psDao.selectListByProjectId(project.getId());
            Boolean flag = false;
            for (ProjectStudent ps : projectStudents) {
                if(students.length()>0) students.append(',');
                String studentName = studentDao.selectById(ps.getStudentId()).getName();
                students.append(studentName);
                if(searchStudent != null && studentName.contains(searchStudent)) flag = true;
            }
            projectVO.setStudents(new String(students));
            if (flag == true || searchName == null) projectVOS.add(projectVO); //搜索框为空或者这个成果包含了这个名字
        }
        return new Result<ProjectVO>(0,"", (long) projectVOS.size(),projectVOS);
    }


    @Override
    public void saveProject(Project project , List<Integer> idArr) {
        projectDao.insert(project);
        Integer projectId = project.getId();
        insertPS(projectId,idArr);
    }

    @Override
    public List<Integer> selectStudentsByProjectId(Integer id) {
        return projectDao.selectStudentsByProjectId(id);
    }

    @Override
    public void updateByProjectId(Project project, List<Integer> idArr) {
        projectDao.updateById(project);
        int id = project.getId();
        psDao.deleteByProjectId(id);//先把ps关系表中此成果相关的都删除
        insertPS(id,idArr);     //再添加
    }



    public void insertPS(Integer projectId,List<Integer> idArr){
        for (Integer studentId : idArr) {
            psDao.insert(new ProjectStudent(projectId,studentId));
        }
    }

}
