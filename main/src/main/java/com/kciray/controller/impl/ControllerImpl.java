package com.kciray.controller.impl;

import com.kciray.controller.ControllerInterface;
import com.kciray.service.ServiceInterface;
import com.kciray.service.impl.ServiceImpl;
import org.springframework.beans.annotation.Autowired;
import org.springframework.beans.annotation.Component;

@Component
public class ControllerImpl implements ControllerInterface {
        private ServiceInterface serviceInterface;

    public ServiceInterface getServiceInterface() {
        return serviceInterface;
    }

@Autowired
    public void setServiceInterface (ServiceInterface serviceInterface) {
        this.serviceInterface = serviceInterface;
    }
//    @Autowired
//    private ServiceImpl serviceImpl;
//
//    @Autowired
//    public void setServiceImpl(ServiceImpl serviceImpl) {
//        this.serviceImpl = serviceImpl;
//    }
//
//    public ServiceImpl getServiceImpl() {
//        return serviceImpl;
//    }
//
    @Autowired
    public ControllerImpl(ServiceInterface serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    @Override
    public void execute() {
//        serviceInterface.execute();
    }
}
