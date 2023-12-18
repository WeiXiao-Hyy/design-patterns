package com.alipay.shop.designer.composite.controller;

import com.alipay.shop.designer.composite.visitor.ProductComposite;
import com.alipay.shop.designer.composite.service.ProductItemService;
import com.alipay.shop.model.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyy
 * @Description
 * @create 2023-12-17 22:33
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductItemService productItemService;

    @PostMapping("/fetchAllItems")
    public ProductComposite fetchAllItems() {
        return productItemService.fetchAllItems();
    }

    @PostMapping("/addItems")
    public ProductComposite addItems(@RequestBody ProductItem item) {
        return productItemService.addItems(item);
    }

    @PostMapping("/delItems")
    public ProductComposite fetchAllItems(@RequestBody ProductItem item) {
        return productItemService.delItems(item);
    }


}