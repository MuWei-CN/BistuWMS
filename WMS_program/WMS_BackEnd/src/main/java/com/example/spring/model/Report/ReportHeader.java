package com.example.spring.model.Report;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportHeader {
    private String title;
    private Date create_date;
    private ReportType reportType;

    public ReportHeader(){

    }

    public ReportHeader(String title,Date create_date,ReportType reportType){
        this.title = title;
        this.create_date = create_date;
        this.reportType = reportType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getCreate_date_zh(){
        String result;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        result = dateFormat.format(create_date);
        return result;
    }
}

