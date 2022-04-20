package cn.bigbaic.dao;

import cn.bigbaic.domain.Fundcode;

public interface FundcodeDao {
    Fundcode[] selectFundcode();
    Fundcode[] selectAllFundcode();
    Integer selectIsExist(String fundcode);
    Integer insertFundcode(Fundcode fundcode);
    Integer deleteFundcode(Fundcode fundcode);
    Integer updateEnableFundcode(Fundcode fundcode);
    Integer updateDisableFundcode(Fundcode fundcode);
    Integer updateAllEnableFundcode();
    Integer updateAllDisableFundcode();
}
