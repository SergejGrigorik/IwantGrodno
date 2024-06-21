package com.kciray;

import org.springframework.beans.annotation.Component;
import org.springframework.beans.interfaces.BeanNameAware;

@Component
public class PromotionsService implements BeanNameAware {

    private String beanName;


    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
