package com.hexactive.proscheduler.CalendarModule;

import java.io.Serializable;

public class EventDetails implements Serializable {
    public String r_date,r_time;
    public String title;
    public EventDetails()
    {
        this.r_date=null;
        this.r_time=null;
        this.title=null;
    }

    EventDetails(String r_date,String r_time,String title)
    {

        this.r_date=r_date;
        this.r_time=r_time;
        this.title=title;

    }

}
