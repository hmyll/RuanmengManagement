package edu.aynu.project.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.aynu.project.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectDao extends BaseMapper<Project> {

    @Select("SELECT s.id from student s,project_student ps WHERE s.id = ps.student_id and ps.project_id = #{id}")
    List<Integer> selectStudentsByProjectId(Integer id);

    @Select("select count(*) from project where team_id = #{id} and teacher_admit = 1 and admin_admit = 1")
    Integer selectCountByTeam(Integer id);

    @Select("select name from project where id = #{id}")
    String getNameById(Integer id);

    @Select("select count(*) from project_student ps, project p where p.teacher_admit = 1 and p.admin_admit = 1 and p.id = ps.project_id and ps.student_id = #{id}")
    Integer getCountByStudentId(Integer id);

    @Select("select count(*) from project p where p.teacher_admit=1 and p.admin_admit=1")
    Integer selectPassCount();

}
