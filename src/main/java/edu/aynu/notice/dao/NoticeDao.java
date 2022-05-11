package edu.aynu.notice.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.aynu.notice.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NoticeDao extends BaseMapper<Notice> {

    @Update("update notice set count = count + 1 where id = #{id}")
    void addNoticeCount(Integer id);

    @Select("select title from notice where id = #{id}")
    String getNameById(Integer id);
}
