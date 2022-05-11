package edu.aynu.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.aynu.common.VO.Result;
import edu.aynu.teacher.entity.Teacher;
import edu.aynu.teacher.entity.TeacherVO;
import edu.aynu.user.entity.User;

public interface TeacherService extends IService<Teacher> {

    Result<TeacherVO> findAll(int page, int limit, String searchName, String searchTeam, User user);
}
