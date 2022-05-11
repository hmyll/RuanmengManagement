package edu.aynu.team.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.aynu.common.VO.BarVO;
import edu.aynu.common.VO.Result;
import edu.aynu.team.entity.Team;
import edu.aynu.team.entity.TeamVO;
import edu.aynu.user.entity.User;

public interface TeamService extends IService<Team> {

    Result<TeamVO> findAll(int page, int limit, String searchName, User user);

    BarVO getBarVO();

    Result<TeamVO> findAllPass(int page, int limit, String searchName);
}
