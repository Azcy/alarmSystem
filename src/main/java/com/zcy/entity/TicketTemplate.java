package com.zcy.entity;

public class TicketTemplate {
    //模板号
    private String TicketTemplateID;
    private int sendNums;
    private int payNums;
    private int hour;

    public String getTicketTemplateID() {
        return TicketTemplateID;
    }

    public void setTicketTemplateID(String ticketTemplateID) {
        TicketTemplateID = ticketTemplateID;
    }

    public int getSendNums() {
        return sendNums;
    }

    public void setSendNums(int sendNums) {
        this.sendNums = sendNums;
    }

    public int getPayNums() {
        return payNums;
    }

    public void setPayNums(int payNums) {
        this.payNums = payNums;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}
