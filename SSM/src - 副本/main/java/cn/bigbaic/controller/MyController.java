package cn.bigbaic.controller;

import cn.bigbaic.dao.FundcodeDao;
import cn.bigbaic.domain.Fundcode;
import cn.bigbaic.service.DoParse;
import cn.bigbaic.service.SayHello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

}
