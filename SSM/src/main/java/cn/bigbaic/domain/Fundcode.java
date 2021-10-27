package cn.bigbaic.domain;

public class Fundcode {
    private String fundcode;
    private String name;
    private int enable;

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

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Fundcode{" +
                "fundcode='" + fundcode + '\'' +
                ", name='" + name + '\'' +
                ", enable=" + enable +
                '}';
    }
}
