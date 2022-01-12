package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ScanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        FakeNetworkClient client = ac.getBean(FakeNetworkClient.class);
        ac.close();

    }

    @Configuration
    static class LifeCycleConfig {

        @Bean/*(initMethod = "init", destroyMethod = "close")*/
        public FakeNetworkClient fakeNetworkClient() {
            FakeNetworkClient fakeNetworkClient = new FakeNetworkClient();
            fakeNetworkClient.setUrl("HIHIHI");
            return fakeNetworkClient;
        }
    }
}
