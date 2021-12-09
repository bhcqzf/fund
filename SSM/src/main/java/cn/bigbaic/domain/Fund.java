package cn.bigbaic.domain;

public class Fund {
    /*
    * ["fundcode"]=>"519983"           //基金代码
	  ["name"]=>"长信量化先锋混合A"       //基金名称
	  ["jzrq"]=>"2018-09-21"           //净值日期
	  ["dwjz"]=>"1.2440"               //当日净值
	  ["gsz"]=>"1.2388"                //估算净值
	  ["gszzl"]=>"-0.42"               //估算涨跌百分比 即-0.42%
	  ["gztime"]=>"2018-09-25 15:00"   //估值时间
    *
    * */
    private String fundcode;
    private String name;
    private String jzrq;
    private Float dwjz;
    private Float gsz;
    private Float gszzl;
    private String gztime;

    public String getFundcode() {
        return fundcode;
    }

    public void setFundcode(String fundcode) {
        this.fundcode = fundcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJzrq() {
        return jzrq;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }

    public Float getDwjz() {
        return dwjz;
    }

    public void setDwjz(Float dwjz) {
        this.dwjz = dwjz;
    }

    public Float getGsz() {
        return gsz;
    }

    public void setGsz(Float gsz) {
        this.gsz = gsz;
    }

    public Float getGszzl() {
        return gszzl;
    }

    public void setGszzl(Float gszzl) {
        this.gszzl = gszzl;
    }

    public String getGztime() {
        return gztime;
    }

    public void setGztime(String gztime) {
        this.gztime = gztime;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "fundcode='" + fundcode + '\'' +
                ", name='" + name + '\'' +
                ", jzrq='" + jzrq + '\'' +
                ", dwjz=" + dwjz +
                ", gsz=" + gsz +
                ", gszzl=" + gszzl +
                ", gztime='" + gztime + '\'' +
                '}';
    }
}
