package edu.aynu.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.aynu.common.VO.BarVO;
import edu.aynu.common.VO.PieVO;
import edu.aynu.common.VO.Result;
import edu.aynu.project.dao.ProjectDao;
import edu.aynu.student.dao.StudentDao;
import edu.aynu.student.entity.Student;
import edu.aynu.teacher.dao.TeacherDao;
import edu.aynu.team.dao.TeamDao;
import edu.aynu.team.entity.Team;
import edu.aynu.team.entity.TeamVO;
import edu.aynu.team.service.TeamService;
import edu.aynu.user.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl extends ServiceImpl<TeamDao, Team> implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private ProjectDao projectDao;

    @Override
    public Result<TeamVO> findAll(int page, int limit, String searchName,User user) {
        LambdaQueryWrapper<Team> lqm = new LambdaQueryWrapper();
        lqm.like(Strings.isNotEmpty(searchName),Team::getName,searchName);
        IPage<Team> iPage = teamDao.selectPage(new Page<Team>(page ,limit) ,lqm);
        List<Team> teamList = iPage.getRecords();
        Integer count = teamDao.selectCount(null);
        ArrayList<TeamVO> teamVOS = new ArrayList<>();
        int status = user.getStatus();
        int cnt = 1;
        for (Team team : teamList) {
            if(status==3){  //团队只能看到已通过审核
                if(team.getTeacherAdmit()!=1||team.getAdminAdmit()!=1) continue;
            }
            else if(status==2){ //导师只能已通过或者向自己申请的队伍
                if(team.getTeacherId()!=user.getStatusId()) continue;
            }
            else if(status==1){ //导师未审核，管理员不能看见
                if(team.getTeacherAdmit() !=1 ) continue;
            }
            TeamVO teamVO = new TeamVO();
            BeanUtils.copyProperties(team,teamVO);
            if(team.getCaptionId()!=null)teamVO.setCaption(studentDao.selectById(team.getCaptionId()).getName());
            teamVO.setTeacher(teacherDao.selectById(team.getTeacherId()).getName());
            teamVO.setStuCount(studentDao.getCountByTeam(team.getId()));
            teamVO.setProCount(projectDao.selectCountByTeam(team.getId()));
            teamVO.setStatusId(user.getStatusId());
            final List<Student> studentList = studentDao.getListByTeam(team.getId());
            StringBuffer students = new StringBuffer();
            for (Student student : studentList) {
                if(students.length()>0) students.append(',');
                students.append(student.getName());
            }
            teamVO.setStudents(new String(students));
            teamVOS.add(teamVO);
        }
        long total = 0;
        if(user.getStatus()==1){
            total = Integer.toUnsignedLong(teamDao.selectCount(null));
        }
        else{
            total = teamDao.getTeamIdByTeacherId(user.getStatusId()).size();
        }
        return new Result<TeamVO>(0,"",total,teamVOS);
    }

    @Override
    public BarVO getBarVO() {
        final List<PieVO> pieVOList = teamDao.getBarVO();
        List<String> nameList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();
        for (PieVO pieVO : pieVOList) {
            nameList.add(pieVO.getName());
            valueList.add(pieVO.getValue());
        }
        return new BarVO(nameList,valueList);
    }

    @Override
    public Result<TeamVO> findAllPass(int page, int limit, String searchName) {
        LambdaQueryWrapper<Team> lqm = new LambdaQueryWrapper();
        lqm.like(Strings.isNotEmpty(searchName),Team::getName,searchName);
        IPage<Team> iPage = teamDao.selectPage(new Page<Team>(page ,limit) ,lqm);
        List<Team> teamList = iPage.getRecords();
        Integer count = teamDao.selectCount(null);
        ArrayList<TeamVO> teamVOS = new ArrayList<>();
        for (Team team : teamList) {
            if(team.getTeacherAdmit()+team.getAdminAdmit()<2) continue;
            TeamVO teamVO = new TeamVO();
            BeanUtils.copyProperties(team,teamVO);
            if(team.getCaptionId()!=null)teamVO.setCaption(studentDao.selectById(team.getCaptionId()).getName());
            teamVO.setTeacher(teacherDao.selectById(team.getTeacherId()).getName());
            teamVO.setStuCount(studentDao.getCountByTeam(team.getId()));
            teamVO.setProCount(projectDao.selectCountByTeam(team.getId()));
            final List<Student> studentList = studentDao.getListByTeam(team.getId());
            StringBuffer students = new StringBuffer();
            for (Student student : studentList) {
                if(students.length()>0) students.append(',');
                students.append(student.getName());
            }
            teamVO.setStudents(new String(students));
            teamVOS.add(teamVO);
        }
        long total = teamDao.getCountByPassed();

        return new Result<TeamVO>(0,"",total,teamVOS);
    }
}
