package com.alipay.shop.designer.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hyy
 * @Description
 * @create 2024-01-09 11:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalTicket implements Cloneable {

    //发票固定不变的信息
    private String finalInfo;

    private String title;

    private String product;

    private String content;

    @Override
    public PersonalTicket clone() {
        try {
            PersonalTicket clone = (PersonalTicket) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}