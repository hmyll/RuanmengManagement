package edu.aynu.notice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.aynu.common.VO.Result;
import edu.aynu.notice.entity.Notice;

public interface NoticeService extends IService<Notice> {

    Result<Notice> findAll(int page, int limit, String searchTitle, String searchContent);

    void addNoticeCount(Integer id);
}
