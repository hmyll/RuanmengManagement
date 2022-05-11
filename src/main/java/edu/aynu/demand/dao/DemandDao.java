package edu.aynu.demand.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.aynu.demand.entity.Demand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DemandDao extends BaseMapper<Demand> {

    @Select("select name from demand where id = #{id}")
    String getNameById(Integer id);

}
