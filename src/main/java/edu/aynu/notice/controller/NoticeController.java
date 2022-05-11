package edu.aynu.notice.controller;


import edu.aynu.common.VO.DataJson;
import edu.aynu.common.VO.Result;
import edu.aynu.common.utils.UploadUtils;
import edu.aynu.notice.entity.Notice;
import edu.aynu.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("")
    public String toListUI(){
        return "/notice/noticeList";
    }

    @GetMapping("/UserView")
    public String toUserListUI(){
        return "/notice/noticeListForUser";
    }

    @GetMapping("/list")
    @ResponseBody
    public Result<Notice> list(int page, int limit, String searchTitle, String searchContent){
        Result<Notice> result = noticeService.findAll(page,limit,searchTitle,searchContent);
        return result;
    }

    @GetMapping("/check/{id}")
    public String checkNotice(@PathVariable Integer id, Model model){
        //System.out.println(noticeService.getById(id));
        noticeService.addNoticeCount(id);
        Notice notice = noticeService.getById(id);
//        String picture = notice.getPicture();
//        if(picture != null && picture != ""){
//            notice.setPicture("http://localhost:8080/pictures/"+picture);
//        }
        model.addAttribute("notice",notice);
        return "notice/noticeCheck";
    }

    @GetMapping("/add/ui")
    public String toAddUI(Model model){
        return "notice/noticeAdd";
    }

    @RequestMapping("/image")
    @ResponseBody
    public DataJson image(MultipartFile file, HttpSession session){
        //调用工具类完成文件上传
        String imagePath =  UploadUtils.upload(file);
        System.out.println(imagePath);
        DataJson dataJson = new DataJson();
        if (imagePath != null){
            //创建一个HashMap用来存放图片路径
            HashMap hashMap = new HashMap();
            hashMap.put("src",imagePath);
            dataJson.setCode(1);
            dataJson.setMsg("上传成功！");
            dataJson.setData(hashMap);
        }else{
            dataJson.setCode(0);
            dataJson.setMsg("上传失败！");
        }
        return dataJson;
    }

    @PostMapping("")
    @ResponseBody
    public Result<Object> save(Notice notice,HttpSession session){
        notice.setTime(new Date());
        notice.setCount(0);
        //System.out.println(notice);
        noticeService.save(notice);
        return Result.success("新增公告成功！");
    }

    @PostMapping("/deletePicture/{imagePath}")
    @ResponseBody
    public void deletePicture(@PathVariable("imagePath") String imagePath){
        System.out.println("1111111111111111"+imagePath);
        if(imagePath!=null) UploadUtils.delete(imagePath);
    }

    @DeleteMapping("/{ids}")
    @ResponseBody
    public Result<Object> delete(@PathVariable("ids") List<Integer> ids){
        for (Integer id : ids) {
            String pictureName = ((Notice)noticeService.getById(id)).getPicture();
            UploadUtils.delete(pictureName);
        }
        noticeService.removeByIds(ids);
        return Result.success("删除公告成功！");
    }

    @GetMapping("/updateUI/{id}")
    public String updateUI(@PathVariable Integer id,Model model){
        model.addAttribute("notice",noticeService.getById(id));
        return "notice/noticeEdit";
    }

    @PutMapping("/update")
    @ResponseBody
    public Result<Object> updateStudent(Notice notice){
        notice.setTime(new Date());
        noticeService.updateById(notice);
        return Result.success("修改公告成功！");
    }

}
