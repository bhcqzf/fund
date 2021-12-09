package cn.bigbaic.service;

import cn.bigbaic.dao.FundDao;
import cn.bigbaic.dao.MarkDao;
import cn.bigbaic.domain.Fund;
import cn.bigbaic.domain.Mark;
import cn.bigbaic.utils.PushMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoParse {

    @Autowired
    private GetFndInfo getFndInfo;

    @Autowired
    private FundDao fundDao;

    @Autowired
    private MarkDao markDao;

    private Fund fund;


    public Fund addFund(String fundcode){
       fund = getFndInfo.getFundInfo(fundcode);
       int res = fundDao.insetFund(fund);
       System.out.println(res==1?"数据插入成功":"数据插入失败");
       return fund;
    }

    public boolean parse(String fundcode){
        fund = this.addFund(fundcode);
        Mark mark = markDao.selectLastMark(fund);
        boolean issend = false;
        System.out.println(mark);
        Float difference = fund.getGszzl() - mark.getGszzl();
        if (( difference >= 0.2) || (difference <= -0.2)){
            System.out.println("当前差值==>"+String.valueOf(difference));
            System.out.println("当前mark中的净值==>"+String.valueOf(mark.getGszzl()));
            System.out.println("现在插入数据库的精致==>"+String.valueOf(fund.getGszzl()));
            markDao.insertMark(fund);
            String title = "当前基金==>"+fund.getName();
            String message = "预计变化==>"+String.valueOf(difference)+"            " +
                    "当前估算净值==>"+String.valueOf(fund.getGszzl());
            PushMessage.pushBark(title,message);//
            issend = true;
        }else{
            System.out.println("当前差值为"+String.valueOf(difference)+"小于0.2略过");
        }
        return issend;
    }
  /*  public Fund sendMsg(String fundcode){
        fund = this.addFund(fundcode);
        System.out.println(this.fund);
        Mark mark = markDao.selectLastMark(fund);
        System.out.println(mark);
        Float difference = fund.getGszzl() - mark.getGszzl();
        System.out.println("当前差值==>"+String.valueOf(difference));
        System.out.println("当前mark中的净值==>"+String.valueOf(mark.getGszzl()));
        System.out.println("现在插入数据库的精致==>"+String.valueOf(fund.getGszzl()));
        markDao.insertMark(fund);
        String title = "当前基金==>"+fund.getName();
        String message = "预计变化==>"+String.valueOf(difference)+"            " +
                "当前估算净值==>"+String.valueOf(fund.getGszzl());
        PushMessage.pushBark(title,message);
        return fund;
    }*/
    public Fund sendMsg(String fundcode){
    fund = this.addFund(fundcode);
    Mark mark = markDao.selectLastMark(fund);
    boolean issend = false;
    System.out.println(mark);
    Float difference = fund.getGszzl() - mark.getGszzl();
    System.out.println("当前差值==>" + String.valueOf(difference));
    System.out.println("当前mark中的净值==>" + String.valueOf(mark.getGszzl()));
    System.out.println("现在插入数据库的精致==>" + String.valueOf(fund.getGszzl()));
    markDao.insertMark(fund);
    String title = "当前基金==>" + fund.getName();
    String message = "预计变化==>" + String.valueOf(difference) + "            " +
            "当前估算净值==>" + String.valueOf(fund.getGszzl());
    PushMessage.pushBark(title, message);
        return fund;
    }

}
