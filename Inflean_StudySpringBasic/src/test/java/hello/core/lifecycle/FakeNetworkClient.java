package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class FakeNetworkClient/* implements InitializingBean, DisposableBean */{

    private String url;

    public FakeNetworkClient() {
        System.out.println("생성자 call url : " + url);
        //connect();
        //call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect call url : " + url);
    }

    public void call(String message) {
        System.out.println("call call url : " + url + "  message : " + message);
    }

    public void disconnect() {
        System.out.println("disconnect call");
    }

    @PostConstruct
    public void init() {
        System.out.println("call init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("call close");
        disconnect();
    }

   /*
   @Override
    public void destroy() throws Exception {
        System.out.println("call destroy");
        disconnect();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("call afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }*/
}
