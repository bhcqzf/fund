package cn.bigbaic.dao;

import cn.bigbaic.domain.Fund;
import cn.bigbaic.domain.Fundcode;
import cn.bigbaic.domain.Mark;

public interface MarkDao {
    Integer initData(Fundcode fundcode);
    Integer insertMark(Fund fund);
    Mark selectLastMark(Fund fund);
    Integer isExist(Fundcode fundcode);

}
