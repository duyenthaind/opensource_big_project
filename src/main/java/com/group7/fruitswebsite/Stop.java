package com.group7.fruitswebsite;

import lombok.extern.log4j.Log4j;

/**
 * @author duyenthai
 */
@Log4j
public class Stop {
    public static void main(String[] args) {
        log.info("Stopping application");
        Start.context.close();
    }
}
