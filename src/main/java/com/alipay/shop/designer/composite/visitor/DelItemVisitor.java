package com.alipay.shop.designer.composite.visitor;

import com.alipay.shop.designer.composite.AbstractProductItem;
import com.alipay.shop.util.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2023-12-17 23:10
 */
@Component
public class DelItemVisitor implements ItemVisitor<AbstractProductItem> {

    @Autowired
    private RedisCommonProcessor redisProcessor;

    @Override
    public AbstractProductItem visitor(AbstractProductItem productItem) {
        //从Redis中获取当前缓存数据
        ProductComposite currentItem = (ProductComposite) redisProcessor.get("items");
        //需要删除的商品类目
        ProductComposite delItem = (ProductComposite) productItem;

        //不可删除根节点
        if (delItem.getId() == currentItem.getId()) {
            throw new UnsupportedOperationException("root can not be deleted");
        }

        //如果删除的节点为当前节点则直接删除
        if (delItem.getPid() == currentItem.getId()) {
            currentItem.delProductItem(delItem);
            return currentItem;
        }

        delChild(delItem, currentItem);
        return currentItem;
    }

    private void delChild(ProductComposite delItem, ProductComposite currentItem) {
        for (AbstractProductItem abstractItem : currentItem.getChild()) {
            ProductComposite item = (ProductComposite) abstractItem;
            if (delItem.getPid() == item.getId()) {
                item.delProductItem(delItem);
                break;
            } else {
                delChild(delItem, item);
            }
        }
    }
}