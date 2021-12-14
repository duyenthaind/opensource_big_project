package com.group7.fruitswebsite.util;

import com.group7.fruitswebsite.config.ApplicationConfig;
import com.mservice.shared.sharedmodels.Environment;
import com.mservice.shared.sharedmodels.PartnerInfo;

/**
 * @author duyenthai
 */
public class MomoHelper {
    private MomoHelper() {
    }

    // test env only
    public static Environment getMomoEnvironment() {
        PartnerInfo partnerInfo = new PartnerInfo(ApplicationConfig.MOMO_PARTNER_CODE, ApplicationConfig.MOMO_ACCESS_KEY, ApplicationConfig.MOMO_SECRET_KEY);
        return new Environment(ApplicationConfig.MOMO_ENDPOINT, partnerInfo, Environment.EnvTarget.DEV);
    }
}
