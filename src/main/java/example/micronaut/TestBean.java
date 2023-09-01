package example.micronaut;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class TestBean {

    private String str = "";
    private String[] strArr = new String[0];

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String[] getStrArr() {
        return strArr;
    }

    public void setStrArr(String[] strArr) {
        this.strArr = strArr;
    }
}