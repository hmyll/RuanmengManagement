package edu.aynu.demand.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.aynu.common.VO.Result;
import edu.aynu.demand.entity.Demand;
import edu.aynu.demand.entity.DemandVO;
import edu.aynu.user.entity.User;

public interface DemandService extends IService<Demand> {

    Result<DemandVO> findAll(int page, int limit, String searchTitle, String searchRequest, User user);

}
