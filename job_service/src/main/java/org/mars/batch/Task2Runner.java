package org.mars.batch;

import org.mars.job.InterruptableTask;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

//com.mars.OneCV.batch.TestTask
@Component
public class Task2Runner implements TaskRunner {
//    private void birthdayBatch() throws InterruptedException {
//        System.out.println("Birthday Employee;");
//        Thread.sleep(10000);
//        System.out.println("Birthday Employee End;");
//    }

    public void runTask(Supplier<Boolean> isStopped) throws InterruptedException {
        System.out.println("Task2Runner;");

        try {
            for (int i = 0; i < 10; i++) {
                // Nếu job bị ngắt, thoát ngay
                if (InterruptableTask.isInterrupted() || Thread.interrupted()) {
                    System.out.println("❌ Job bị dừng giữa chừng!");
                    return;
                }

                Thread.sleep(10000);
                System.out.println("Đang xử lý " + (i + 1) + "...");
            }
        } catch (InterruptedException e) {
            System.out.println("❌ Thread bị ngắt khi sleep!");
            Thread.currentThread().interrupt();
            return;
        }

        System.out.println("Task2Runner;");
    }
}
