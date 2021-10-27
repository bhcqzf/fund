package cn.bigbaic.controller;

import cn.bigbaic.dao.FundcodeDao;
import cn.bigbaic.dao.MarkDao;
import cn.bigbaic.domain.Fundcode;
import cn.bigbaic.service.DoParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
@Controller
@EnableScheduling
public class TaskController {

    @Autowired
    private DoParse doParse;

    @Autowired
    private MarkDao markDao;

    @Autowired
    private FundcodeDao fundcodeDao;

    @Scheduled(cron = "0 0/10 9-12,13-15 * * MON-FRI ") // 开市时间
//    @Scheduled(cron = "0/5 * * * * MON-FRI") // 间隔5秒执行
    public void taskCycle() {
        System.out.println("使用SpringMVC框架配置定时任务");
        Fundcode[] fundcodes = fundcodeDao.selectFundcode();
        for (Fundcode fundcode:
                fundcodes) {
            System.out.println(fundcode);
            doParse.parse(fundcode.getFundcode());
        }
    }

//       @Scheduled(cron = "0/5 * * * * * ") // 间隔5秒执行
    @Scheduled(cron = "0 */10 9-12,13-15 * * MON-FRI ") // 工作日9点
    public void initData() {

        Fundcode[] fundcodes = fundcodeDao.selectFundcode();
        for (Fundcode fundcode:
                fundcodes) {
            System.out.println(fundcode);
            if (markDao.isExist(fundcode) == 0 ){
                System.out.println("初始化数据");
                markDao.initData(fundcode);
            }else{
                System.out.println("数据已存在,无需重复初始化");
            }

        }

    }

    @Scheduled(cron = "0 0 0 * * * ") // 间隔5秒执行
    public void test() {
       Fundcode[] fundcodes = fundcodeDao.selectFundcode();
        for (Fundcode fundcode:
             fundcodes) {
            System.out.println(fundcode);
        }
    }
    @Scheduled(cron = "0 30 11 * * MON-FRI ") // 间隔5秒执行
    public String lunch(){
        Fundcode[] fundcodes = fundcodeDao.selectFundcode();
        String res = "";
        for (Fundcode fundcode:
                fundcodes) {
            System.out.println(fundcode);
            doParse.parse(fundcode.getFundcode());
            res = doParse.sendMsg(fundcode.getFundcode());
        }
        return res;
    }

    @Scheduled(cron = "0 0 15 * * MON-FRI ") // 间隔5秒执行
    public String dinner(){
        Fundcode[] fundcodes = fundcodeDao.selectFundcode();
        String res = "";
        for (Fundcode fundcode:
                fundcodes) {
            System.out.println(fundcode);
            doParse.parse(fundcode.getFundcode());
            res = doParse.sendMsg(fundcode.getFundcode());
        }
        return res;
    }
}
