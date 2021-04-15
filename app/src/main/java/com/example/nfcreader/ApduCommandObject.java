package com.example.nfcreader;

public class ApduCommandObject {

    private String CLA, INS, P1, P2, LC, DATA, LE;

    //Basic APDU command constructor
    public ApduCommandObject(String CLA, String INS, String P1, String P2, String LC, String DATA, String LE) {
        if (CLA.equals("") || CLA == null) {
            throw new IllegalArgumentException("Empty CLA!");
        }
        if (INS.equals("") || INS == null) {
            throw new IllegalArgumentException("Empty INS!");
        }
        if (P1.equals("") || P1 == null) {
            throw new IllegalArgumentException("Empty P1!");
        }
        if (P2.equals("") || P2 == null) {
            throw new IllegalArgumentException("Empty P2!");
        }
        this.CLA = CLA;
        this.INS = INS;
        this.P1 = P1;
        this.P2 = P2;
        this.LC = LC;
        this.DATA = DATA;
        this.LE = LE;
    }

    //Constructor for prepared APDU command
    public ApduCommandObject(String apduCommand) {
        if (apduCommand.length() < 8) {
            throw new IllegalArgumentException("At least four header bytes are mandatory!");
        } else {
            //Case 1: CLA|INS|P1|P2
            if (apduCommand.length() == 8) {
                this.CLA = apduCommand.substring(0, 2);
                this.INS = apduCommand.substring(2, 4);
                this.P1 = apduCommand.substring(4, 6);
                this.P2 = apduCommand.substring(6, 8);
                this.LC = "";
                this.DATA = "";
                this.LE = "";
            }
            //Case 2: CLA|INS|P1|P2|LE
            else if (apduCommand.length() == 10) {
                this.CLA = apduCommand.substring(0, 2);
                this.INS = apduCommand.substring(2, 4);
                this.P1 = apduCommand.substring(4, 6);
                this.P2 = apduCommand.substring(6, 8);
                this.LE = apduCommand.substring(8, 10);
                this.LC = "";
                this.DATA = "";
            }
            //Case 3: CLA|INS|P1|P2|LC|DATA
            else if (apduCommand.length() >= 12) {
                this.CLA = apduCommand.substring(0, 2);
                this.INS = apduCommand.substring(2, 4);
                this.P1 = apduCommand.substring(4, 6);
                this.P2 = apduCommand.substring(6, 8);
                this.LC = apduCommand.substring(8, 10);
                int dataLength = Integer.parseInt(apduCommand.substring(8, 10), 16);
                this.DATA = apduCommand.substring(10, 10 + (dataLength * 2));
                //Case 4: CLA|INS|P1|P2|LC|DATA|LE
                try {
                    this.LE = apduCommand.substring(10 + (dataLength * 2), 10 + (dataLength * 2) + 2);
                } catch (IndexOutOfBoundsException e) {
                    this.LE = "";
                }
            }

        }

    }

    public String getCLA() {
        return CLA;
    }

    public void setCLA(String CLA) {
        this.CLA = CLA;
    }

    public String getINS() {
        return INS;
    }

    public void setINS(String INS) {
        this.INS = INS;
    }

    public String getP1() {
        return P1;
    }

    public void setP1(String p1) {
        P1 = p1;
    }

    public String getP2() {
        return P2;
    }

    public void setP2(String p2) {
        P2 = p2;
    }

    public String getLC() {
        return LC;
    }

    public void setLC(String LC) {
        this.LC = LC;
    }

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public String getLE() {
        return LE;
    }

    public void setLE(String LE) {
        this.LE = LE;
    }

    @Override
    public String toString() {
        return CLA + INS + P1 + P2 + LC + DATA + LE;
    }


}