package edu.aynu.demand.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.aynu.common.VO.Result;
import edu.aynu.demand.dao.DemandDao;
import edu.aynu.demand.entity.Demand;
import edu.aynu.demand.entity.DemandVO;
import edu.aynu.demand.service.DemandService;
import edu.aynu.team.dao.TeamDao;
import edu.aynu.user.dao.UserDao;
import edu.aynu.user.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DemandServiceImpl extends ServiceImpl<DemandDao, Demand> implements DemandService {

    @Autowired
    private DemandDao demandDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Result<DemandVO> findAll(int page, int limit, String searchTitle, String searchRequest, User user) {
        LambdaQueryWrapper<Demand> lqm = new LambdaQueryWrapper();
        lqm.like(Strings.isNotEmpty(searchTitle),Demand::getTitle,searchTitle);
        lqm.like(Strings.isNotEmpty(searchRequest),Demand::getRequest,searchRequest);
        IPage<Demand> iPage = demandDao.selectPage(new Page<Demand>(page ,limit) ,lqm);
        List<Demand> demandList = iPage.getRecords();
        List<DemandVO> demandVOList = new ArrayList<>();
        for (Demand demand : demandList) {
            DemandVO demandVO = new DemandVO();
            //if(userId != -1 && demand.getUserId() != userId) continue;  //用户只能查看自己提交的请求
            BeanUtils.copyProperties(demand,demandVO);
            demandVO.setTeam(teamDao.getNameById(demandVO.getTeamId()));
            //demandVO.setAdmin(userDao.getNameById(demandVO.getAdminId()));
            //demandVO.setUser(userDao.getNameById(demandVO.getUserId()));
            demandVO.setStatusId(user.getStatusId());
            demandVOList.add(demandVO);
        }
        Collections.reverse(demandVOList);
        return new Result<DemandVO>(0,"",Integer.toUnsignedLong(demandDao.selectCount(null)),demandVOList);
    }
}
