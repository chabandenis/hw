package ru.otus.hw.ex17_user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
public class ApplConfig {
    private static final int THREAD_POOL_SIZE = 5;

    /*
    @Bean(destroyMethod = "close")
    public NioEventLoopGroup eventLoopGroup() {
        return new NioEventLoopGroup(THREAD_POOL_SIZE,
                new ThreadFactory() {
                    private final AtomicLong threadIdGenerator = new AtomicLong(0);

                    @Override
                    public Thread newThread(@NonNull Runnable task) {
                        return new Thread(task, "server-thread-" + threadIdGenerator.incrementAndGet());
                    }
                });
    }
*/
    @Bean
    public Scheduler workerPool() {
        return Schedulers.newParallel("worker-thread", THREAD_POOL_SIZE);
    }
}
