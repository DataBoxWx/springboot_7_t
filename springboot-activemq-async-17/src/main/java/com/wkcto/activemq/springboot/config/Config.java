package com.wkcto.activemq.springboot.config;

import com.wkcto.activemq.springboot.listener.MyMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

/**
 * ClassName:Config
 * package:com.wkcto.activemq.springboot.config
 * Description:
 *
 * @Data:2018/8/5 11:17
 * @Auther:wangxin
 */
@Configuration
public class Config {
    @Value("${spring.jms.template.default-destination}")
    private String destination;
    @Autowired
    private ConnectionFactory connectionFactory;

    @Value("${spring.jms.pub-sub-domain}")
    private  Boolean subdomain;

    @Autowired
    private MyMessageListener myMessageListener;


    /**
     *
     1、
     <!-- 配置一个连接工厂 -->
     <bean id="connectionFactory" class="org.apache.activemq.ActiveMQConnectionFactory">
     <property name="brokerURL" value="tcp://192.168.160.128:61616"/>
     </bean>

     2、
     <!-- 配置一个sping JMS 监听器的容器 -->
     <bean class="org.springframework.jms.listener.DefaultMessageListenerContainer">
     <property name="connectionFactory" ref="connectionFactory"/>
     <property name="destinationName" value="springTopic"/>
     <property name="messageListener" ref="receiverListener" />
     <!--发送模式，true表示发布订阅模式，false表示点对点模式，默认是false所以默认是点对点模式-->
     <property name="pubSubDomain" value="true"/>
     </bean>

     3、
     <!--自定义的消息监听器-->
     <bean id="receiverListener" class="com.bjpowernode.activemq.listener.MyMessageListener"/>
     *
     */

    @Bean
    public DefaultMessageListenerContainer getMessageListener(){
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
        defaultMessageListenerContainer.setDestinationName(destination);
        defaultMessageListenerContainer.setMessageListener(myMessageListener);
        defaultMessageListenerContainer.setPubSubDomain(subdomain);

        return defaultMessageListenerContainer;
    }
}
