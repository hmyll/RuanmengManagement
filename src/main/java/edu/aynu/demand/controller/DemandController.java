package edu.aynu.demand.controller;


import edu.aynu.common.VO.Result;
import edu.aynu.demand.entity.Demand;
import edu.aynu.demand.entity.DemandVO;
import edu.aynu.demand.service.DemandService;
import edu.aynu.team.service.TeamService;
import edu.aynu.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/demand")
public class DemandController {

    @Autowired
    private DemandService demandService;

    @Autowired
    private TeamService teamService;

    @GetMapping("")
    public String toListUI(){
        return "/demand/demandListForAdmin";
    }

    @GetMapping("/teamView")
    public String toListUIForUser(Model model){
        return "/demand/demandListForTeam";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Result<DemandVO> list(int page, int limit, String searchTitle, String searchRequest, HttpSession session){
        User user = ((User)session.getAttribute("userInfo"));
        Result<DemandVO> result = demandService.findAll(page,limit,searchTitle,searchRequest,user);
        System.out.println(result);
        return result;
    }

    @GetMapping("/addUI")
    public String toAddUI(Model model){
        model.addAttribute("teamList",teamService.list());
        return "demand/demandAdd";
    }

    @PostMapping()
    @ResponseBody
    public Result<Object> save(HttpSession session, Demand demand){
        User user = ((User)session.getAttribute("userInfo")); //用户只能看到自己上传的
        demand.setTeamId(user.getStatusId());
        demand.setRequestTime(new Date());
        demandService.save(demand);
        return Result.success("提交留言成功！");
    }

    @GetMapping("/check/{id}")
    public String check(Model model,@PathVariable Integer id){
        model.addAttribute("teamList",teamService.list());
        model.addAttribute("demand",demandService.getById(id));
        return "demand/demandCheck";
    }

    @DeleteMapping("/{ids}")
    @ResponseBody
    public Result<Object> delete(@PathVariable("ids") List<Integer> ids){
        demandService.removeByIds(ids);
        return Result.success("删除留言成功！");
    }

    @GetMapping("/updateUI/{id}")
    public String updateUI(@PathVariable Integer id,Model model){
        model.addAttribute("teamList",teamService.list());
        model.addAttribute("demand",demandService.getById(id));
        return "demand/demandEdit";
    }

    @PutMapping("/update")
    @ResponseBody
    public Result<Object> updateProject(Demand demand,HttpSession session){
        final User user = (User) session.getAttribute("userInfo");
        if(user.getStatus() == 1){
            demand.setResponseTime(new Date());
            //demand.setAdminId(user.getId());
            demandService.updateById(demand);
            return Result.success("回复留言成功！");
        }
        else {
            demand.setRequestTime(new Date());
            demandService.updateById(demand);
            return Result.success("修改留言成功！");
        }
    }

}
