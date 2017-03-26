package com.iclass.ppt_hw.config.file;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/26/2017 8:12 PM.
 */
@Component
@ConfigurationProperties(prefix = "file")
public class FileConfig {

    private String filePath;

    private Long maxSize;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Long maxSize) {
        this.maxSize = maxSize;
    }
}
