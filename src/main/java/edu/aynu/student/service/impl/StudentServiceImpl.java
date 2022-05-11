package edu.aynu.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.aynu.common.VO.Result;
import edu.aynu.project.dao.ProjectDao;
import edu.aynu.student.dao.StudentDao;
import edu.aynu.student.entity.Student;
import edu.aynu.student.entity.StudentVO;
import edu.aynu.student.service.StudentService;
import edu.aynu.team.dao.TeamDao;
import edu.aynu.user.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private ProjectDao projectDao;

    @Override
    public Result<StudentVO> findAll(int page, int limit, String searchName, String searchTeam, User user) {
        LambdaQueryWrapper<Student> lqm = new LambdaQueryWrapper();
        lqm.like(Strings.isNotEmpty(searchName),Student::getName,searchName);
        IPage<Student> iPage = studentDao.selectPage(new Page<Student>(page ,limit) ,lqm);
        List<Student> studentList = iPage.getRecords();
        List<StudentVO> studentVOList = new ArrayList<>();
        for (Student student : studentList) {
            String teamName = teamDao.getNameById(student.getTeamId());
            if(searchTeam != null && searchTeam != ""){
                if(teamName == null) continue;
                if(!teamName.contains(searchTeam)) continue;
            }
            if(user.getStatus() != 1){
                if(user.getStatus() == 2){
                    List<Integer> teamIdList =  teamDao.getTeamIdByTeacherId(user.getStatusId());
                    if(!teamIdList.contains(student.getTeamId())) continue;
                }
                if(user.getStatus()==3){
                    if(student.getTeamId() != user.getStatusId()) continue;
                }
            }
            StudentVO studentVO = new StudentVO();
            BeanUtils.copyProperties(student,studentVO);
            studentVO.setTeam(teamName);
            studentVO.setProCount(projectDao.getCountByStudentId(student.getId()));
            studentVOList.add(studentVO);
        }
        long total = 0;
        if(user.getStatus() == 1){
            total = Integer.toUnsignedLong(studentDao.selectCount(null));
        }
        else if(user.getStatus() == 2){
            List<Integer> teamIdList =  teamDao.getTeamIdByTeacherId(user.getStatusId());
            for (Integer teamId : teamIdList) {
                total +=  studentDao.getCountByTeam(teamId);
            }
        }
        else{
            total = studentDao.getCountByTeam(user.getStatusId());
        }
        return new Result<StudentVO>(0,"",total,studentVOList);
    }
}
