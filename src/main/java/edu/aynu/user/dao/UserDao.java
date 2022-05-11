package edu.aynu.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.aynu.common.VO.PieVO;
import edu.aynu.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {

    @Select("select * from user where login_name = #{loginName}")
    public User findByLoginNameAndPassword(User user);

    @Select("SELECT t.name name,count(s.team_id) value from student s, team t  where s.team_id = t.id and t.teacher_admit = 1 and t.admin_admit =1  GROUP BY s.team_id")
    public List<PieVO> findTeamNameAndNum();

    @Select("SELECT COUNT(*) FROM user WHERE  login_name = #{loginname} and id != #{id} ")
    public int findByLonginName(@Param("id")int id, @Param("loginname")String loginname);

    @Update("update user set password = #{password} where id = #{id}")
    void updatePasswordById(@Param("password") String password,@Param("id") int id);

    @Select("select * from user where admin = 0")
    List<User> findUserListByNotAdmin();

    @Select("SELECT COUNT(*) FROM user WHERE  login_name = #{loginname} ")
    int findLonginNameCount(String loginname);

    @Select("select name from user where id = #{id}")
    String getNameById(Integer id);
}
