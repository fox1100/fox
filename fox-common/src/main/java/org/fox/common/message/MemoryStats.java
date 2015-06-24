package org.fox.common.message;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 13:14
 */
public class MemoryStats extends Message {
   private long ram ;
   private long used;
   private long free;
   private long actualUsed;
   private long actualFree;
   private double usedPercent ;
   private double freePercent;

    public long getRam() {
        return ram;
    }

    public void setRam(long ram) {
        this.ram = ram;
    }

    public long getUsed() {
        return used;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public long getFree() {
        return free;
    }

    public void setFree(long free) {
        this.free = free;
    }

    public long getActualUsed() {
        return actualUsed;
    }

    public void setActualUsed(long actualUsed) {
        this.actualUsed = actualUsed;
    }

    public long getActualFree() {
        return actualFree;
    }

    public void setActualFree(long actualFree) {
        this.actualFree = actualFree;
    }

    public double getUsedPercent() {
        return usedPercent;
    }

    public void setUsedPercent(double usedPercent) {
        this.usedPercent = usedPercent;
    }

    public double getFreePercent() {
        return freePercent;
    }

    public void setFreePercent(double freePercent) {
        this.freePercent = freePercent;
    }

    @Override
    public String toString() {
        return "MemoryStats{" +
                "ram=" + ram +
                ", used=" + used +
                ", free=" + free +
                ", actualUsed=" + actualUsed +
                ", actualFree=" + actualFree +
                ", usedPercent=" + usedPercent +
                ", freePercent=" + freePercent +
                '}';
    }
}
