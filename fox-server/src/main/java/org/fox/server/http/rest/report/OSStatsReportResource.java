package org.fox.server.http.rest.report;

import org.fox.server.report.Report;
import org.fox.server.storage.Storage;
import org.fox.server.storage.StorageFactory;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author jie.huang
 *         Date: 2015/6/29
 *         Time: 14:44
 */
public class OSStatsReportResource {

    @Path("{date}/{hour}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentReport(@PathParam("date") String date,
                                     @PathParam("hour") int hour,
                                     @QueryParam("app") String appName) {
        Storage<String, Report> storage = StorageFactory.getMemoryStorage();
        Report report = storage.get(date + "/" + hour + "/" + appName);
        if (report != null) {
            return Response.ok(report).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
