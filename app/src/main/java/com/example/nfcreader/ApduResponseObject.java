package com.example.nfcreader;

public class ApduResponseObject {
    private String data;
    private String S1;
    private String S2;

    public ApduResponseObject(String apduResponse) {
        int responseLength = apduResponse.length();
        this.S1 = apduResponse.substring(responseLength - 4, responseLength - 2);
        this.S2 = apduResponse.substring(responseLength - 2, responseLength);
        this.data = apduResponse.substring(0, responseLength - 4);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getS1() {
        return S1;
    }

    public void setS1(String s1) {
        S1 = s1;
    }

    public String getS2() {
        return S2;
    }

    public void setS2(String s2) {
        S2 = s2;
    }

    public String getS1_S2()
    {
        return (S1 + S2).toUpperCase();
    }
}
