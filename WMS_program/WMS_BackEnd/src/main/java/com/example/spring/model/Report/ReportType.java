package com.example.spring.model.Report;

public enum ReportType implements Comparable<ReportType> {
    BOUND_OUT("出库信息"),
    BOUND_IN("入库信息");

    private String message;

    ReportType() {

    }

    ReportType(String message) {
        this.message = message;
    }
}
