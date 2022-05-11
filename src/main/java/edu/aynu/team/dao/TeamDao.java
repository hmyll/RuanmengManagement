package edu.aynu.team.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.aynu.common.VO.PieVO;
import edu.aynu.team.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeamDao extends BaseMapper<Team> {

    @Select("select id from team where name=#{teamName}")
    Integer findIdByName(String teamName);

    @Select("SELECT t.name name,count(*) value from team t, project p where t.id = p.team_id and p.teacher_admit=1 and p.admin_admit=1 GROUP BY p.team_id ")
    List<PieVO> getBarVO();

    @Select("select name from team where id = #{teamId}")
    String getNameById(Integer teamId);

    @Select("select * from team where id = #{id}")
    Team getTeamById(Integer id);

    @Select("select id from team where teacher_id = #{teacherId}")
    List<Integer> getTeamIdByTeacherId(Integer teacherId);

    @Select("select count(*) from team where teacher_admit = 1 and admin_admit = 1")
    Integer getCountByPassed();

    @Select("select name from team where teacher_id = #{teacherId} and teacher_admit = 1 and admin_admit = 1")
    List<String> getTeamNameByTeacherId(Integer teacherId);
}
