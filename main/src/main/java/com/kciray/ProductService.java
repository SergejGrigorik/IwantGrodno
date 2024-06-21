package com.kciray;

import org.springframework.beans.annotation.Autowired;
import org.springframework.beans.annotation.Component;

@Component
public class ProductService {
    @Autowired

    private PromotionsService promotionsService;
    public ProductService(){}

    public ProductService(PromotionsService promotionsService) {

    }
    public PromotionsService getPromotionsService() {
        return promotionsService;
    }

    public void setPromotionsService(PromotionsService promotionsService) {
        this.promotionsService = promotionsService;
    }




}
