package cn.bigbaic.controller;

import cn.bigbaic.dao.FundcodeDao;
import cn.bigbaic.dao.MarkDao;
import cn.bigbaic.domain.Fund;
import cn.bigbaic.domain.Fundcode;
import cn.bigbaic.service.DoParse;
import cn.bigbaic.service.GetFndInfo;
import cn.bigbaic.service.SayHello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <h3>spirngmvc-study</h3>
 * <p></p>
 *
 * @author : baibaibai
 * @date : 2021-07-30 11:20
 **/

@Controller
@RequestMapping
public class MyController {
    @Autowired
    private DoParse doParse;
    
    @Autowired
    private FundcodeDao fundcodeDao;

    @Autowired
    private GetFndInfo getFndInfo;

    @Autowired
    private MarkDao markDao;

    @ResponseBody
    @RequestMapping(value="/getfund",method = {RequestMethod.GET})
    public String getfund(){
        Fundcode[] fundcodes = fundcodeDao.selectFundcode();
        String res = "";
        for (Fundcode fundcode:fundcodes) {
            System.out.println(fundcode);
            boolean issend = doParse.parse(fundcode.getFundcode());
            if ( !issend ){
                res = doParse.sendMsg(fundcode.getFundcode());
            }
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/addfund")
    public String addfund(@RequestParam String fundcode){
        Fund fund = getFndInfo.getFundInfo(fundcode);
        System.out.println(fund);
        boolean isExist = fundcodeDao.selectIsExist(fund.getFundcode())>0?true:false;
        if (!isExist){
            Fundcode fundcode1 = new Fundcode();
            fundcode1.setFundcode(fundcode);
            fundcode1.setName(fund.getName());
            fundcode1.setEnable(1);
            int insertRes = fundcodeDao.insertFundcode(fundcode1);
            System.out.println(insertRes==1?"插入成功":"插入失败");
        }
        return "{\"success\":\"true\",\"msg\":\"yes\"}";
    }

    @ResponseBody
    @RequestMapping(value = "/init")
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
}
