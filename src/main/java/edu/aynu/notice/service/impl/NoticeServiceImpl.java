package edu.aynu.notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.aynu.common.VO.Result;
import edu.aynu.notice.dao.NoticeDao;
import edu.aynu.notice.entity.Notice;
import edu.aynu.notice.service.NoticeService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeDao, Notice> implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Override
    public Result<Notice> findAll(int page, int limit, String searchTitle, String searchContent) {
        LambdaQueryWrapper<Notice> lqm = new LambdaQueryWrapper();
        lqm.like(Strings.isNotEmpty(searchTitle),Notice::getTitle,searchTitle);
        lqm.like(Strings.isNotEmpty(searchContent),Notice::getContent,searchContent);
        IPage<Notice> iPage = noticeDao.selectPage(new Page<Notice>(page ,limit) ,lqm);
        List<Notice> noticeList = iPage.getRecords();
        Collections.sort(noticeList, new Comparator<Notice>(){
            @Override
            public int compare(Notice o1, Notice o2) {
                return (o2.getTime()).compareTo(o1.getTime());
            }
        });
//        for(int i=0;i<noticeList.size();i++){
//            noticeList.get(i).setNewId(i+1);
//        }
        return new Result<Notice>(0,"",Integer.toUnsignedLong(noticeDao.selectCount(null)),noticeList);
    }

    @Override
    public void addNoticeCount(Integer id) {
        noticeDao.addNoticeCount(id);
    }
}
