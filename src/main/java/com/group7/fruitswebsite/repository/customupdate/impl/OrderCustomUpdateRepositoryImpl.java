package com.group7.fruitswebsite.repository.customupdate.impl;

import com.group7.fruitswebsite.dto.update.OrderUpdateCondition;
import com.group7.fruitswebsite.entity.DhOrder;
import com.group7.fruitswebsite.repository.customupdate.OrderCustomUpdateRepository;
import com.group7.fruitswebsite.util.QueryGeneratorUtil;
import com.group7.fruitswebsite.util.QueryHelper;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @author duyenthai
 */
@Log4j
@Transactional
public class OrderCustomUpdateRepositoryImpl implements OrderCustomUpdateRepository {

    private EntityManager entityManager;

    @Override
    public void customUpdate(DhOrder entity) {
        try{
            OrderUpdateCondition condition = new OrderUpdateCondition(entity);
            String sqlQuery = QueryGeneratorUtil.generateQueryUpdate(condition.getConditions(), condition.getSearches(), "dh_order");
            log.info(String.format("Update with sql_query %s", sqlQuery));
            QueryHelper.replaceConditionAndQueryUpdate(condition.getConditions(), condition.getSearches(), sqlQuery, entityManager, DhOrder.class);
        } catch (Exception ex){
            log.error("Custom update error, ", ex);
        }
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
