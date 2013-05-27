package com.fusionspy.beacon.site;

import com.fusionspy.beacon.system.entity.SiteListEntity;
import com.fusionspy.beacon.system.service.SystemService;
import com.sinosoft.one.util.thread.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * monitor manage
 * User: qc
 * Date: 11-9-13
 * Time: 下午8:49
 */
@Component(value = "monitorManage")
public class MonitorManage {

    private static Logger logger = LoggerFactory.getLogger(MonitorManage.class);

    private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private DateFormat df = new SimpleDateFormat(DATE_FORMAT);

    private static final int CORE_POOL_SIZE = 20;

    private AlertMessage alertMessage = new AlertMessage();

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(CORE_POOL_SIZE, new ThreadUtils.CustomizableThreadFactory("tux"));

    @Autowired
    private SitesHolder sitesHolder;

    @Autowired
    private SystemService systemService;

    private SiteInfoLogger siteInfoLogger = SiteInfoLogger.getInstance();

    @PostConstruct
    public void init(){

      for(SiteListEntity siteListEntity : systemService.getSites()){
          monitor(siteListEntity.getSiteName());
      }
    }

    /**
     * start monitor
     *
     * @param siteName
     */
    public void monitor(String siteName) {
        MonitorSite monitorSite = sitesHolder.getMonitorSite(siteName);
        if (!monitorSite.isRunning()) {
            monitor(monitorSite);
            //logger.info("Site Name: {} start now", siteName);
            String msg = alertMessage.getMessage(AlarmMessageFormat.START,df.format(new Date()),siteName);
            logger.info(msg);
            siteInfoLogger.logInf(siteName,msg);
        } else {
            logger.info("Site Name: {} has started, so do nothing", siteName);
        }

    }


    private void monitor(MonitorSite monitorSite) {
        ScheduledFuture scheduledFuture = executorService.scheduleAtFixedRate(monitorSite.start(), 0, monitorSite.getPeriod(), TimeUnit.SECONDS);
        monitorSite.setScheduledFuture(scheduledFuture);
    }


    public int getQueueSize() {
        return ((ScheduledThreadPoolExecutor) executorService).getQueue().size();
    }

    /**
     * site change period
     *
     * @param siteName
     * @param period(sec)
     */
    public void changePeriod(String siteName, int period) {
        Assert.hasText(siteName);
        Assert.isTrue(period >= 30);
        cancel(siteName);
        MonitorSite monitorSite = sitesHolder.getMonitorSite(siteName);
        if (!monitorSite.isRunning()) {
            monitorSite.setPeriod(period);
            monitor(monitorSite);
            logger.info("Site Name:{}  change period:{}", siteName, period);
        }
    }

    /**
     * stop monitor
     *
     * @param siteName
     */
    public void cancel(String siteName) {
        Assert.hasText(siteName);
        MonitorSite monitorSite = sitesHolder.getMonitorSite(siteName);
        if (monitorSite.isRunning()) {
            monitorSite.stop();
            String msg = alertMessage.getMessage(AlarmMessageFormat.END,df.format(new Date()),siteName);
            logger.info("Site Name:{} ", msg);
            siteInfoLogger.logInf(siteName, msg);
        } else {
            logger.info("Site Name:{}  had stopped,so do nothing ", siteName);
        }
    }

    /**
     * get monitor data
     *
     * @param siteName
     * @param <T>
     * @return HisData
     */
    public <T extends HisData> T getMonitorInf(String siteName) {
        Assert.hasText(siteName);
        MonitorSite monitorSite = sitesHolder.getMonitorSite(siteName);
        //      logger.debug(" getMonitorInf monitorSite =  {}",monitorSite.getSiteName() );
        return (T) monitorSite.getMonitorData();
    }

    /**
     * switch site save
     *
     * @param siteName
     */
    public boolean switchSave(String siteName) {
        MonitorSite monitorSite = sitesHolder.getMonitorSite(siteName);
        return monitorSite.switchSaveFlag();
    }


    /**
     * get save state
     * @param siteName
     * @return
     */
    public boolean isSave(String siteName){
         MonitorSite monitorSite = sitesHolder.getMonitorSite(siteName);
         return monitorSite.saveDbFlag;
    }


    public boolean siteRunning(String siteName) {
        MonitorSite monitorSite = sitesHolder.getMonitorSite(siteName);
        return monitorSite.isRunning();
    }

    public List<String> getLogger(String siteName) {
        return siteInfoLogger.getLog(siteName);
    }
}