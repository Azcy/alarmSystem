package com.zcy.dao;

import com.zcy.entity.TicketTemplate;

import java.util.Date;
import java.util.List;

public interface TicketDao {
    //根据（模板，时间段），查询发券数  EveryTime
    public int querySendTicketByTemp(String TicketTemplateID,int time ,Date date);

    //根据（模板，时间段），查询兑券数
    public int queryPayTicketByTemp(String TicketTemplateID,int time ,Date date);

    //根据（零售商，时间段），查询发券数
    public  int querySendTicketByRetailerID(String RetailerID,int time ,Date date);

    //根据（零售商，时间段），查询发券数
    public  int queryPayTicketByRetailerID(String RetailerID,int time ,Date date);

    //总发券数
    public  int queryAllSendTicket(int time,Date date);

    //总兑券数
    public  int queryAllPayTicket(int time,Date date);

    //总兑券金额
    public double querAllPayPrice(int time,Date date);



    //根据一天中每 1小时计算发券数，发券数
    public List<TicketTemplate> queryDateSendTicketByTemp(Date date);



    //查询24小时每小时 模板号对应的兑券数
    public List<TicketTemplate> queryDatePayTicketByTemp(Date date);



    //查询每天总券数为止的00:00:00，到date的发券数
    public int  querySendTicketByTime(Date date);



    //查询每天总券数为止的00:00:00，到date的发券数
    public int queryPayTicketByTime(Date date);

    //查询到datetime为止有效的模板id
    public List<String> queryVaildTicketTemplateID(Date date);


}
