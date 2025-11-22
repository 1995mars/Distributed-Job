package org.mars.config;

import org.springframework.batch.core.ItemWriteListener;

import java.util.List;
import java.util.Map;

public class LoggingItemWriteListener implements ItemWriteListener<Map<String, Object>> {

    public void beforeWrite(List<? extends Map<String, Object>> items) {
        // Không cần log trước ghi
    }

    public void afterWrite(List<? extends Map<String, Object>> items) {
        // Có thể log số bản ghi ghi thành công
    }

    public void onWriteError(Exception e, List<? extends Map<String, Object>> items) {
        System.err.println("❌ Lỗi khi ghi batch: " + e.getMessage());
    }
}
