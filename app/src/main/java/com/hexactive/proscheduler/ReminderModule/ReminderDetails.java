package com.hexactive.proscheduler.ReminderModule;

import java.io.Serializable;

public class ReminderDetails implements Serializable {
   public String uid;
   public String r_date,r_time;
   public String note,title,notification,priority,rid;
    public ReminderDetails()
    {
        this.uid=null;
        this.r_date=null;
        this.r_time=null;
        this.note=null;
        this.title=null;
        this.notification=null;
        this.priority=null;
        this.rid=null;
    }

    ReminderDetails(String uid,String r_date,String r_time,String  note,String title,String notification,String priority)
    {
        this.uid=uid;
        this.r_date=r_date;
        this.r_time=r_time;
        this.note=note;
        this.title=title;
        this.notification=notification;
        this.priority=priority;
        this.rid=rid;
    }

}
