package com.zcy;

import com.zcy.dao.Impl.TicketDaoImpl;
import com.zcy.dao.TicketDao;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )

    {


      /*  TicketDao ticketDao=new TicketDaoImpl();

        int sum= ticketDao.queryAllPayTicket(5);

        System.out.println(sum);*/
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(System.currentTimeMillis());
        String dateString = simpleDateFormat.format(date);
        System.out.println(dateString);

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,-1440);
        System.out.println(simpleDateFormat.format(nowTime.getTime()));
        System.out.println( "Hello World!" +nowTime);
    }
}
