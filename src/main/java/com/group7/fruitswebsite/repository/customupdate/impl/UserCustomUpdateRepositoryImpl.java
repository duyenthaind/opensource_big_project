package com.group7.fruitswebsite.repository.customupdate.impl;

import com.group7.fruitswebsite.dto.update.UserUpdateCondition;
import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.repository.customupdate.UserCustomUpdateRepository;
import com.group7.fruitswebsite.util.QueryGeneratorUtil;
import com.group7.fruitswebsite.util.QueryHelper;
import lombok.extern.log4j.Log4j;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author duyenthai
 */
@Log4j
@Transactional
public class UserCustomUpdateRepositoryImpl implements UserCustomUpdateRepository {

    private EntityManager entityManager;

    @Override
    public void customUpdate(DhUser entity) {
        try{
            UserUpdateCondition condition = new UserUpdateCondition(entity);
            String sqlQuery = QueryGeneratorUtil.generateQueryUpdate(condition.getConditions(), condition.getSearches(), "dh_user");
            log.info(String.format("Update with sql_query %s", sqlQuery));
            QueryHelper.replaceConditionAndQueryUpdate(condition.getConditions(), condition.getSearches(), sqlQuery, entityManager, DhUser.class);
        } catch (Exception ex){
            log.error("Custom update error, ", ex);
        }
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
