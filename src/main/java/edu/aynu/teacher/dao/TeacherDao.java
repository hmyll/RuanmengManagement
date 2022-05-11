package edu.aynu.teacher.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.aynu.teacher.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherDao extends BaseMapper<Teacher> {

    @Select("select name from teacher where id = #{id}")
    String getNameById(Integer id);

    @Select("select teacher_id from team where id = #{teamId}")
    int getIdByTeamId(int teamId);

    @Select("select * from teacher where admin_admit = 1")
    List<Teacher> findPassTeacher();
}
