package com.zcy.test;

import com.zcy.service.TicketService;
import com.zcy.service.impl.TicketServiceImpl;

public class test {
    public static void main(String[] args) {
        TicketService service=new TicketServiceImpl();
        //service.rule_2_compare();
        service.rule_3_compare();
    }
}
