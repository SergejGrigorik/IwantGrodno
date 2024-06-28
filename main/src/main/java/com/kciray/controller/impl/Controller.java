package com.kciray.controller.impl;

import com.kciray.service.ServiceInterface;
import org.springframework.annotation.Component;
import org.springframework.postprocessor.autowired.annotation.Autowired;

@Component
public class Controller {

    private ServiceInterface serviceInterface;

    @Autowired
    public Controller(ServiceInterface serviceInterface, String s, int i, double v) {
        this.serviceInterface = serviceInterface;
    }

    public ServiceInterface getServiceInterface() {
        return serviceInterface;
    }


    public void execute() {
        serviceInterface.execute();
    }
}
