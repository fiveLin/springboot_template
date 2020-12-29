//package com.example.demo.config;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.server.standard.ServerEndpointRegistration;
//
///**
// * 时光之里山南水北,你我之间人山人海
// * <p>
// *
// * @author 折骨为刀
// * @create 2019-03-07 下午 02:32
// **/
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void registerStompEndpoints (StompEndpointRegistry stompEndpointRegistry) {
//        stompEndpointRegistry.addEndpoint("/endpointSang").setAllowedOrigins ("*").withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.enableSimpleBroker("/edu/");
//    }
//
//    @Bean
//    public MyEndpointConfigure newConfigure() {
//        return new MyEndpointConfigure();
//    }
//}
//
//class MyEndpointConfigure extends ServerEndpointRegistration.Configurator implements ApplicationContextAware {
//
//    private static volatile BeanFactory context;
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        MyEndpointConfigure.context = applicationContext;
//    }
//
//    @Override
//    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
//        return context.getBean(clazz);
//    }
//}