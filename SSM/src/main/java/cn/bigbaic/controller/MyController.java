package cn.bigbaic.controller;

import cn.bigbaic.dao.FundcodeDao;
import cn.bigbaic.dao.MarkDao;
import cn.bigbaic.domain.Fund;
import cn.bigbaic.domain.Fundcode;
import cn.bigbaic.domain.Result;
import cn.bigbaic.service.DoParse;
import cn.bigbaic.service.GetFndInfo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
        Result result;
        try {
            Fundcode[] fundcodes = fundcodeDao.selectFundcode();
            Fund fund;
            List<Fund> list = new ArrayList();
            for (Fundcode fundcode:fundcodes) {
                System.out.println(fundcode);
                fund = doParse.sendMsg(fundcode.getFundcode());
                System.out.println(fund);
                list.add(fund);
            }
            result = Result.ok();
            result.put(list);
        }catch (Exception e){
            result = Result.error();
        }
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/addfund")
    public String addfund(@RequestParam String fundcode){
        Fund fund = getFndInfo.getFundInfo(fundcode);
        System.out.println(fund);
        boolean isExist = fundcodeDao.selectIsExist(fund.getFundcode())>0?true:false;
        if (!isExist){
            //如果不存在
            Fundcode fundcode1 = new Fundcode();
            fundcode1.setFundcode(fundcode);
            fundcode1.setName(fund.getName());
            fundcode1.setEnable(1);
            int insertRes = fundcodeDao.insertFundcode(fundcode1);
            String msg = insertRes==1?"数据插入成功":"数据插入失败";
            System.out.println(msg);
            if (insertRes==1){
                //这里成功以后最好调用一下初始化init

                return JSON.toJSONString(Result.ok(msg));
            }else{
                return JSON.toJSONString(Result.error(msg));
            }
        }else{
            return JSON.toJSONString(Result.ok("该数据已存在"));
        }

    }

    @ResponseBody
    @RequestMapping(value = "/delfund")
    public String delfund(@RequestParam String fundcode){
        Fund fund = getFndInfo.getFundInfo(fundcode);
        System.out.println(fund);
        boolean isExist = fundcodeDao.selectIsExist(fund.getFundcode())>0?true:false;
        if (isExist){
            //如果存在
            Fundcode fundcode1 = new Fundcode();
            fundcode1.setFundcode(fundcode);
            int insertRes = fundcodeDao.deleteFundcode(fundcode1);
            String msg = insertRes==1?"数据删除成功":"数据删除失败";
            System.out.println(msg);
            if (insertRes==1){
                //这里成功以后最好调用一下初始化init
                return JSON.toJSONString(Result.ok(msg));
            }else{
                return JSON.toJSONString(Result.error(msg));
            }
        }else{
            return JSON.toJSONString(Result.ok("该数据不存在"));
        }

    }

    @ResponseBody
    @RequestMapping(value = "/init")
    public String initData() {
        Result result = new Result();
        String msg = "";
        Fundcode[] fundcodes = fundcodeDao.selectFundcode();
        for (Fundcode fundcode:
                fundcodes) {
            System.out.println(fundcode);
            if (markDao.isExist(fundcode) == 0 ){
                System.out.println("初始化数据");
                markDao.initData(fundcode);
                msg = "初始化数据成功";
                result = Result.ok().put(msg);
            }else{
                msg = "数据已存在,无需重复初始化";
                System.out.println(msg);
                result = Result.error(msg);

            }
        }
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/getallfund")
    public String getAllFundCode() {
        Result result = Result.ok();
        List<Fundcode> list = new ArrayList();
        Fundcode[] fundcodes = fundcodeDao.selectAllFundcode();
        for (Fundcode fundcode :
                fundcodes) {
            System.out.println(fundcode);
            list.add(fundcode);
        }
        result.put(list);
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/enablefund")
    public String enableFund(Fundcode fundcode) {
        Result result = Result.ok();
        int res = fundcodeDao.updateEnableFundcode(fundcode);
        String msg;
        if ( res == 1 ){
            msg = "启用成功";
        }else{
            msg = "启用失败";
        }
        result.put(msg);
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/disablefund")
    public String disableFund(Fundcode fundcode) {
        Result result = Result.ok();
        int res = fundcodeDao.updateDisableFundcode(fundcode);
        String msg;
        if ( res == 1 ){
            msg = "禁用成功";
        }else{
            msg = "禁用失败";
        }
        result.put(msg);
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/enableallfund")
    public String updateAllEnableFundcode() {
        Result result = Result.ok();
        int res = fundcodeDao.updateAllEnableFundcode();
        String msg;
        if ( res == 0 ){
            msg = "全部启用失败";
        }else{
            msg = "全部启用成功";
        }
        result.put(msg);
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/disableallfund")
    public String updateAllDisableFundcode() {
        Result result = Result.ok();
        int res = fundcodeDao.updateAllDisableFundcode();
        String msg;
        if ( res == 0 ){
            msg = "全部禁用失败";
        }else{
            msg = "全部禁用成功";
        }
        result.put(msg);
        return JSON.toJSONString(result);
    }


}
