package com.zcy.service;

/**
 * 检测规则
 */
public interface TicketService {
    /**
    * 1、前5分钟的兑券数量与前前5分钟的发券数量相差不超多10
    * */
    public void  rule_1_compare();


    /**
     * 2、此刻前5分钟的兑券数量与昨天此刻时间前五分钟兑券数量的差距
     */
    public void rule_2_compare();


    /**
     * 3、固定几个点 每天 10点 12点  14点 18点的总兑券与昨天的兑券进行比较
     */
    public void rule_3_compare();


    /**
     * 4、每小时播报一次兑券总数
     */
    public void rule_4_compare();

    /**
     *
     */
}
