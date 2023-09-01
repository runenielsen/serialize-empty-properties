package example.micronaut;

import io.micronaut.serde.annotation.Serdeable;

import java.util.Arrays;
import java.util.Objects;

@Serdeable
public class TestBean {

    private String str;
    private String[] strArr;

    public TestBean() {
    }

    public TestBean(String str, String[] strArr) {
        this();
        this.str = str;
        this.strArr = strArr;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestBean testBean = (TestBean) o;
        return Objects.equals(str, testBean.str) && Arrays.equals(strArr, testBean.strArr);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(str);
        result = 31 * result + Arrays.hashCode(strArr);
        return result;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "str=" + (str != null ? "'" + str + "'" : null) +
                ", strArr=" + Arrays.toString(strArr) +
                '}';
    }
}