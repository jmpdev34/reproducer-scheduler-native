package org.acme;

import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ReproducerScheduler {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss");
    Map<String, String> maps = new HashMap<>();

    @Scheduled(cron = "{reproducer.scheduler.cron}")
    public void schedule() {
        LocalDateTime now = LocalDateTime.now();
        String msg = now + " " + Thread.currentThread().getName() + " " + Thread.currentThread().getId();

        String time = dtf.format(now);
        if(!maps.containsKey(time)) {
            maps.put(time, msg);
            System.out.println("Triggered at : " + now);
        } else {
            System.out.println("Already triggered : " + maps.get(time));
        }
    }
}