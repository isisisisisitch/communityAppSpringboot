package ca.bytetube.communityApp.web.handler;

import ca.bytetube.communityApp.exceptions.ProductOperationException;
import ca.bytetube.communityApp.exceptions.ShopOperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

/**
 * 对单个controller进行异常拦截和处理
 */
@ControllerAdvice //对spring容器扫描到的controller进行拦截
public class GlobalExceptionHandler {
    private final static Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> handle(Exception e) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("success", false);
        if (e instanceof ShopOperationException) {
            modelMap.put("errMsg", e.getMessage());
        } else if (e instanceof ProductOperationException) {
            modelMap.put("errMsg", e.getMessage());
        } else {
            LOG.error("system error", e.getMessage());
            modelMap.put("errMsg", "Unknown error. Contact technical support");
        }
        return modelMap;
    }

}
