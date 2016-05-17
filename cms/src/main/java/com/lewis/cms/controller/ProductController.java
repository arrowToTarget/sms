package com.lewis.cms.controller;

import com.alibaba.fastjson.JSON;
import com.lewis.cms.common.annotation.TspServiceInfo;
import com.lewis.cms.domain.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product")
@Scope("prototype")
public class ProductController {


    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    @TspServiceInfo(name = "SMS.CMS.ProductController.queryProduct",description = "query detail product")
    public String queryProduct(){
        Product product = new Product(100,"java effictive",100,"tuling");
        String jsonStrign = JSON.toJSONString(product);
        System.out.println(jsonStrign);
        return jsonStrign;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    @TspServiceInfo(name = "SMS.CMS.ProductController.updateProduct",description = "update product")
    public String updateProduct(Product product){
        Product product1 = new Product(100,"java effictive",100,"tuling");
        String jsonStrign = JSON.toJSONString(product1);
        System.out.println(jsonStrign);
        return jsonStrign;
    }

}
