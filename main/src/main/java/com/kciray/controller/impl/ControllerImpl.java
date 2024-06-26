package com.kciray.controller.impl;

import com.kciray.controller.ControllerInterface;
import com.kciray.service.ServiceInterface;
import com.kciray.service.impl.ServiceImpl;
import org.springframework.beans.annotation.Autowired;
import org.springframework.beans.annotation.Component;

@Component
public class ControllerImpl implements ControllerInterface {

    private ServiceInterface serviceInterface;

    @Autowired
    public  ControllerImpl(ServiceInterface serviceInterface,String s ,int i,double v){
        this.serviceInterface = serviceInterface;
    }

    public ServiceInterface getServiceInterface() {
        return serviceInterface;
    }

    @Override
    public void execute() {
        serviceInterface.execute();
    }
}
