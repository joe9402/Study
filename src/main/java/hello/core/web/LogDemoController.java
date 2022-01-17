package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    //private final ObjectProvider<MyLogger> myLoggerProvier;
    private final MyLogger myLogger;


    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        //MyLogger myLogger = myLoggerProvier.getObject();
        String requestURL = request.getRequestURI().toString();

        System.out.println("myLogger : " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test1");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
