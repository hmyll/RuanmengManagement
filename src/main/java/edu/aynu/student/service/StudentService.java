package edu.aynu.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.aynu.common.VO.Result;
import edu.aynu.student.entity.Student;
import edu.aynu.student.entity.StudentVO;
import edu.aynu.user.entity.User;

public interface StudentService extends IService<Student> {


    Result<StudentVO> findAll(int page, int limit, String searchName, String searchTeam, User user);
}
