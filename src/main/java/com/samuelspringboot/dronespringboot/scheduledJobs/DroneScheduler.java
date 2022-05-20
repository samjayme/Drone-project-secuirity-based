package com.samuelspringboot.dronespringboot.scheduledJobs;

import com.samuelspringboot.dronespringboot.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
@EnableAsync
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class DroneScheduler {
    @Autowired
    private DroneService droneService;
    private static final Logger log = LoggerFactory.getLogger(DroneScheduler.class);


    @Scheduled(cron= "0 0/60 * * * *")
    @Async
    public void CheckAllDroneBatteryPercent(){

        String DroneBatteryLevel = droneService.checkDroneBatteryLevel();
        log.info("Checking all Drone battery Level",DroneBatteryLevel);
     

    }

}
