package edu.aynu.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.aynu.common.VO.Result;
import edu.aynu.teacher.dao.TeacherDao;
import edu.aynu.teacher.entity.Teacher;
import edu.aynu.teacher.entity.TeacherVO;
import edu.aynu.teacher.service.TeacherService;
import edu.aynu.team.dao.TeamDao;
import edu.aynu.user.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherDao, Teacher> implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private TeamDao teamDao;

    @Override
    public Result<TeacherVO> findAll(int page, int limit, String searchName, String searchTeam, User user) {
        LambdaQueryWrapper<Teacher> lqm = new LambdaQueryWrapper();
        lqm.like(Strings.isNotEmpty(searchName),Teacher::getName,searchName);
        IPage<Teacher> iPage = teacherDao.selectPage(new Page<Teacher>(page ,limit) ,lqm);
        List<Teacher> teacherList = iPage.getRecords();
        List<TeacherVO> teacherVOList = new ArrayList<>();
        for (Teacher teacher : teacherList) {
            if(user.getStatus()!=1){
                if(teacher.getAdminAdmit() != 1)continue;
            }
            StringBuffer teams = new StringBuffer();
            List<String> teamList =  teamDao.getTeamNameByTeacherId(teacher.getId());
            for (String team : teamList) {
                if(teams.length()>0) teams.append(",");
                teams.append(team);
            }
            TeacherVO teacherVO = new TeacherVO();
            BeanUtils.copyProperties(teacher,teacherVO);
            teacherVO.setTeams(new String(teams));
            teacherVOList.add(teacherVO);
        }
        long total = 0;
        if(user.getStatus()==1){
            total = Integer.toUnsignedLong(teamDao.selectCount(null));
        }
        else{
            total = teacherDao.findPassTeacher().size();
        }
        return new Result<TeacherVO>(0,"",Integer.toUnsignedLong(teacherVOList.size()),teacherVOList);
    }

}
