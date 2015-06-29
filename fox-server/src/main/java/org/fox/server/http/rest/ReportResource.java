package org.fox.server.http.rest;

import org.fox.server.http.rest.report.OSStatsReportResource;

import javax.ws.rs.Path;

/**
 * @author jie.huang
 *         Date: 2015/6/29
 *         Time: 14:44
 */
@Path("/r")
public class ReportResource {
    private OSStatsReportResource osStatsReportResource;
    public ReportResource(){
        osStatsReportResource = new OSStatsReportResource();
    }

    @Path("os")
    public OSStatsReportResource getOsStatsReportResource(){
        return osStatsReportResource;
    }
}
