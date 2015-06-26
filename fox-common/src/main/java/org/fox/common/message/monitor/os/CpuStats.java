package org.fox.common.message.monitor.os;

import org.fox.common.message.Message;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 12:53
 */
public class CpuStats extends Message {
    private String vendor;
    private String model;
    private int mhz;
    private int totalCores;
    private double user;
    private double sys;
    private double idle;
    private double wait;
    private double usagePercent;

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMhz() {
        return mhz;
    }

    public void setMhz(int mhz) {
        this.mhz = mhz;
    }

    public int getTotalCores() {
        return totalCores;
    }

    public void setTotalCores(int totalCores) {
        this.totalCores = totalCores;
    }

    public double getUser() {
        return user;
    }

    public void setUser(double user) {
        this.user = user;
    }

    public double getSys() {
        return sys;
    }

    public void setSys(double sys) {
        this.sys = sys;
    }

    public double getIdle() {
        return idle;
    }

    public void setIdle(double idle) {
        this.idle = idle;
    }

    public double getWait() {
        return wait;
    }

    public void setWait(double wait) {
        this.wait = wait;
    }

    public double getUsagePercent() {
        return usagePercent;
    }

    public void setUsagePercent(double usagePercent) {
        this.usagePercent = usagePercent;
    }

    @Override
    public String toString() {
        return "CpuStats{" +
                "vendor='" + vendor + '\'' +
                ", model='" + model + '\'' +
                ", mhz=" + mhz +
                ", totalCores=" + totalCores +
                ", user=" + user +
                ", sys=" + sys +
                ", idle=" + idle +
                ", wait=" + wait +
                ", usagePercent=" + usagePercent +
                '}';
    }
}
