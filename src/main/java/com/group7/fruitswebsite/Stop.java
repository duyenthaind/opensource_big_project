package com.group7.fruitswebsite;

import com.group7.fruitswebsite.worker.PoolWorker;
import lombok.extern.log4j.Log4j;

/**
 * @author duyenthai
 */
@Log4j
public class Stop {
    public static void main(String[] args) {
        log.info("Stopping application");
        PoolWorker.stopAllWorker();
        Start.context.close();
    }
}
