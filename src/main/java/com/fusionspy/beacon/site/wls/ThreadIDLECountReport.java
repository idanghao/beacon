package com.fusionspy.beacon.site.wls;

import com.fusionspy.beacon.report.StatisticForwardReport;
import com.fusionspy.beacon.report.Statistics;
import com.fusionspy.beacon.site.wls.dao.WlsThreadDao;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.sinosoft.one.monitor.attribute.model.Attribute;
import com.sinosoft.one.monitor.common.ResourceType;
import com.sinosoft.one.util.date.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.util.Map;

@Service
public class ThreadIDLECountReport extends StatisticForwardReport implements WlsReport {

    private Attribute attribute;

    @Autowired
    private WlsThreadDao wlsThreadDao;

    @Override
    public Map<String, Statistics> getStatistic(String resourceId, DateTime startDate, DateTime endDate) {
        String start = DateUtils.toFormatString(startDate.toDate(), DateUtils.Formatter.YEAR_TO_SECOND);
        String end = DateUtils.toFormatString(endDate.toDate(), DateUtils.Formatter.YEAR_TO_SECOND);
        return Maps.uniqueIndex(wlsThreadDao.statisticIdleCount(resourceId, new Timestamp(startDate.getMillis()), new Timestamp(endDate.getMillis())),
                new Function<Statistics, String>() {
                    @Nullable
                    @Override
                    public String apply(@Nullable Statistics input) {
                        return input.getName();
                    }
                });
    }

    @Override
    public Attribute getAttribute() {
        if (attribute == null) {
            attribute = new Attribute();
            attribute.setAttribute("THREAD_IDLE_COUNT");
            attribute.setAttributeCn("空闲线程数");
            attribute.setResourceType(ResourceType.WEBLOGIC);
            attribute.setUnits("个");
        }
        return attribute;
    }
}
