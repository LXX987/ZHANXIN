package com.huaqi.zhanxin.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

import cn.hutool.json.JSONObject;
import cn.hutool.system.oshi.CpuInfo;
import cn.hutool.system.oshi.OshiUtil;
import com.huaqi.zhanxin.mapper.MetricsMapper;
import com.huaqi.zhanxin.service.MetricsService;
import com.huaqi.zhanxin.tools.OnlineSessionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

import javax.annotation.Resource;

@Slf4j
@Service
@EnableScheduling
public class MetricsServiceImpl implements MetricsService {

    @Resource
    MetricsMapper metricsMapper;

    @Override
    public JSONObject displaySystemInfo()
    {
        return getSystemInfo();
    }

    @Override
    @Scheduled(fixedDelay = 30*60*1000)
    public void logSystemInfo()
    {
        JSONObject jsonObject = getSystemInfo();
        metricsMapper.logSystemInfo((Integer) jsonObject.get("cpuCore"), (String) jsonObject.get("cpuUsage"), (String) jsonObject.get("memory"), (String) jsonObject.get("memoryUsage"), OnlineSessionListener.concurrent_user_count, LocalDateTime.now());
    }

    @Override
    public List<JSONObject> getHistory()
    {
        return metricsMapper.getHistory();
    }


    private JSONObject getSystemInfo() {
        JSONObject jsonObject = new JSONObject();

        CpuInfo cpuInfo = OshiUtil.getCpuInfo();
        double freeCPU = cpuInfo.getFree();

        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();

        GlobalMemory memory = systemInfo.getHardware().getMemory();
        long totalByte = memory.getTotal();
        long acaliableByte = memory.getAvailable();

        log.info("CPU总数 = {},CPU利用率 ={}", processor.getLogicalProcessorCount(), new DecimalFormat("#.##%").format((100.0D - freeCPU)/100.0D));
        log.info("内存大小 = {},内存使用率 ={}", formatByte(totalByte), new DecimalFormat("#.##%").format((totalByte - acaliableByte) * 1.0 / totalByte));
        jsonObject.put("cpuCore", processor.getLogicalProcessorCount());
        jsonObject.put("cpuUsage", new DecimalFormat("#.##%").format((100.0D - freeCPU)/100.0D));
        jsonObject.put("memory", formatByte(totalByte));
        jsonObject.put("memoryUsage", new DecimalFormat("#.##%").format((totalByte - acaliableByte) * 1.0 / totalByte));
        return jsonObject;
    }

    private static String formatByte(long byteNumber) {
        double FORMAT = 1024.0;
        double kbNumber = byteNumber / FORMAT;
        if (kbNumber < FORMAT) {
            return new DecimalFormat("#.##KB").format(kbNumber);
        }
        double mbNumber = kbNumber / FORMAT;
        if (mbNumber < FORMAT) {
            return new DecimalFormat("#.##MB").format(mbNumber);
        }
        double gbNumber = mbNumber / FORMAT;
        if (gbNumber < FORMAT) {
            return new DecimalFormat("#.##GB").format(gbNumber);
        }
        double tbNumber = gbNumber / FORMAT;
        return new DecimalFormat("#.##TB").format(tbNumber);
    }

}
