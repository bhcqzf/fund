package cn.bigbaic.domain;

public class Fund {
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
