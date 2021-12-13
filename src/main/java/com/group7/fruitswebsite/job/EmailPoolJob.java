package com.group7.fruitswebsite.job;

import com.group7.fruitswebsite.common.Constants;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author duyenthai
 */
@ToString
public class EmailPoolJob extends PoolJob {
    private String username;
    private String email;
    private Map<String, Object> customs = new HashMap<>();

    public EmailPoolJob(Constants.JobType jobType, String username, String email) {
        super(jobType);
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, Object> getCustoms() {
        return customs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCustoms(Map<String, Object> customs) {
        this.customs = customs;
    }

}
