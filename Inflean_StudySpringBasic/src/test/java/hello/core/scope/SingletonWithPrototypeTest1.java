package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test
    void protoTypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        Assertions.assertThat(bean1.getCount()).isEqualTo(1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        Assertions.assertThat(bean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean bean1 = ac.getBean(ClientBean.class);
        int count = bean1.logic();
        Assertions.assertThat(count).isEqualTo(1);

        ClientBean bean2 = ac.getBean(ClientBean.class);
        count = bean2.logic();
        Assertions.assertThat(count).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean {
        private final PrototypeBean prototypeBean;

        //@Autowired
        //ApplicationContext applicationContext;

        //@Autowired
        //private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {

            /*prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;*/

            /*PrototypeBean bean = applicationContext.getBean(PrototypeBean.class);
            bean.addCount();
            int count = bean.getCount();
            return count;*/

            /*PrototypeBean bean = prototypeBeanProvider.getObject();
            bean.addCount();
            int count = bean.getCount();
            return count;*/

            PrototypeBean bean = prototypeBeanProvider.get();
            bean.addCount();
            int count = bean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean init : "  + this);
        }

        @PreDestroy
        public void destroy () {
            System.out.println("PrototypeBean destroy : "  + this);
        }
    }
}
