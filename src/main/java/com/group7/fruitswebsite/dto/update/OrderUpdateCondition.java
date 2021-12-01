package com.group7.fruitswebsite.dto.update;

import com.group7.fruitswebsite.entity.DhOrder;

/**
 * @author duyenthai
 */
public class OrderUpdateCondition extends UpdateCondition<DhOrder> {
    public OrderUpdateCondition(DhOrder ent) {
        super(ent);
        extractConditions(ent);
        extractSearches(ent);
    }

    @Override
    protected void extractConditions(DhOrder ent) {
        conditions.put("is_prepaid", Boolean.TRUE.equals(ent.getIsPrepaid()) ? 1 : 0);
        conditions.put("order_status", ent.getOrderStatus());
    }

    @Override
    protected void extractSearches(DhOrder ent) {
        searches.put("id", ent.getId());
    }
}
