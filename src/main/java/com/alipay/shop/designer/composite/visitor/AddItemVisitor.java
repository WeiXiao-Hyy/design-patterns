package com.alipay.shop.designer.composite.visitor;

import com.alipay.shop.designer.composite.AbstractProductItem;
import com.alipay.shop.util.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2023-12-17 22:54
 */

@Component
public class AddItemVisitor implements ItemVisitor<AbstractProductItem> {

    @Autowired
    private RedisCommonProcessor redisProcessor;

    @Override
    public AbstractProductItem visitor(AbstractProductItem productItem) {

        //从Redis中获取当前缓存数据
        ProductComposite currentItem = (ProductComposite) redisProcessor.get("items");
        //需要新增的商品类目
        ProductComposite addItem = (ProductComposite) productItem;
        //如果新增的节点的父节点是当前节点则直接添加
        if (addItem.getPid() == currentItem.getId()) {
            currentItem.addProductItem(addItem);
            return currentItem;
        }
        addChild(addItem, currentItem);
        return currentItem;
    }

    /**
     * 递归寻找新增类目的插入点
     *
     * @param addItem     插入点
     * @param currentItem 当前节点
     */
    private void addChild(ProductComposite addItem, ProductComposite currentItem) {
        for (AbstractProductItem abstractItem : currentItem.getChild()) {
            ProductComposite item = (ProductComposite) abstractItem;
            if (addItem.getPid() == item.getId()) {
                item.addProductItem(addItem);
                break;
            } else {
                addChild(addItem, item);
            }
        }
    }
}