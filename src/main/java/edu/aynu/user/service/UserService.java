package edu.aynu.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.aynu.common.VO.PieVO;
import edu.aynu.common.VO.Result;
import edu.aynu.user.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    public User Login(User user);

    List<PieVO> getPieVO();

    int findLoginNameCount(User user);

    Result<Object> updatePassword(String oldPassword, String newPassword,int userId);

    Result<User> findAll(int page, int limit, String searchName);

    int findLoginNameCount(String loginname);
}
