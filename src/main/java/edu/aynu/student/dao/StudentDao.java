package edu.aynu.student.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.aynu.student.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentDao extends BaseMapper<Student> {

    @Select("select count(*) from student where team_id=#{teamId}")
    Integer getCountByTeam(Integer teamId);

    @Select("select * from student where team_id=#{teamId}")
    List<Student> getListByTeam(Integer teamId);

    @Select("select name from student where id = #{id}")
    String getNameById(Integer id);

    @Select("select * from student where stu_id = #{stuId}")
    Student findByStuId(Integer stuId);

}
