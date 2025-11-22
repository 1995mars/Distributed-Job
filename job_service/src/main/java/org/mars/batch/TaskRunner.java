package org.mars.batch;

import java.util.function.Supplier;

public interface TaskRunner {
    void runTask(Supplier<Boolean> isStopped) throws InterruptedException;
}