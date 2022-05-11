package edu.aynu.project.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.aynu.project.entity.ProjectStudent;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectStudentDao extends BaseMapper<ProjectStudent> {


    @Select("select * from project_student where project_id = #{projectId}")
    List<ProjectStudent> selectListByProjectId(Integer projectId);

    @Delete("delete from project_student where project_id = #{id}")
    void deleteByProjectId(int id);
}
