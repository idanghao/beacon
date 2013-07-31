package com.fusionspy.beacon.site.tux;


import com.fusionspy.beacon.report.*;
import com.fusionspy.beacon.site.tux.dao.TuxcltsStatsDao;
import com.sinosoft.one.monitor.attribute.model.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ClientTotalCountReport implements TuxReport
{
    private Attribute attribute;

    @Autowired
    private TuxcltsStatsDao cltsStatsDao;

    @Override
    public ReportResult getStatistic(String resourceId, DateSeries dateSeries) {
        ReportQuery query = dateSeries.getQuery();
        ReportResult reportResult = new ReportResult();
        for(TimePeriod period:query.getPeriods()){
            Statistics statistics =  cltsStatsDao.statisticTotalCountByRectimeBetween(resourceId,
                    new Timestamp(period.getStartDateTime().getMillis()),
                    new Timestamp(period.getEndDateTime().getMillis()));
            statistics.setTimePeriod(period);
            reportResult.addStatistics(statistics);
        }
        reportResult.setStartTime(query.getStartDateTime());
        reportResult.setEndTime(query.getEndDateTime());
        return reportResult;
    }

    @Override
    public Attribute getAttribute() {
        if(attribute == null){
            attribute = new Attribute();
            attribute.setAttribute("CLT_COUNT");
            attribute.setAttributeCn("客户端数量");
            attribute.setUnits("");
        }
        return attribute;
    }
}
