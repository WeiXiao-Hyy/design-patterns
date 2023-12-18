package com.alipay.shop.designer.composite.visitor;

import com.alipay.shop.designer.composite.AbstractProductItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hyy
 * @Description
 * @create 2023-12-16 22:37
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductComposite extends AbstractProductItem {
    private int id;
    private int pid;
    private String name;
    private List<AbstractProductItem> child = new ArrayList<>();

    @Override
    protected void addProductItem(AbstractProductItem item) {
        child.add(item);
    }

    @Override
    protected void delProductItem(AbstractProductItem item) {
        ProductComposite removeItem = (ProductComposite) item;
        Iterator<AbstractProductItem> iterator = child.iterator();
        while (iterator.hasNext()) {
            ProductComposite composite = (ProductComposite) iterator.next();
            //移除ID相同的商品类目
            if (composite.getId() == removeItem.getId()) {
                iterator.remove();
                break;
            }
        }
    }
}