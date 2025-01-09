package com.example.spring.model.Report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Report {
    private ReportHeader reportHeader;
    private List<ReportItem> reportItems;

    public Report(){
        reportHeader = new ReportHeader();
        reportItems = new ArrayList<ReportItem>();
    }

    public Report(ReportHeader reportHeader, List<ReportItem> reportItems){
        this.reportHeader = reportHeader;
        this.reportItems = reportItems;
    }

    public List<ReportItem> getReportItems() {
        return reportItems;
    }

    public void setReportItems(List<ReportItem> reportItems) {
        this.reportItems = reportItems;
    }

    public ReportHeader getReportHeader() {
        return reportHeader;
    }

    public void setReportHeader(ReportHeader reportHeader) {
        this.reportHeader = reportHeader;
    }

    public void setReportHeader(String title) {
        this.reportHeader.setTitle(title);
    }

    public void setReportHeader(Date date) {
        this.reportHeader.setCreate_date(date);
    }

    public void setReportHeader(ReportType reportType) {
        this.reportHeader.setReportType(reportType);
    }

    public void setReportHeader(String title,Date date) {
        this.reportHeader.setTitle(title);
        this.reportHeader.setCreate_date(date);
    }

    public void setReportHeader(String title,ReportType reportType) {
        this.reportHeader.setTitle(title);
        this.reportHeader.setReportType(reportType);
    }

    public void setReportHeader(Date date, ReportType reportType) {
        this.reportHeader.setCreate_date(date);
        this.reportHeader.setReportType(reportType);
    }

    public void setReportHeader(String title,Date date,ReportType reportType) {
        this.reportHeader.setTitle(title);
        this.reportHeader.setCreate_date(date);
        this.reportHeader.setReportType(reportType);
    }

    @Override
    public String toString() {
        String result;
        result = "{" +
                "report_title=" + reportHeader.getTitle() +
                ", report_create_date=" + reportHeader.getCreate_date_zh() +
                ", report_type=" + reportHeader.getReportType().toString() +
                ", reportItems=[";
        for (ReportItem reportItem : reportItems) {
            result += "{" +
                "reportItem=" + reportItem.toString() +
            "}, ";
        }
        result += "]}";
        return result;
    }
}
