package org.mars.batch;

import org.springframework.stereotype.Component;

import java.util.function.Supplier;

//com.mars.OneCV.batch.TestTask
@Component
public class Task1Runner implements TaskRunner {
//    private void birthdayBatch() throws InterruptedException {
//        System.out.println("Birthday Employee;");
//        Thread.sleep(10000);
//        System.out.println("Birthday Employee End;");
//    }

    public void runTask(Supplier<Boolean> isStopped) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            if (isStopped.get()) {
                System.out.println("Task1 stopped externally");
                return;
            }
            System.out.println("Task1 running step " + i);
            Thread.sleep(10000);
        }
    }
}
