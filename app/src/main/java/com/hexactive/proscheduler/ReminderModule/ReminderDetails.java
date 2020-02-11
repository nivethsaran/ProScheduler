package com.hexactive.proscheduler.ReminderModule;

public class ReminderDetails {
    String uid;
    String r_date,r_time;
    String note,title,notification,priority;
    ReminderDetails(String uid,String r_date,String r_time,String  note,String title,String notification,String priority)
    {
        this.uid=uid;
        this.r_date=r_date;
        this.r_time=r_time;
        this.note=note;
        this.title=title;
        this.notification=notification;
        this.priority=priority;
    }

}
