package com.test;

 import org.junit.runner.RunWith;
 import org.springframework.test.context.ContextConfiguration;
 import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

 /**
  *配置spring和junit整合，junit启动时加载springIOC容器 
  */
@RunWith(SpringJUnit4ClassRunner.class)
 //告诉junit spring配置文件
@ContextConfiguration("classpath:applicationContext.xml")                  //我是放在classpath下的，可以根据自己的路径改
public class BaseTest {
       
 }
