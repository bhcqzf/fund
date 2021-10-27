package cn.bigbaic.domain;

public class Mark {
    private String fundcode;
    private Float gszzl;
    private String jzrq;
    private String gztime;

    public String getFundcode() {
        return fundcode;
    }

    public void setFundcode(String fundcode) {
        this.fundcode = fundcode;
    }

    public Float getGszzl() {
        return gszzl;
    }

    public void setGszzl(Float gszzl) {
        this.gszzl = gszzl;
    }

    public String getJzrq() {
        return jzrq;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }

    public String getGztime() {
        return gztime;
    }

    public void setGztime(String gztime) {
        this.gztime = gztime;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "fundcode='" + fundcode + '\'' +
                ", gszzl=" + gszzl +
                ", jzrq='" + jzrq + '\'' +
                ", gztime='" + gztime + '\'' +
                '}';
    }
}
