package edu.aynu.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.aynu.common.VO.Result;
import edu.aynu.project.entity.Project;
import edu.aynu.project.entity.ProjectVO;
import edu.aynu.user.entity.User;

import java.util.List;

public interface ProjectService extends IService<Project> {

    Result<ProjectVO> findMyProject(int page, int limit, String searchName, String searchStudent, User user);

    void saveProject(Project project , List<Integer> idArr);

    List<Integer> selectStudentsByProjectId(Integer id);

    void updateByProjectId(Project project, List<Integer> idArr);

    Result<ProjectVO> findAll(int page, int limit, String searchName, String searchStudent, User user);
}
