package net.chensee.platform.erp.action.busFLowState.event;

import lombok.extern.slf4j.Slf4j;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.log.service.SysLogService;
import net.chensee.platform.erp.log.vo.SysLogVo;
import net.chensee.platform.erp.utils.RandomUUIDUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wh
 * @version 1.0
 * @date 2020/3/26 15:38
 */
@Slf4j
@Component
public class EventHandlerManager implements EventHandler, ApplicationContextAware {

    private String BEAN_NAME = "eventHandlerManager";
    private String REQ_URL = "/busFlow/update/state";
    private String PACKAGE_NAME = "net.chensee.platform.erp.action.busFLowState.event.EventHandlerManager";

    private ApplicationContext applicationContext;

    @Autowired
    private SysLogService sysLogService;

    @Override
    public void doHandler(HttpServletRequest request) {
        Map<String, EventHandler> beans = this.applicationContext.getBeansOfType(EventHandler.class);
        for (Map.Entry<String, EventHandler> e : beans.entrySet()) {
            try {
                if (!BEAN_NAME.equals(e.getKey())) {
                    e.getValue().handler(request);
                }
            } catch (Exception e1) {
                log.error(e.getValue().getClass().getName() + "执行异常！", e1);
                SysLogVo sysLogVo = new SysLogVo();
                sysLogVo.setId(RandomUUIDUtil.randomUUID());
                sysLogVo.setReqUrl(REQ_URL);
                sysLogVo.setPackageName(PACKAGE_NAME);
                sysLogVo.setMethodName(e.getKey());
                sysLogVo.setOprBy(UserUtil.getCurrentUser().getId());
                sysLogVo.setOprName(UserUtil.getCurrentUser().getUsername());
                sysLogVo.setExpContent(e1.toString());
                sysLogVo.setExpTime(System.currentTimeMillis());
                sysLogService.insertLogManually(sysLogVo);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
