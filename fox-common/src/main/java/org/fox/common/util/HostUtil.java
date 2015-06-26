package org.fox.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author jie.huang
 *         Date: 2015/6/26
 *         Time: 13:27
 */
public class HostUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(HostUtil.class);
    public final static String UNKNOWN_HOST_NAME = "Unknown-Host";
    public final static String UNKNOWN_HOST_IP = "Unknown-Ip";
    private static String hostName;
    private static String hostIp;
    private static int intHostIp;

    static {
        hostIp = UNKNOWN_HOST_IP;
        hostName = UNKNOWN_HOST_NAME;
        try {
            Enumeration<NetworkInterface> enet = NetworkInterface.getNetworkInterfaces();
            while (enet.hasMoreElements()) {

                NetworkInterface net = enet.nextElement();
                if (!net.isUp()) {
                    continue;
                }

                Enumeration<InetAddress> eaddr = net.getInetAddresses();
                InetAddress local = null;
                while (eaddr.hasMoreElements()) {
                    InetAddress address = eaddr.nextElement();
                    if (address instanceof Inet4Address) {
                        if (address.isLoopbackAddress() || address.isSiteLocalAddress()) {
                            if (local == null) {
                                local = address;
                            } else if (address.isSiteLocalAddress() && !address.isLoopbackAddress()) {
                                local = address;
                            } else if (local.isSiteLocalAddress() && address.isSiteLocalAddress()) {
                                if (local.getHostName().equals(local.getHostAddress()) && !address.getHostName().equals(address.getHostAddress())) {
                                    local = address;
                                }
                            }
                        } else {
                            if (local == null) {
                                local = address;
                            }
                        }
                    }
                }
                if (local != null) {
                    hostIp = local.getHostAddress();
                    intHostIp = ByteUtil.toInt(local.getAddress());
                }
            }
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            LOGGER.warn("Get host and ip error: ", e);
        }
    }

    public static String getHostName() {
        return hostName;
    }

    public static String getHostIp() {
        return hostIp;
    }

    public static int getHostIpIntValue() {
        return intHostIp;
    }
    public static String intToIp(int ipInt) {
        return String.valueOf(((ipInt >> 24) & 0xff)) + '.' + ((ipInt >> 16) & 0xff) + '.' + ((ipInt >> 8) & 0xff) + '.' + (ipInt & 0xff);
    }

}
