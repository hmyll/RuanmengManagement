package edu.aynu.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.aynu.common.VO.PieVO;
import edu.aynu.common.VO.Result;
import edu.aynu.user.dao.UserDao;
import edu.aynu.user.entity.User;
import edu.aynu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {


    @Autowired
    public UserDao userDao;

    @Override
    public User Login(User user) {
        final User user1 = userDao.findByLoginNameAndPassword(user);
        if(user1 == null) return null;
        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(user.getPassword(),user1.getPassword()) == true){
            return user1;
        }
        return null;
    }


    @Override
    public List<PieVO> getPieVO()   {
        List<PieVO> teamChartVOList = userDao.findTeamNameAndNum();
        return teamChartVOList;
    }


    @Override
    public int findLoginNameCount(User user) {
        return userDao.findByLonginName(user.getId(),user.getLoginName());
    }



    @Override
    public Result<Object> updatePassword(String oldPassword, String newPassword,int userId) {
        String password = userDao.selectById(userId).getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(oldPassword,password) == false){
            return Result.fail("旧的密码错误，请重新输入！");
        }else {
            userDao.updatePasswordById(passwordEncoder.encode(newPassword),userId);
            return Result.success("修改密码成功，请重新登录！");
        }
    }

    @Override
    public Result<User> findAll(int page, int limit, String searchName) {
        LambdaQueryWrapper<User> lqm = new LambdaQueryWrapper();
        //lqm.like(Strings.isNotEmpty(searchName),User::getName,searchName);
        //lqm.like(User::getAdmin,0);
        IPage<User> iPage = userDao.selectPage(new Page<User>(page ,limit) ,lqm);
        List<User> userList = iPage.getRecords();
        Integer count = userDao.selectCount(null);
        return new Result<User>(0,"",Integer.toUnsignedLong(count),userList);
    }

    @Override
    public int findLoginNameCount(String loginname) {
        return userDao.findLonginNameCount(loginname);
    }


}
