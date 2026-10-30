package com.mycompany.boarding_center;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * Generates the PawStay reservation report with JasperReports.
 *
 * The report intentionally joins four tables:
 * reservations -> customers -> pets -> payments.
 * Payments are aggregated so a reservation with multiple payments still
 * appears only once in the report.
 */
public final class ReportService {

    private ReportService() {
    }

    public static File generateReservationReport() throws Exception {
        try (Connection connection = Database.getConnection()) {

            var resource = ReportService.class.getResourceAsStream(
                    "/reports/reservation_report.jrxml");

            if (resource == null) {
                throw new IllegalStateException(
                        "Missing /reports/reservation_report.jrxml resource.");
            }

            JasperReport report = JasperCompileManager.compileReport(
                    JRXmlLoader.load(resource));

            // The SQL is stored in the JRXML so JasperReports executes it
            // directly through the supplied JDBC connection.
            JasperPrint print = JasperFillManager.fillReport(
                    report,
                    new HashMap<>(),
                    connection);

            File directory = new File("reports");
            if (!directory.exists() && !directory.mkdirs()) {
                throw new IllegalStateException(
                        "Unable to create the reports directory.");
            }

            File output = new File(
                    directory,
                    "PawStay_Reservation_Report.pdf");

            JasperExportManager.exportReportToPdfFile(
                    print,
                    output.getAbsolutePath());

            return output;
        }
    }
}
