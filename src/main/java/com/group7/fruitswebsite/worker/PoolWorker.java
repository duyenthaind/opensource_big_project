package com.group7.fruitswebsite.worker;

import com.group7.fruitswebsite.job.PoolJob;
import lombok.extern.log4j.Log4j;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author duyenthai
 */
@Log4j
public abstract class PoolWorker extends Thread {
    private static final Map<String, List<PoolWorker>> ALL_WORKERS = new ConcurrentHashMap<>();
    private static final Random RANDOM = new Random();

    protected final BlockingQueue<PoolJob> queue = new LinkedBlockingQueue<>();
    protected AtomicBoolean isRunning = new AtomicBoolean(true);

    protected PoolWorker(String name) {
        super(name);
        registerWorker();
        log.info(String.format("Worker %s has started ! ", name));
    }

    private List<PoolWorker> getWorkersForClass(Class clazz) {
        synchronized (ALL_WORKERS) {
            List<PoolWorker> allWorkerFromClass = ALL_WORKERS.get(clazz.getName());
            if (allWorkerFromClass == null || allWorkerFromClass.isEmpty()) {
                allWorkerFromClass = new CopyOnWriteArrayList<>();
                ALL_WORKERS.put(clazz.getName(), allWorkerFromClass);
            }
            return allWorkerFromClass;
        }
    }

    private void registerWorker() {
        List<PoolWorker> poolWorkers = getWorkersForClass(this.getClass());
        poolWorkers.add(this);
    }

    public void stopWorker() {
        this.isRunning.set(false);
    }

    public static void stopAllWorker() {
        synchronized (ALL_WORKERS) {
            for (Map.Entry<String, List<PoolWorker>> entry : ALL_WORKERS.entrySet()) {
                entry.getValue().forEach(PoolWorker::stopWorker);
            }
        }
    }

    public static void pubJob(PoolJob job, Class clazz) {
        List<PoolWorker> allWorkerForClass = ALL_WORKERS.get(clazz.getName());
        if (!allWorkerForClass.isEmpty()) {
            int index = RANDOM.nextInt(allWorkerForClass.size());
            PoolWorker worker = allWorkerForClass.get(index);
            try {
                worker.queue.put(job);
            } catch (Exception ex) {
                log.error(String.format("Put job to worker error, job details %s, worker %s", job, clazz.getName()), ex);
            }
        }
    }

}
