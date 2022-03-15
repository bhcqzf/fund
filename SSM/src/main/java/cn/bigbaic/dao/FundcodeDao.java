package cn.bigbaic.dao;

import cn.bigbaic.domain.Fundcode;

public interface FundcodeDao {
    Fundcode[] selectFundcode();
    Fundcode[] selectAllFundcode();
    Integer selectIsExist(String fundcode);
    Integer insertFundcode(Fundcode fundcode);
}
