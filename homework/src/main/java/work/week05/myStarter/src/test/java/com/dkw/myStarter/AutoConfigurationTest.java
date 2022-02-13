package com.dkw.myStarter;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = AutoConfiguration.class)
@ExtendWith(SpringExtension.class)
public class AutoConfigurationTest {
    @Autowired
    School mySchool;

    @Test
    public void test() {
        System.out.println(mySchool.toString());
    }
}