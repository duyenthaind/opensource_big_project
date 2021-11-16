package com.group7.fruitswebsite.dto.update;

import com.group7.fruitswebsite.entity.DhUser;
import org.apache.commons.lang3.StringUtils;

/**
 * @author duyenthai
 */
public class UserUpdateCondition extends UpdateCondition<DhUser> {

    public UserUpdateCondition(DhUser ent) {
        super(ent);
        extractConditions(ent);
        extractSearches(ent);
    }

    @Override
    protected void extractConditions(DhUser ent) {
        conditions.put("email", ent.getEmail());
        conditions.put("password", ent.getPassword());
        conditions.put("avatar", ent.getAvatar());
        conditions.put("name", ent.getName());
        conditions.put("address", ent.getAddress());
    }

    @Override
    protected void extractSearches(DhUser ent) {
        searches.put("username", ent.getUsername());
        searches.put("id", ent.getId());
    }
}
