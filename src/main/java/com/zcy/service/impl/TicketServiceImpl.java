package com.zcy.service.impl;

import com.zcy.dao.Impl.TicketDaoImpl;
import com.zcy.dao.TicketDao;
import com.zcy.service.TicketService;
import com.zcy.service.VoiceService;
import com.zcy.util.VoiceInitUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TicketServiceImpl implements TicketService {
    TicketDao dao=new TicketDaoImpl();
    Map<String,String> voiceInit= VoiceInitUtil.initiateParam("小燕","关","20","40","100");
    VoiceService voiceService=new VoiceService();
    //voiceService.start(voiceInit,"ID:"+templateID+",before:"+before+",end:"+end+",before - end = "+result);
    Calendar now=Calendar.getInstance();
    //查出有效TemplateID
    List<String> ticketTemplateIDList =dao.queryVaildTicketTemplateID(now.getTime());
    /**
     * 1、前5分钟的兑券数量与前前5分钟的发券数量的值进行比较
     * */
    @Override
    public void rule_1_compare() {

        Calendar calendar=null;

        //遍历进行每五分钟比较
        for (String templateID:ticketTemplateIDList){
            calendar=Calendar.getInstance();
           // System.out.println("b"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
            int before=dao.querySendTicketByTemp(templateID,5,calendar.getTime());

            //获取前5分钟
            calendar.add(Calendar.MINUTE,-5);
           // System.out.println("e"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
            int end=dao.querySendTicketByRetailerID(templateID,5,calendar.getTime());

            int result=before-end;


            System.out.println("ID:"+templateID+",before:"+before+",end:"+end+",before - end = "+result);
        }


    }

    /**
     * 2、此刻前5分钟的兑券数量与昨天此刻时间前五分钟兑券数量的差距
     */
    @Override
    public void rule_2_compare() {
        Calendar calendar=null;

        //遍历进行每五分钟比较
        for (String templateID:ticketTemplateIDList){
            calendar=Calendar.getInstance();
            // System.out.println("b"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
            int before=dao.querySendTicketByTemp(templateID,5,calendar.getTime());

            //获取前一天时间
            calendar.add(Calendar.MINUTE,-1440);
            // System.out.println("e"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
            int end=dao.querySendTicketByRetailerID(templateID,5,calendar.getTime());

            //求差值
            int result=before-end;

            System.out.println("ID:"+templateID+",before:"+before+",end:"+end+",before - end = "+result);
        }


    }

    /**
     * 3、固定几个点 每天 10点 12点  14点 18点的总兑券与昨天的兑券进行比较
     */
    @Override
    public void rule_3_compare() {
        String date =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        //前天的数据
        String beforeDate=new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

        System.out.println("beforeDate"+beforeDate);
        try {


            int num_1_10=dao.querySendTicketByTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date+" 10:00:00"));
            int num_1_12=dao.querySendTicketByTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date+" 12:00:00"));
            int num_1_14=dao.querySendTicketByTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date+" 14:00:00"));
            int num_1_18=dao.querySendTicketByTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date+" 18:00:00"));

            int num_2_10=dao.querySendTicketByTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beforeDate+" 10:00:00"));
            int num_2_12=dao.querySendTicketByTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beforeDate+" 12:00:00"));
            int num_2_14=dao.querySendTicketByTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beforeDate+" 14:00:00"));
            int num_2_18=dao.querySendTicketByTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beforeDate+" 18:00:00"));
            if ((Math.abs(num_1_10-num_2_10)>2000&&num_1_10!=0||Math.abs(num_1_12-num_2_12)>2000||Math.abs(num_1_14-num_2_14)>2000||Math.abs(num_1_18-num_2_18)>2000)){
                //通知报异常
                voiceService.start(voiceInit,"数据异常，今天10点、12点、14点、18点与昨天相比较 ，相差比较大");
                System.out.println("异常");
            }
            voiceService.start(voiceInit,"数据异常，今天10点、12点、14点、18点与昨天相比较 ，相差比较大");

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    /**
     * 4、每小时播报一次兑券总数
     */
    @Override
    public void rule_4_compare() {

    }
}
