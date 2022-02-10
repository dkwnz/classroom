package work.week05.xmlBeans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        XmlBean xmlBean = context.getBean(XmlBean.class);
        System.out.println(xmlBean);
    }
}
