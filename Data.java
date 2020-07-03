package com.gacrnd.gcs.generictest.inject;

/**
 * @author Jack_Ou  created on 2020/7/2.
 */
public class Data {
    String result;

    public Data(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Data{" + "result=" + result + '}';
    }
}
