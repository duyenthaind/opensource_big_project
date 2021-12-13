package com.group7.fruitswebsite.job;

import com.group7.fruitswebsite.common.Constants;

/**
 * @author duyenthai
 */
public abstract class PoolJob {
    protected Constants.JobType jobType;

    public PoolJob(Constants.JobType jobType) {
        this.jobType = jobType;
    }

    public Constants.JobType getJobType() {
        return jobType;
    }

    public void setJobType(Constants.JobType jobType) {
        this.jobType = jobType;
    }
}
