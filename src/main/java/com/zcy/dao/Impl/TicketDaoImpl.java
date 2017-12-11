package com.zcy.dao.Impl;

import com.zcy.dao.TicketDao;
import com.zcy.entity.TicketTemplate;
import com.zcy.util.DBUtil;
import javafx.scene.chart.PieChart;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TicketDaoImpl implements TicketDao {

    Calendar nowTime=Calendar.getInstance();
    Calendar endTime=Calendar.getInstance();
    /**
     * 查询模板号 time时间段的发券数量
     * @param TicketTemplateID
     * @param time
     * @return
     */
    public int querySendTicketByTemp(String TicketTemplateID, int time,Date date) {
        int sum=0;
        //当前时间
        nowTime.setTime(date);
        //定义个结束时间
        endTime.setTime(date);
        //当前时间加上time

        nowTime.add(Calendar.MINUTE,-time);
        Connection conn= DBUtil.getConnection();
        String sql=null;

        sql="select COUNT(*) from c_ticket where TicketTemplateID = ? AND SendTime  BETWEEN  '"+nowTime+"' and '" +endTime+"'";
        try {
                PreparedStatement cmd= conn.prepareStatement(sql);
                cmd.setString(1,TicketTemplateID);

                ResultSet resultSet=cmd.executeQuery();
                while (resultSet.next()){
                    //获取查询的总数
                    sum=resultSet.getInt(1);
            }
          } catch (SQLException e) {
             e.printStackTrace();
        }
        return sum;



    }


    /**
     * 在date时间点的前time时间段，TicketTemplateID的发券数
     * @param TicketTemplateID
     * @param time
     * @param date
     * @return
     */
    public int queryPayTicketByTemp(String TicketTemplateID, int time ,Date date ) {
        int sum=0;
        //当前时间
        nowTime.setTime(date);
        //定义个结束时间
        endTime.setTime(date);
        //当前时间加上time
        nowTime.add(Calendar.MINUTE,-time);
        Connection conn= DBUtil.getConnection();

        System.out.println(endTime+","+nowTime);
        String sql="select COUNT(*) from c_ticket where TicketTemplateID = ? AND PayTime is not NULL AND PayTime  BETWEEN  '"+nowTime+"' and '" +endTime+"'";

        try {
            PreparedStatement cmd= conn.prepareStatement(sql);
            cmd.setString(1,TicketTemplateID);
            ResultSet resultSet=cmd.executeQuery();
            while (resultSet.next()){
                //获取查询的总数
                sum=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public int querySendTicketByRetailerID(String RetailerID, int time,Date date) {
        int sum=0;
        //当前时间
        nowTime.setTime(date);
        //定义个结束时间
        endTime.setTime(date);
        //当前时间加上time
        nowTime.add(Calendar.MINUTE,-time);
        Connection conn= DBUtil.getConnection();
        String sql="select COUNT(*) from c_ticket where RetailerID = ?AND SendTime is not NULL AND SendTime  BETWEEN  '"+nowTime+"' and '" +endTime+"'";

        try {
            PreparedStatement cmd= conn.prepareStatement(sql);
            cmd.setString(1,RetailerID);
            ResultSet resultSet=cmd.executeQuery();
            while (resultSet.next()){
                //获取查询的总数
                sum=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public int queryPayTicketByRetailerID(String RetailerID, int time,Date date) {
        int sum=0;
        //当前时间
        nowTime.setTime(date);
        //定义个结束时间
        endTime.setTime(date);
        //当前时间加上time
        nowTime.add(Calendar.MINUTE,-time);
        Connection conn= DBUtil.getConnection();
        String sql="select COUNT(*) from c_ticket where RetailerID = ?AND PayTime is not NULL AND PayTime  BETWEEN  '"+nowTime+"' and '" +endTime+"'";

        try {
            PreparedStatement cmd= conn.prepareStatement(sql);
            cmd.setString(1,RetailerID);
            ResultSet resultSet=cmd.executeQuery();
            while (resultSet.next()){
                //获取查询的总数
                sum=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public int queryAllSendTicket(int time,Date date) {
        int sum=0;
        //当前时间
        nowTime.setTime(date);
        //定义个结束时间
        endTime.setTime(date);
        //当前时间加上time
        nowTime.add(Calendar.MINUTE,-time);
        Connection conn= DBUtil.getConnection();
        String sql="select COUNT(*) from c_ticket where  SendTime  BETWEEN  '"+nowTime+"' and '" +endTime+"'";

        try {
            Statement cmd= conn.createStatement();



            ResultSet rs=cmd.executeQuery(sql);
            while (rs.next()){
                //获取查询的总数
                sum=rs.getInt(1);
                System.out.println(sum);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public int queryAllPayTicket(int time,Date date) {
        int sum=0;
        //当前时间
        nowTime.setTime(date);
        //定义个结束时间
        endTime.setTime(date);
        //当前时间加上time
        nowTime.add(Calendar.MINUTE,-time);
        Connection conn= DBUtil.getConnection();
        String sql="select COUNT(*) from c_ticket where PayTime  BETWEEN  '"+nowTime+"' and '" +endTime +"'";

        try {
            PreparedStatement cmd= conn.prepareStatement(sql);


            ResultSet resultSet=cmd.executeQuery();
            while (resultSet.next()){
                //获取查询的总数
                sum=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public double querAllPayPrice(int time,Date date) {
        int sum=0;
        //当前时间
        nowTime.setTime(date);
        //定义个结束时间
        endTime.setTime(date);
        //当前时间加上time
        nowTime.add(Calendar.MINUTE,-time);
        Connection conn= DBUtil.getConnection();
        String sql="select sum(TicketMoney) from c_ticket where SendTime  BETWEEN  '"+nowTime+"' and '" +endTime+"'";

        try {
            PreparedStatement cmd= conn.prepareStatement(sql);
            ResultSet resultSet=cmd.executeQuery();
            while (resultSet.next()){
                //获取查询的总数
                sum=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sum;
    }

    //查询一天中每个小时，每个模板号的发券数量
    @Override
    public List<TicketTemplate> queryDateSendTicketByTemp(Date date) {
        List<TicketTemplate> list=new ArrayList<TicketTemplate>();
        Connection conn=DBUtil.getConnection();
        //获取当天的年月日
       String nowDate=new SimpleDateFormat("yyyy-MM-dd").format(date);

       String sql="SELECT " +
               "TicketTemplateID," +
               "COUNT(*) AS sendNums ," +
               "0 AS payNums,"+
               "HOUR(SendTime) as hh  " +
               "FROM c_ticket WHERE SendTime BETWEEN '"+nowDate+"' AND '"+ nowDate+" 23:59:59' GROUP BY hh ORDER by hh ASC ";
        PreparedStatement cmd= null;
        try {
            cmd = conn.prepareStatement(sql);
            ResultSet rs=cmd.executeQuery();
            TicketTemplate ticketTemplate=null;
            while (rs.next()){
                 ticketTemplate=new TicketTemplate();
                ticketTemplate.setTicketTemplateID(rs.getString(1));
                ticketTemplate.setSendNums(rs.getInt(2));
                ticketTemplate.setPayNums(rs.getInt(3));
                ticketTemplate.setHour(rs.getInt(4));
                list.add(ticketTemplate);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }


    //查询一天中每个小时，每个模板号的兑券数量
    @Override
    public List<TicketTemplate> queryDatePayTicketByTemp(Date date) {
        List<TicketTemplate> list=new ArrayList<TicketTemplate>();
        Connection conn=DBUtil.getConnection();
        //获取当天的年月日
        String nowDate=new SimpleDateFormat("yyyy-MM-dd").format(date);

        String sql="SELECT " +
                "TicketTemplateID," +
                "0 AS sendNums ," +
                "COUNT(*) AS payNums,"+
                "HOUR(SendTime) as hh  " +
                "FROM c_ticket WHERE PayTime BETWEEN '"+nowDate+"' AND '"+ nowDate+" 23:59:59' GROUP BY hh ORDER by hh ASC ";
        PreparedStatement cmd= null;
        try {
            cmd = conn.prepareStatement(sql);
            ResultSet rs=cmd.executeQuery();
            TicketTemplate ticketTemplate=null;
            while (rs.next()){
                ticketTemplate=new TicketTemplate();
                ticketTemplate.setTicketTemplateID(rs.getString(1));
                ticketTemplate.setSendNums(rs.getInt(2));
                ticketTemplate.setPayNums(rs.getInt(3));
                ticketTemplate.setHour(rs.getInt(4));
                list.add(ticketTemplate);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    @Override
    public long querySendTicketByTime(Date date) {
        String stringDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println(stringDate);
        String Ymd=new SimpleDateFormat("yyyy-MM-dd").format(date);

        Connection conn=DBUtil.getConnection();
        String sql="SELECT COUNT(TicketTemplateID) as zzz FROM c_ticket where SendTime BETWEEN '"+Ymd+"' AND '"+stringDate+"'";

        //String sql="SELECT  * fr";
       // String sql="SELECT  count(*) FROM c_ticket ";
        System.out.println("sql: "+sql);


        long sum = 0;
        try {
           // Statement cmd=conn.createStatement();
            PreparedStatement cmd=conn.prepareStatement(sql);
            ResultSet rs=cmd.executeQuery();
            while (rs.next()){
                sum=rs.getInt(1);
            }



                //System.out.println("sum"+sum);

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sum;
    }

    @Override
    public int queryPayTicketByTime(Date date) {
        return 0;
    }

    //查询有效的TicketTemplateID
    @Override
    public List<String> queryVaildTicketTemplateID(Date date) {
        List<String> list=new ArrayList<String>();
        String stringDate=new SimpleDateFormat("yyyy-MM-dd").format(date);
        Connection conn=DBUtil.getConnection();
        String sql="SELECT TicketTemplateID FROM sprojectitem where EndDateTime > '"+stringDate+"'";
        PreparedStatement cmd= null;
        try {
            cmd = conn.prepareStatement(sql);
            ResultSet rs=cmd.executeQuery();
            while (rs.next()){
              list.add(rs.getString(1));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


}
