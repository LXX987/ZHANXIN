package com.huaqi.zhanxin.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.mapper.MetricsMapper;
import com.huaqi.zhanxin.service.MetricsService;
import com.huaqi.zhanxin.tools.OnlineSessionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

import javax.annotation.Resource;

@Slf4j
@Service
public class MetricsServiceImpl implements MetricsService {

    @Resource
    MetricsMapper metricsMapper;

    @Override
    public JSONObject displaySystemInfo()
    {
        return getSystemInfo();
    }

    @Override
    public void logSystemInfo()
    {
        JSONObject jsonObject = getSystemInfo();
        metricsMapper.logSystemInfo((Integer) jsonObject.get("CPU core"), (String) jsonObject.get("CPU %"), (String) jsonObject.get("Memory"), (String) jsonObject.get("Memory %"), OnlineSessionListener.concurrent_user_count, LocalDateTime.now());
    }

    @Override
    public List<JSONObject> getHistory()
    {
        return metricsMapper.getHistory();
    }


    private JSONObject getSystemInfo() {
        JSONObject jsonObject = new JSONObject();
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        long totalByte = memory.getTotal();
        long acaliableByte = memory.getAvailable();

        log.info("CPU总数 = {},CPU利用率 ={}", processor.getLogicalProcessorCount(), new DecimalFormat("#.##%").format(1.0 - (idle * 1.0 / totalCpu)));
        log.info("内存大小 = {},内存使用率 ={}", formatByte(totalByte), new DecimalFormat("#.##%").format((totalByte - acaliableByte) * 1.0 / totalByte));
        jsonObject.put("CPU core", processor.getLogicalProcessorCount());
        jsonObject.put("CPU %", new DecimalFormat("#.##%").format(1.0 - (idle * 1.0 / totalCpu)));
        jsonObject.put("Memory", formatByte(totalByte));
        jsonObject.put("Memory %", new DecimalFormat("#.##%").format((totalByte - acaliableByte) * 1.0 / totalByte));
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
