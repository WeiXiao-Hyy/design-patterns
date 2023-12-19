package com.alipay.shop.designer.composite.service;

import com.alipay.shop.designer.composite.AbstractProductItem;
import com.alipay.shop.designer.composite.visitor.AddItemVisitor;
import com.alipay.shop.designer.composite.visitor.DelItemVisitor;
import com.alipay.shop.designer.composite.visitor.ProductComposite;
import com.alipay.shop.model.ProductItem;
import com.alipay.shop.repo.mapper.ProductItemMapper;
import com.alipay.shop.util.RedisCommonProcessor;
import java.util.ArrayList;
import static java.util.Collections.emptyList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @author hyy
 * @Description
 * @create 2023-12-17 22:15
 */
@Service
public class ProductItemService {

    @Autowired
    private ProductItemMapper productItemRepo;

    @Autowired
    private RedisCommonProcessor redisClient;

    @Autowired
    private AddItemVisitor addItemVisitor;

    @Autowired
    private DelItemVisitor delItemVisitor;

    public ProductComposite fetchAllItems() {
        Object cacheItems = redisClient.get("items");
        if (Objects.nonNull(cacheItems)) {
            return (ProductComposite) cacheItems;
        }

        List<ProductItem> fetchDbItems = productItemRepo.findAll();

        //将DB中的商品类目信息拼接成组合模式的树形结构
        ProductComposite items = generateProductTree(fetchDbItems);

        if (Objects.isNull(items)) {
            throw new UnsupportedOperationException("Product items should not be empty in DB");
        }
        redisClient.set("items", items);

        return items;
    }

    /**
     * 增加类目
     *
     * @param item 增加类目{@link ProductItem}
     * @return {@link ProductComposite}
     */
    @Transactional
    public ProductComposite addItems(ProductItem item) {
        //先更新数据库
        productItemRepo.addItem(item.getName(), item.getPid());
        //通过访问者模式访问树形结构，并添加新的商品类目
        ProductComposite addItem = ProductComposite.builder()
                .id(productItemRepo.findByNameAndPid(item.getName(), item.getPid()).getId())
                .name(item.getName())
                .pid(item.getPid())
                .child(new ArrayList<>())
                .build();
        AbstractProductItem updatedItems = addItemVisitor.visitor(addItem);
        //再更新Redis缓存，此处可以做重试机制，如果重试不成功，可以人工介入
        redisClient.set("items", updatedItems);
        return (ProductComposite) updatedItems;
    }

    /**
     * 删除类目
     *
     * @param item 删除类目{@link ProductItem}
     * @return {@link ProductComposite}
     */
    @Transactional
    public ProductComposite delItems(ProductItem item) {

        //通过访问者模式访问树形结构，并删除商品类目
        ProductComposite delItem = ProductComposite.builder()
                .id(item.getId())
                .pid(item.getPid())
                .build();
        AbstractProductItem updatedItems = delItemVisitor.visitor(delItem);

        //先更新数据库
        productItemRepo.delItemsByIds(getDelItemsIds(updatedItems));

        //再更新Redis缓存，此处可以做重试机制，如果重试不成功，可以人工介入
        redisClient.set("items", updatedItems);

        return (ProductComposite) updatedItems;
    }

    private ProductComposite generateProductTree(List<ProductItem> fetchDbItems) {
        List<ProductComposite> composites = new ArrayList<>(fetchDbItems.size());

        fetchDbItems.forEach(dbItem -> composites.add(ProductComposite.builder()
                .id(dbItem.getId())
                .name(dbItem.getName())
                .pid(dbItem.getPid())
                .build()));

        Map<Integer, List<ProductComposite>> groupingList = composites.stream().collect(Collectors.groupingBy(ProductComposite::getPid));

        composites.forEach(item -> {
            List<ProductComposite> list = groupingList.get(item.getId());
            item.setChild(list == null ? new ArrayList<>() : list.stream().map(x -> (AbstractProductItem) x).collect(Collectors.toList()));
        });

        return composites.isEmpty() ? null : composites.get(0);
    }

    /**
     * 获取需要删除的商品类目IdList.
     *
     * @param productItem 需要删除的商品类目
     * @return id集合
     */
    private List<Integer> getDelItemsIds(AbstractProductItem productItem) {
        //获取全量ids
        Set<Integer> productItemIds = productItemRepo.findAll()
                .stream()
                .map(ProductItem::getId)
                .collect(Collectors.toSet());

        //根据父id获取所有子id
        List<Integer> delItemsIds = getProductLeafsByPId(productItem);

        //取补集
        return productItemIds.stream().filter(delItemsIds::contains).toList();
    }

    //根据父id获取所有子id
    private List<Integer> getProductLeafsByPId(AbstractProductItem productItem) {
        //退出条件
        if (Objects.isNull(productItem)) {
            return emptyList();
        }

        ProductComposite delItem = (ProductComposite) productItem;
        List<Integer> res = new ArrayList<>();
        res.add(delItem.getId());

        //空集合
        if (CollectionUtils.isEmpty(delItem.getChild())) {
            return res;
        }

        //dfs
        for (AbstractProductItem abstractItem : delItem.getChild()) {
            res.addAll(getProductLeafsByPId(abstractItem));
        }
        return res;
    }
}