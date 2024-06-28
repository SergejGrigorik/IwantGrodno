package com.kciray.service.impl;

import com.kciray.dao.DaoInterface;
import com.kciray.service.ServiceInterface;
import org.springframework.annotation.Component;
import org.springframework.postprocessor.autowired.annotation.Autowired;

@Component
public class ServiceImpl implements ServiceInterface {


    private DaoInterface daoInterface;
    private String string;


    @Autowired
    private void setDaoInterface(DaoInterface daoInterface) {
        this.daoInterface = daoInterface;
    }


    @Override
    public void execute() {
        daoInterface.execute();
    }

}
