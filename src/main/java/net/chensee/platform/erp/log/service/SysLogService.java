package net.chensee.platform.erp.log.service;

import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.common.HttpClientHelper;
import net.chensee.platform.erp.log.vo.SysLogVo;
import net.chensee.platform.erp.utils.RandomUUIDUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wh
 * @version 1.0
 * @date 2020/3/27 10:24
 */
@Component
public class SysLogService {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Value("${logMsgUrl}")
    private String logMsgUrl;

    public void insertLogManually(SysLogVo sysLogVo) {
        executorService.execute(() -> {
            try {
                HttpClientHelper.sendPost(logMsgUrl+"/log", sysLogVo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
