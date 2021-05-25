package com.example.nfcreader;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

public class VerificationActivity extends AppCompatActivity implements NfcAdapter.ReaderCallback {
    private NfcAdapter nfcAdapter = null;
    private TextView txt_verification_state;
    private final String EMPTY = "";
    private LottieAnimationView lottie_verification;
    private final String ZERO_BYTE = "00";
    private String currentDate;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        txt_verification_state = findViewById(R.id.txt_verification_state);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        calendar = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcAdapter.enableReaderMode(this, this, NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK, null);
        txt_verification_state.setText("Searching for requests...");
        txt_verification_state.setTextColor(Color.parseColor("#757575"));
        lottie_verification = findViewById(R.id.lottie_verification);
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcAdapter.disableReaderMode(this);
        txt_verification_state.setText("Searching for requests...");
        txt_verification_state.setTextColor(Color.parseColor("#757575"));
    }


    @Override
    public void onTagDiscovered(Tag tag) {
        IsoDep isoDep = IsoDep.get(tag);
        try {
            isoDep.connect();
            ApduCommandObject apduCommand = new ApduCommandObject(ApduCommandValues.COMMANDS.SELECT_AID);
            //final byte[] response = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
            //INS_GET_USER_ATTRIBUTES_DISCLOSED
/*            if (Utils.byteArrayToHexString(response).equals("9000")) {
                apduCommand.setCLA("70");
                apduCommand.setINS("0C");
                apduCommand.setP1("F8");
                apduCommand.setP2("05");
                apduCommand.setLC("");
                apduCommand.setDATA("");
                apduCommand.setLE("A1");
                final byte[] response2 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                System.out.println(Utils.byteArrayToHexString(response2));
            }*/


            //INS_SET_USER_IDENTIFIER
/*            if (Utils.byteArrayToHexString(response).equals("9000")) {
                String data = "1000000000000004".toUpperCase();
                apduCommand.setCLA("70");
                apduCommand.setINS("0B");
                apduCommand.setP1("00");
                apduCommand.setP2("00");
                apduCommand.setLC(Utils.dataLengthCounter(data));
                apduCommand.setDATA(data);
                apduCommand.setLE("00");
                final byte[] response2 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                System.out.println(Utils.byteArrayToHexString(response2));
            }*/
            //INS_GET_USER_IDENTIFIER
/*           if (Utils.byteArrayToHexString(response).equals("9000")) {
                apduCommand.setCLA("70");
                apduCommand.setINS("01");
                apduCommand.setP1("00");
                apduCommand.setP2("00");
                apduCommand.setLC("");
                apduCommand.setDATA("");
                apduCommand.setLE("08");
                final byte[] response2 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                System.out.println(Utils.byteArrayToHexString(response2));
            }*/
            //INS_SET_REVOCATION_AUTHORITY_DATA
            /*if (Utils.byteArrayToHexString(response).equals("9000")) {
                apduCommand.setCLA("70");
                apduCommand.setINS("02");
                apduCommand.setP1("01");
                apduCommand.setP2("06");
                apduCommand.setLC("61");
                apduCommand.setDATA("108AD0190590A76A939C261747F22FF1FAD6C689D8C5BEDB6934F603225A5A16041D2E912CB510128C5D4B70437CAF06977B990E4E693D5B97DA0A34CE9B37779C1E0FE27C76E3F68730BFEA2A61BCE865E9F4AEA86F6E18789293DA65973D4C81");
                apduCommand.setLE("");
                final byte[] response2 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                if (Utils.byteArrayToHexString(response2).toUpperCase().equals("91AF")) {
                    apduCommand.setCLA("70");
                    apduCommand.setINS("02");
                    apduCommand.setP1("02");
                    apduCommand.setP2("06");
                    apduCommand.setLC("FA");
                    apduCommand.setDATA("0A021A32E3ABA84525FE5D7F3782370CD1055FE4DD012758AFC010457E978EB15829190DFEA4BBC5826D455EFF9DF17FA070070464998B2ECEA4150B9993A2812AE30417E55666B0B25906544D9E67A123FFAFA294AE8826E14F80D95325C90A4C55112004F83D9C3A2EB80BAB6BE3AFB17B87FE6D825CCA90D0021C5997633CA5CCF9040E45472E94122E45BD4DA2E5E818800928E1DC22FABC4723E867D1760A8112DA1C50D3CC6A558DBFF9B395B553CC7D8D693DC20CB398125CE26620864F8020F1000934981F1A4379F6818C4A1FBA59E909D286B0A904C904FF34BCA16AE362B6D80005298AB19A8E5AAB5B101C7ED75E4CD35D4A7422");
                    apduCommand.setLE("");
                    final byte[] response3 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                    if (Utils.byteArrayToHexString(response3).toUpperCase().equals("91AF")) {
                        apduCommand.setCLA("70");
                        apduCommand.setINS("02");
                        apduCommand.setP1("03");
                        apduCommand.setP2("06");
                        apduCommand.setLC("FA");
                        apduCommand.setDATA("F342674451B7F6ED632F4FB70007D38ED851A9AF0ADA168C7890D77C34E0B810526FA3001357A76170705FBFA30008B00884F15CAFE8A762BD524CAC02FF3817AD0C2F3B629FB779B3521F59216B000EBEBCD0FBFC804131FD58181A260870C2CED3F07E58C659BF7BE72AE0C4CBAD0006F04D43CBF6C077DAF5188B2916DD26C642D94738F76370A81389035C2078D0001844609084A6C3204483345FA05F400310ABBF2295F7F4CF77B177B98BF9BE9D0009AE0F36F653D44A8E80C67DCE3FF67AEB2C0277F1E655C8F7465841DC5C70B9000A8F99751DA1F33394F8575D99113337130709BA8DDF1B32C8073BD2EBEE27DF0010625E99D597");
                        apduCommand.setLE("");
                        final byte[] response4 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                        if (Utils.byteArrayToHexString(response4).toUpperCase().equals("91AF")) {
                            apduCommand.setCLA("70");
                            apduCommand.setINS("02");
                            apduCommand.setP1("04");
                            apduCommand.setP2("06");
                            apduCommand.setLC("FA");
                            apduCommand.setDATA("6C4674DDD5F4E7F1FCB6C16A0035F4418385890868B8FC8DD6850420E805A3107F5ABC9F7EEB2D92EAE80A447BBBCFF8B7A569BE3DB8BD3A7E768700A97E2DF24FE40E8BDE2FAD3FCC4645D49D39DB7FCA6A9B834F8FFCD0FB06A20420225CFBE3F70BC5A65CABF1168FB3FF8BBD144CD47FB7FB94EF37C0B866F9EF117E5BC68C7F9A810122CF17E0A5B0220C84B738EE443CD0FB9F568367B2BADA041E019BB7FBEAA75AA74E91378AAA18B5C06A40226AC5A0711BE8D9E11351457D1C8522C4044702DB259B58293A3D22F079476A175B18EAD90DC7C49799440B7604231CE473C524F8B4582BB4DFE2759086A82684E8992EF9F98CFF4E0D");
                            apduCommand.setLE("");
                            final byte[] response5 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                            if (Utils.byteArrayToHexString(response5).toUpperCase().equals("91AF")) {
                                apduCommand.setCLA("70");
                                apduCommand.setINS("02");
                                apduCommand.setP1("05");
                                apduCommand.setP2("06");
                                apduCommand.setLC("FA");
                                apduCommand.setDATA("E1FCDCA701029BDCFF29AC0F4520F92C09B19921548B102E378E67888D598FCD6BA9D10B041CF7C96A029171E5B82A97D4B3F38C376FCEBA037C08010261AFEF47EFB9C434224DFDB90125AC747AF6DFBBC301298E78CED39B80A02EC72BA4B696BAD9FF500409A8F38FC35DB9B59E8AC44C063F40D550D403F3EE75EC904F0FBC1E5A908E341350A826579D4BE25C7AF06D2CD31EF35E774A64222204C61598F7C232F62997041A8E7BD1C986DFC71ACC99FF5C757BE3C640B2007DBAF26CC42606B40076877422C425703A799837D6849556EE72AF0190F0E06EC24E126E4ECB81DC781C1AEF041C6D33891B17E93279874D352D6E23583DB6");
                                apduCommand.setLE("");
                                final byte[] response6 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                                if (Utils.byteArrayToHexString(response6).toUpperCase().equals("91AF")) {
                                    apduCommand.setCLA("70");
                                    apduCommand.setINS("02");
                                    apduCommand.setP1("06");
                                    apduCommand.setP2("06");
                                    apduCommand.setLC("B0");
                                    apduCommand.setDATA("60DB279128122EB20D76FFBBC3190586A9C058E4205030C878AB4AD45FAFF14EC7EAC83ED11BC343D7C9B516A799041F7E058149C454735E87986AEDAAE95AD2B7934C2008A37A42198A2B8FCE978B01E983C38AA80D1BF39C16F158AF3C4130FAE6CCD9EB76CBFD5B0CB17DF71401042169F550E6D1937CED216B50A78CF26F6203624106C9FCAB7E88412C4CC42EEA24A46BA138CE88DC08216AEEDFD16AAC7D71EFE2F1DC2CA36DEA4ECF11FAF1E5");
                                    apduCommand.setLE("");
                                    final byte[] response7 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                                    System.out.println(Utils.byteArrayToHexString(response7));
                                }
                            }
                        }
                    }
                }
            }*/
            //INS_GET_USER_IDENTIFIER_ATTRIBUTES
/*            if (Utils.byteArrayToHexString(response).equals("9000")){
                String data = "";
                apduCommand.setCLA("70");
                apduCommand.setINS("04");
                apduCommand.setP1("01");
                apduCommand.setP2("01");
                apduCommand.setLC("");
                apduCommand.setDATA(data);
                apduCommand.setLE("6A");
                final byte[] response2 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                System.out.println(Utils.byteArrayToHexString(response2));
            }*/
            //INS_SET_USER_ATTRIBUTES
/*            if (Utils.byteArrayToHexString(response).equals("9000")) {
                String data = "050E38CCCF1472730D07C592576D5C35831D7B7829AF6486394B66AF10A6DB208D0DD3A04A448324630A5EACCD987DD5572610EDEC1677078E16E48BD0612E1777166B3071F1DA73B051C0B7FA6CF0F7027684AC35D326C3A51C1170A84650434F1CEE3BB1A9147C15B97F634893FA656CA33B1540E399DFBAEB51F646C5889FE51F5D67629F4DC4311A392CAB422D1502A062C791D427838786932CC6828E240D";
                apduCommand.setCLA("70");
                apduCommand.setINS("03");
                apduCommand.setP1("01");
                apduCommand.setP2("01");
                apduCommand.setLC(Utils.dataLengthCounter(data));
                apduCommand.setDATA(data);
                apduCommand.setLE("");
                final byte[] response2 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                System.out.println(Utils.byteArrayToHexString(response2));
            }*/

            //INS_SET_ISSUER_SIGNATURES
/*          if (Utils.byteArrayToHexString(response).equals("9000")) {
                String data = "04050423C45A0095B1618752D9F128AEC3F951F51C3D211EC34AA6C6DA9E9EC3EB1C08264F7C341E30A88812A0D70D807C7362B735C38DFA858FAA5ACD9A122CC6041FE57C3130245F2000D4CBF8B0E8CE6F7A1A9DDD9A0B07E5F044F53837C251720074276087F6586C9258803B10A6CD7166536EB9FC894B723FA359B365500E70041751DB7F118C2700D416124F0A3BFFEC84F1408AB70B76A391F75FBF066ED63D011B2B218A1FF96C7E805743891404042B2692A97110AEA3F6B1E80D9A2466600414D2A7645476BEDE301898111CF80DC12E19F36841A969B81A426ADACCEA57990AAF7ACDB41AEC0A287011AFACC6812D0BE445F88AB8";
                apduCommand.setCLA("70");
                apduCommand.setINS("05");
                apduCommand.setP1("01");
                apduCommand.setP2("02");
                apduCommand.setLC(Utils.dataLengthCounter(data));
                apduCommand.setDATA(data);
                apduCommand.setLE("");
                final byte[] response2 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                if (Utils.byteArrayToHexString(response2).toUpperCase().equals("91AF")) {
                    data = "62FBE163DF29406830BF041252D0AD6722E14C4AB3CD64AE5ADC82641063767DA8E8C7015B32D7E2F120BA0DD68C052DACFE57CFF75612091EFF4A458E33A5D3EFBF6D9F11BB0881350FCC041A605D918B563DF249DF616566A51FD4DA716D623673FDCBB7ED790A30A795B60A8309790915AAD16D10A0FDEE6A0AD8A1C09269990BC3ED854AEFA8257A322404232C850A34612B3F9F9CD6620518FECD272CA58986BF98465ECFB244A649AD810871488C21BF21DECBEB73786564F901A0535C8DA87966793BDF7C5A5746A36D";
                    apduCommand.setCLA("70");
                    apduCommand.setINS("05");
                    apduCommand.setP1("02");
                    apduCommand.setP2("02");
                    apduCommand.setLC(Utils.dataLengthCounter(data));
                    apduCommand.setDATA(data);
                    apduCommand.setLE("");
                    final byte[] response3 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                    System.out.println(Utils.byteArrayToHexString(response3));
                }
            }*/
            //CMD_TEST_BIT_CHECKER
/*            if (Utils.byteArrayToHexString(response).equals("9000")) {
                String data = "";
                apduCommand.setCLA("70");
                apduCommand.setINS("09");
                apduCommand.setP1("20");
                apduCommand.setP2("04");
                apduCommand.setLC("");
                apduCommand.setDATA("");
                apduCommand.setLE("");
                final byte[] response2 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
            }*/
            //INS_COMPUTE_PROOF_OF_KNOWLEDGE_SEQ_DISCLOSED
/*            if (Utils.byteArrayToHexString(response).equals("9000")) {
                String data = "6237EB155B271AB642156C1F36AF4F0ED056C2E93E994909E41CB421E7391FDB01280421";
                apduCommand.setCLA("70");
                apduCommand.setINS("0A");
                apduCommand.setP1("20");
                apduCommand.setP2("04");
                apduCommand.setLC(Utils.dataLengthCounter(data));
                apduCommand.setDATA(data);
                apduCommand.setLE("");
                final byte[] response2 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
            }*/

            //INS_GET_PROOF_OF_KNOWLEDGE P1="01"
           /* if (Utils.byteArrayToHexString(response).equals("9000")) {
                String data = "";
                apduCommand.setCLA("70");
                apduCommand.setINS("07");
                apduCommand.setP1("01");
                apduCommand.setP2("02");
                apduCommand.setLC("");
                apduCommand.setDATA("");
                apduCommand.setLE("FA");
                final byte[] response2 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                System.out.println(Utils.byteArrayToHexString(response2));
                if (Utils.byteArrayToHexString(response2).substring(500, 504).toUpperCase().equals("91AF")) {
                    data = "";
                    apduCommand.setCLA("70");
                    apduCommand.setINS("07");
                    apduCommand.setP1("01");
                    apduCommand.setP2("02");
                    apduCommand.setLC("");
                    apduCommand.setDATA("");
                    apduCommand.setLE("3C");
                    final byte[] response3 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                    System.out.println(Utils.byteArrayToHexString(response3));
                }


            }/*
            //INS_GET_PROOF_OF_KNOWLEDGE P1="02"
            if (Utils.byteArrayToHexString(response).equals("9000")) {
                String data = "";
                apduCommand.setCLA("70");
                apduCommand.setINS("07");
                apduCommand.setP1("02");
                apduCommand.setP2("02");
                apduCommand.setLC("");
                apduCommand.setDATA("");
                apduCommand.setLE("FA");
                final byte[] response2 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                System.out.println("250 Bytes:" + Utils.byteArrayToHexString(response2));
                System.out.println(Utils.byteArrayToHexString(response2).substring(500, 504));
                if (Utils.byteArrayToHexString(response2).substring(500, 504).toUpperCase().equals("91AF")) {
                    data = "";
                    apduCommand.setCLA("70");
                    apduCommand.setINS("07");
                    apduCommand.setP1("02");
                    apduCommand.setP2("02");
                    apduCommand.setLC("");
                    apduCommand.setDATA("");
                    apduCommand.setLE("8C");
                    final byte[] response3 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                    System.out.println("140 Bytes: " + Utils.byteArrayToHexString(response3));
                }


            }*/
           ApduResponseObject response_to_SELECT_AID = new ApduResponseObject(Utils.byteArrayToHexString(isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()))));
            //CMD_TEST_BIT_CHECKER
            if (response_to_SELECT_AID.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                ApduResponseObject response_to_CMD_TEST_BIT_CHECKER = CMD_TEST_BIT_CHECKER(isoDep, apduCommand);
                //INS_COMPUTE_PROOF_OF_KNOWLEDGE_SEQ_DISCLOSED
                if (response_to_CMD_TEST_BIT_CHECKER.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                    //CryptoCore initialization
                    CryptoCore cryptoCore = new CryptoCore(this);
                    ApduResponseObject response_to_INS_COMPUTE_PROOF_OF_KNOWLEDGE_SEQ_DISCLOSED = INS_COMPUTE_PROOF_OF_KNOWLEDGE_SEQ_DISCLOSED(isoDep, apduCommand, cryptoCore);
                    //INS_GET_PROOF_OF_KNOWLEDGE
                    if (response_to_INS_COMPUTE_PROOF_OF_KNOWLEDGE_SEQ_DISCLOSED.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                        //Crypto Credential selection
                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
                        //EID
                        if (sharedPreferences.getBoolean(Constants.Eid.EID_CRYPTO_CREDENCIAL, false)) {
                            //Resetting OLD values, like s_m_1, s_m_2 and so on..
                            resetValuesFromDatabase();
                            //Getting number of hidden attributes
                            int eid_number_of_hidden_attributes = sharedPreferences.getInt(Constants.Eid.EID_NUMBER_OF_HIDDEN_ATTRIBUTES, Integer.parseInt(Constants.Eid.EID_NUMBER_OF_ATTRIBUTES, 16));
                            //EID -- Two or less hidden attributes. Two or less hidden attributes means size < 250
                            if (eid_number_of_hidden_attributes <= 2) {
                                ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI = INS_GET_PROOF_OF_KNOWLEDGE_PI(isoDep, apduCommand, eid_number_of_hidden_attributes);
                                if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                                    ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1 = INS_GET_PROOF_OF_KNOWLEDGE_CRED_1(isoDep, apduCommand);
                                    if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1.getS1_S2().equals(ApduCommandValues.SW1_SW2.MORE_DATA)) {
                                        ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2 = INS_GET_PROOF_OF_KNOWLEDGE_CRED_2(isoDep, apduCommand);
                                        if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                                            //Verification
                                            verifyAttributes(response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI, null, response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1, response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2, Integer.parseInt(Constants.Eid.EID_NUMBER_OF_ATTRIBUTES, 16), cryptoCore);
                                        }
                                        //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                        else {
                                            showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                        }
                                    }
                                    //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                    else {
                                        showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                    }
                                }
                                //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                else {
                                    showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                }
                            }
                            //EID -- Three or more hidden attributes
                            else {
                                int totalSizeInBytes = 182 + eid_number_of_hidden_attributes * 32;
                                ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_1 = INS_GET_PROOF_OF_KNOWLEDGE_PI_1(isoDep, apduCommand, eid_number_of_hidden_attributes);
                                if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_1.getS1_S2().equals(ApduCommandValues.SW1_SW2.MORE_DATA)) {
                                    ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_2 = INS_GET_PROOF_OF_KNOWLEDGE_PI_2(isoDep, apduCommand, totalSizeInBytes);
                                    if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_2.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                                        ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1 = INS_GET_PROOF_OF_KNOWLEDGE_CRED_1(isoDep, apduCommand);
                                        if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1.getS1_S2().equals(ApduCommandValues.SW1_SW2.MORE_DATA)) {
                                            ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2 = INS_GET_PROOF_OF_KNOWLEDGE_CRED_2(isoDep, apduCommand);
                                            if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                                                verifyAttributes(response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_1, response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_2, response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1, response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2, Integer.parseInt(Constants.Eid.EID_NUMBER_OF_ATTRIBUTES, 16), cryptoCore);
                                            }
                                            //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                            else {
                                                showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                            }
                                        }
                                        //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                        else {
                                            showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                        }
                                    }
                                    //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                    else {
                                        showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                    }
                                }
                                //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                else {
                                    showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                }
                            }
                        }
                        //Ticket
                        else if (sharedPreferences.getBoolean(Constants.Ticket.TICKET_CRYPTO_CREDENCIAL, false)) {
                            resetValuesFromDatabase();
                            //Getting number of hidden attributes
                            int ticket_number_of_hidden_attributes = sharedPreferences.getInt(Constants.Ticket.TICKET_NUMBER_OF_HIDDEN_ATTRIBUTES, Integer.parseInt(Constants.Ticket.TICKET_NUMBER_OF_ATTRIBUTES, 16));
                            //Ticket -- Two or less hidden attributes. Two or less hidden attributes means size < 250
                            if (ticket_number_of_hidden_attributes <= 2) {
                                ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI = INS_GET_PROOF_OF_KNOWLEDGE_PI(isoDep, apduCommand, ticket_number_of_hidden_attributes);
                                if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                                    ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1 = INS_GET_PROOF_OF_KNOWLEDGE_CRED_1(isoDep, apduCommand);
                                    if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1.getS1_S2().equals(ApduCommandValues.SW1_SW2.MORE_DATA)) {
                                        ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2 = INS_GET_PROOF_OF_KNOWLEDGE_CRED_2(isoDep, apduCommand);
                                        if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                                            //Verification
                                            verifyAttributes(response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI, null, response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1, response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2, Integer.parseInt(Constants.Ticket.TICKET_NUMBER_OF_ATTRIBUTES, 16), cryptoCore);
                                        }
                                        //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                        else {
                                            showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                        }
                                    }
                                    //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                    else {
                                        showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                    }
                                }
                                //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                else {
                                    showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                }
                            }
                            //Ticket -- Three or more hidden attributes
                            else {
                                int totalSizeInBytes = 182 + ticket_number_of_hidden_attributes * 32;
                                ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_1 = INS_GET_PROOF_OF_KNOWLEDGE_PI_1(isoDep, apduCommand, ticket_number_of_hidden_attributes);
                                if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_1.getS1_S2().equals(ApduCommandValues.SW1_SW2.MORE_DATA)) {
                                    ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_2 = INS_GET_PROOF_OF_KNOWLEDGE_PI_2(isoDep, apduCommand, totalSizeInBytes);
                                    if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_2.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                                        ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1 = INS_GET_PROOF_OF_KNOWLEDGE_CRED_1(isoDep, apduCommand);
                                        if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1.getS1_S2().equals(ApduCommandValues.SW1_SW2.MORE_DATA)) {
                                            ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2 = INS_GET_PROOF_OF_KNOWLEDGE_CRED_2(isoDep, apduCommand);
                                            if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                                                verifyAttributes(response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_1, response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_2, response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1, response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2, Integer.parseInt(Constants.Ticket.TICKET_NUMBER_OF_ATTRIBUTES, 16), cryptoCore);
                                            }
                                            //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                            else {
                                                showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                            }
                                        }
                                        //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                        else {
                                            showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                        }
                                    }
                                    //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                    else {
                                        showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                    }
                                }
                                //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                else {
                                    showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                }
                            }
                        }
                        //Employee Card
                        else if (sharedPreferences.getBoolean(Constants.EmployeeCard.EMPLOYEE_CARD_CRYPTO_CREDENCIAL, false)) {
                            resetValuesFromDatabase();
                            //Getting number of hidden attributes
                            int employee_card_number_of_hidden_attributes = sharedPreferences.getInt(Constants.EmployeeCard.EMPLOYEE_CARD_NUMBER_OF_HIDDEN_ATTRIBUTES, Integer.parseInt(Constants.EmployeeCard.EMPLOYEE_CARD_NUMBER_OF_ATTRIBUTES, 16));
                            //Employee Card -- Two or less hidden attributes. Two or less hidden attributes means size < 250
                            if (employee_card_number_of_hidden_attributes <= 2) {
                                ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI = INS_GET_PROOF_OF_KNOWLEDGE_PI(isoDep, apduCommand, employee_card_number_of_hidden_attributes);
                                if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                                    ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1 = INS_GET_PROOF_OF_KNOWLEDGE_CRED_1(isoDep, apduCommand);
                                    if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1.getS1_S2().equals(ApduCommandValues.SW1_SW2.MORE_DATA)) {
                                        ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2 = INS_GET_PROOF_OF_KNOWLEDGE_CRED_2(isoDep, apduCommand);
                                        if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                                            //Verification
                                            verifyAttributes(response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI, null, response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1, response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2, Integer.parseInt(Constants.EmployeeCard.EMPLOYEE_CARD_NUMBER_OF_ATTRIBUTES, 16), cryptoCore);
                                        }
                                        //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                        else {
                                            showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                        }
                                    }
                                    //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                    else {
                                        showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                    }
                                }
                                //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                else {
                                    showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                }
                            }
                            //Employee Card -- Three or more hidden attributes
                            else {
                                int totalSizeInBytes = 182 + employee_card_number_of_hidden_attributes * 32;
                                ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_1 = INS_GET_PROOF_OF_KNOWLEDGE_PI_1(isoDep, apduCommand, employee_card_number_of_hidden_attributes);
                                if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_1.getS1_S2().equals(ApduCommandValues.SW1_SW2.MORE_DATA)) {
                                    ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_2 = INS_GET_PROOF_OF_KNOWLEDGE_PI_2(isoDep, apduCommand, totalSizeInBytes);
                                    if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_2.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                                        ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1 = INS_GET_PROOF_OF_KNOWLEDGE_CRED_1(isoDep, apduCommand);
                                        if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1.getS1_S2().equals(ApduCommandValues.SW1_SW2.MORE_DATA)) {
                                            ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2 = INS_GET_PROOF_OF_KNOWLEDGE_CRED_2(isoDep, apduCommand);
                                            if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                                                verifyAttributes(response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_1, response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_2, response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1, response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2, Integer.parseInt(Constants.EmployeeCard.EMPLOYEE_CARD_NUMBER_OF_ATTRIBUTES, 16), cryptoCore);
                                            }
                                            //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                            else {
                                                showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                            }
                                        }
                                        //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                        else {
                                            showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                        }
                                    }
                                    //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                    else {
                                        showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                    }
                                }
                                //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                else {
                                    showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                }
                            }
                        }
                        //User Defined
                        else if (sharedPreferences.getBoolean(Constants.UserDefined.USER_DEFINED_CRYPTO_CREDENCIAL, false)) {
                            resetValuesFromDatabase();
                            //Getting number of hidden attributes
                            int user_defined_number_of_hidden_attributes = sharedPreferences.getInt(Constants.UserDefined.USER_DEFINED_NUMBER_OF_HIDDEN_ATTRIBUTES, 0);
                            //User Defined -- Two or less hidden attributes. Two or less hidden attributes means size < 250
                            if (user_defined_number_of_hidden_attributes <= 2) {
                                ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI = INS_GET_PROOF_OF_KNOWLEDGE_PI(isoDep, apduCommand, user_defined_number_of_hidden_attributes);
                                if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                                    ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1 = INS_GET_PROOF_OF_KNOWLEDGE_CRED_1(isoDep, apduCommand);
                                    if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1.getS1_S2().equals(ApduCommandValues.SW1_SW2.MORE_DATA)) {
                                        ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2 = INS_GET_PROOF_OF_KNOWLEDGE_CRED_2(isoDep, apduCommand);
                                        if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                                            //Verification
                                            verifyAttributes(response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI, null, response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1, response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2, Integer.parseInt(sharedPreferences.getString(Constants.UserDefined.NUMBER_OF_ATTRIBUTES_KEY, "00")), cryptoCore);
                                        }
                                        //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                        else {
                                            showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                        }
                                    }
                                    //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                    else {
                                        showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                    }
                                }
                                //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                else {
                                    showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                }
                            }
                            //User Defined -- Three or more hidden attributes
                            else {
                                int totalSizeInBytes = 182 + user_defined_number_of_hidden_attributes * 32;
                                ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_1 = INS_GET_PROOF_OF_KNOWLEDGE_PI_1(isoDep, apduCommand, user_defined_number_of_hidden_attributes);
                                if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_1.getS1_S2().equals(ApduCommandValues.SW1_SW2.MORE_DATA)) {
                                    ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_2 = INS_GET_PROOF_OF_KNOWLEDGE_PI_2(isoDep, apduCommand, totalSizeInBytes);
                                    if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_2.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                                        ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1 = INS_GET_PROOF_OF_KNOWLEDGE_CRED_1(isoDep, apduCommand);
                                        if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1.getS1_S2().equals(ApduCommandValues.SW1_SW2.MORE_DATA)) {
                                            ApduResponseObject response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2 = INS_GET_PROOF_OF_KNOWLEDGE_CRED_2(isoDep, apduCommand);
                                            if (response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2.getS1_S2().equals(ApduCommandValues.SW1_SW2.SUCCESS)) {
                                                verifyAttributes(response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_1, response_to_INS_GET_PROOF_OF_KNOWLEDGE_PI_2, response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_1, response_to_INS_GET_PROOF_OF_KNOWLEDGE_CRED_2, Integer.parseInt(sharedPreferences.getString(Constants.UserDefined.NUMBER_OF_ATTRIBUTES_KEY, "00")), cryptoCore);
                                            }
                                            //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                            else {
                                                showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                            }
                                        }
                                        //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                        else {
                                            showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                        }
                                    }
                                    //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                    else {
                                        showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                    }
                                }
                                //Problem during INS_GET_PROOF_OF_KNOWLEDGE
                                else {
                                    showProblemMessage(Constants.SystemParameters.INS_GET_PROOF_OF_KNOWLEDGE_SIGN);
                                }
                            }
                        }
                    }
                    //Problem During INS_COMPUTE_PROOF_OF_KNOWLEDGE_SEQ_DISCLOSED
                    else {
                        //Problem during data exchange
                        showProblemMessage(Constants.SystemParameters.INS_COMPUTE_PROOF_OF_KNOWLEDGE_SEQ_DISCLOSED_SIGN);
                    }
                }
                //Problem During CMD_BIT_CHECKER
                else {
                    //Problem during data exchange
                    showProblemMessage(Constants.SystemParameters.INS_CMD_BIT_CHECKER_SIGN);
                }
            }
            //Problem during SELECT_AID
            else {
                showProblemMessage(Constants.SystemParameters.SELECT_AID_SIGN);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showProblemMessage(String instruction) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lottie_verification.cancelAnimation();
                lottie_verification.setAnimation(R.raw.problem);
                lottie_verification.setRepeatCount(0);
                lottie_verification.playAnimation();
                txt_verification_state.setText("Problem during " + instruction);
                txt_verification_state.setTextColor(Color.parseColor("#757575"));
            }
        });
    }

    private ApduResponseObject INS_GET_PROOF_OF_KNOWLEDGE_PI_2(IsoDep isoDep, ApduCommandObject apduCommand, int totalSizeInBytes) throws IOException {
        apduCommand.setINS(ApduCommandValues.INS.INS_GET_PROOF_OF_KNOWLEDGE);
        apduCommand.setP1(ApduCommandValues.P1.GET_PROOF_OF_KNOWLEDGE_P1_PI);
        apduCommand.setP2(ApduCommandValues.P2.GET_PROOF_OF_KNOWLEDGE_P2_TWO_MESSAGES);
        apduCommand.setDATA(EMPTY);
        apduCommand.setLC(EMPTY);
        //totalSizeInBytes - 250
        String LE = Integer.toHexString(totalSizeInBytes - 250);
        if (LE.length() < 2) {
            LE = "0" + LE;
        }
        apduCommand.setLE(LE);
        return new ApduResponseObject(Utils.byteArrayToHexString(isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()))));
    }

    private ApduResponseObject INS_GET_PROOF_OF_KNOWLEDGE_PI_1(IsoDep isoDep, ApduCommandObject apduCommand, int number_of_hidden_attributes) throws IOException {
        apduCommand.setINS(ApduCommandValues.INS.INS_GET_PROOF_OF_KNOWLEDGE);
        apduCommand.setP1(ApduCommandValues.P1.GET_PROOF_OF_KNOWLEDGE_P1_PI);
        apduCommand.setP2(ApduCommandValues.P2.GET_PROOF_OF_KNOWLEDGE_P2_TWO_MESSAGES);
        apduCommand.setDATA(EMPTY);
        apduCommand.setLC(EMPTY);
        //250 -> FA
        apduCommand.setLE("FA");
        return new ApduResponseObject(Utils.byteArrayToHexString(isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()))));
    }

    private void verifyAttributes(ApduResponseObject response_to_ins_get_proof_of_knowledge_pi_1, ApduResponseObject response_to_ins_get_proof_of_knowledge_pi_2, ApduResponseObject response_to_ins_get_proof_of_knowledge_cred_1, ApduResponseObject response_to_ins_get_proof_of_knowledge_cred_2, int number_of_attributes, CryptoCore cryptoCore) {
        String allData;
        if (response_to_ins_get_proof_of_knowledge_pi_2 == null) {
            allData = response_to_ins_get_proof_of_knowledge_pi_1.getData() + response_to_ins_get_proof_of_knowledge_cred_1.getData() + response_to_ins_get_proof_of_knowledge_cred_2.getData();
        } else {
            allData = response_to_ins_get_proof_of_knowledge_pi_1.getData() + response_to_ins_get_proof_of_knowledge_pi_2.getData() + response_to_ins_get_proof_of_knowledge_cred_1.getData() + response_to_ins_get_proof_of_knowledge_cred_2.getData();
        }
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.SystemParameters.E, allData.substring(0, 40));
        editor.putString(Constants.SystemParameters.S_V, allData.substring(40, 106));
        editor.putString(Constants.SystemParameters.S_I, allData.substring(106, 172));
        editor.putString(Constants.SystemParameters.S_E_I, allData.substring(172, 236));
        editor.putString(Constants.SystemParameters.S_E_II, allData.substring(236, 300));
        editor.putString(Constants.SystemParameters.S_M_R, allData.substring(300, 364));
        int indexHolder = 364;
        for (int i = 1; i <= number_of_attributes; i++) {
            if (sharedPreferences.getString(Constants.SystemParameters.M_ + i, "").equals("")) {
                editor.putString(Constants.SystemParameters.S_M + i, allData.substring(indexHolder, indexHolder + 64));
                indexHolder += 64;
            } else {
                continue;
            }
        }
        //Sigma_roof
        editor.putString(Constants.SystemParameters.SIGMA_ROOF, allData.substring(indexHolder, indexHolder + 130));
        indexHolder += 130;
        //Sigma_roof_eI
        editor.putString(Constants.SystemParameters.SIGMA_ROOF_E_I, allData.substring(indexHolder, indexHolder + 130));
        indexHolder += 130;
        //Sigma_roof_eII
        editor.putString(Constants.SystemParameters.SIGMA_ROOF_E_II, allData.substring(indexHolder, indexHolder + 130));
        indexHolder += 130;
        //Sigma_plane_eI
        editor.putString(Constants.SystemParameters.SIGMA_PLANE_E_I, allData.substring(indexHolder, indexHolder + 130));
        indexHolder += 130;
        //Sigma_plane_eII
        editor.putString(Constants.SystemParameters.SIGMA_PLANE_E_II, allData.substring(indexHolder, indexHolder + 130));
        indexHolder += 130;
        //C
        editor.putString(Constants.SystemParameters.C, allData.substring(indexHolder, indexHolder + 130));
        editor.commit();
        if (cryptoCore.verify()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    calendar = Calendar.getInstance();
                    currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                    int currentLogNumber = sharedPreferences.getInt(Constants.SystemParameters.NUMBER_OF_LOGS, 0);
                    editor.putInt(Constants.SystemParameters.NUMBER_OF_LOGS, currentLogNumber + 1);
                    editor.putString(Constants.SystemParameters.LOG_STATE + (currentLogNumber + 1), "Verified");
                    editor.putString(Constants.SystemParameters.LOG_DATE + (currentLogNumber + 1), currentDate);
                    editor.commit();
                    lottie_verification.cancelAnimation();
                    lottie_verification.setAnimation(R.raw.check);
                    lottie_verification.setRepeatCount(0);
                    lottie_verification.playAnimation();
                    txt_verification_state.setText("Verified !");
                    txt_verification_state.setTextColor(Color.parseColor("#4CAF50"));
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    calendar = Calendar.getInstance();
                    currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                    int currentLogNumber = sharedPreferences.getInt(Constants.SystemParameters.NUMBER_OF_LOGS, 0);
                    editor.putInt(Constants.SystemParameters.NUMBER_OF_LOGS, currentLogNumber + 1);
                    editor.putString(Constants.SystemParameters.LOG_STATE + currentLogNumber + 1, "Not verified");
                    editor.putString(Constants.SystemParameters.LOG_DATE + currentLogNumber + 1, currentDate);
                    editor.commit();
                    lottie_verification.cancelAnimation();
                    lottie_verification.setAnimation(R.raw.wrong);
                    lottie_verification.setRepeatCount(0);
                    lottie_verification.playAnimation();
                    txt_verification_state.setText("Not Verified !");
                    txt_verification_state.setTextColor(Color.parseColor("#F44336"));
                }
            });
        }


    }

    private ApduResponseObject INS_GET_PROOF_OF_KNOWLEDGE_CRED_2(IsoDep isoDep, ApduCommandObject apduCommand) throws IOException {
        apduCommand.setINS(ApduCommandValues.INS.INS_GET_PROOF_OF_KNOWLEDGE);
        apduCommand.setLE(Constants.SystemParameters.BYTES_140);
        return new ApduResponseObject(Utils.byteArrayToHexString(isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()))));
    }

    private ApduResponseObject INS_GET_PROOF_OF_KNOWLEDGE_CRED_1(IsoDep isoDep, ApduCommandObject apduCommand) throws IOException {
        apduCommand.setINS(ApduCommandValues.INS.INS_GET_PROOF_OF_KNOWLEDGE);
        apduCommand.setP1(ApduCommandValues.P1.GET_PROOF_OF_KNOWLEDGE_P1_CRED);
        apduCommand.setP2(ApduCommandValues.P2.GET_PROOF_OF_KNOWLEDGE_P2_TWO_MESSAGES);
        apduCommand.setDATA(EMPTY);
        apduCommand.setLC(EMPTY);
        //FA -> 250
        apduCommand.setLE(Constants.SystemParameters.BYTES_250);
        return new ApduResponseObject(Utils.byteArrayToHexString(isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()))));
    }

    private ApduResponseObject INS_GET_PROOF_OF_KNOWLEDGE_PI(IsoDep isoDep, ApduCommandObject apduCommand, int number_of_hidden_attributes) throws IOException {
        apduCommand.setINS(ApduCommandValues.INS.INS_GET_PROOF_OF_KNOWLEDGE);
        apduCommand.setP1(ApduCommandValues.P1.GET_PROOF_OF_KNOWLEDGE_P1_PI);
        apduCommand.setP2(ApduCommandValues.P2.GET_PROOF_OF_KNOWLEDGE_P2_ONE_MESSAGE);
        apduCommand.setDATA(EMPTY);
        apduCommand.setLC(EMPTY);
        //LE setup
        String LE_hex = Integer.toHexString(182 + 32 * number_of_hidden_attributes);
        if (LE_hex.length() < 2) {
            LE_hex = "0" + LE_hex;
        }
        apduCommand.setLE(LE_hex);
        return new ApduResponseObject(Utils.byteArrayToHexString(isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()))));
    }

    private ApduResponseObject INS_COMPUTE_PROOF_OF_KNOWLEDGE_SEQ_DISCLOSED(IsoDep isoDep, ApduCommandObject apduCommand, CryptoCore cryptoCore) throws IOException {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //Initializing EPOCH
        //String EPOCH = sharedPreferences.getString(Constants.SystemParameters.EPOCH, "00000000");
        String EPOCH = "01280421";
        //Generating NONCE
        //String NONCE = cryptoCore.generateNonce().toUpperCase();
        String NONCE = "6237EB155B271AB642156C1F36AF4F0ED056C2E93E994909E41CB421E7391FDB";
        editor.putString(Constants.SystemParameters.NONCE, NONCE);
        editor.commit();
        apduCommand.setINS(ApduCommandValues.INS.INS_COMPUTE_PROOF_OF_KNOWLEDGE_SEQ_DISCLOSED);
        apduCommand.setLC(Utils.dataLengthCounter(NONCE + EPOCH));
        apduCommand.setDATA(NONCE + EPOCH);
        apduCommand.setLE(EMPTY);
        return new ApduResponseObject(Utils.byteArrayToHexString(isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()))));
    }

    private ApduResponseObject CMD_TEST_BIT_CHECKER(IsoDep isoDep, ApduCommandObject apduCommand) throws IOException {
        apduCommand.setCLA(ApduCommandValues.CLA.PEAS_CLA);
        apduCommand.setINS(ApduCommandValues.INS.CMD_TEST_BIT_CHECKER);
        //Setting P1
        apduCommand.setP1(setP1_CMD_TEST_BIT_CHECKER());
        //Setting P2
        apduCommand.setP2(setP2_CMD_TEST_BIT_CHECKER());
        apduCommand.setLC(EMPTY);
        apduCommand.setDATA(EMPTY);
        apduCommand.setLE(EMPTY);
        return new ApduResponseObject(Utils.byteArrayToHexString(isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()))));
    }


    private void resetValuesFromDatabase() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 1; i <= 9; i++) {
            editor.putString(Constants.SystemParameters.S_M + i, "");
        }
        editor.commit();


    }


    private String setP2_CMD_TEST_BIT_CHECKER() {
        //Initialization of SharedPreferences
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
        //EID
        if (sharedPreferences.getBoolean(Constants.Eid.EID_CRYPTO_CREDENCIAL, false)) {
            String P2_binary = "0000";
            int number_of_hidden_attributes_int = Integer.parseInt(Constants.Eid.EID_NUMBER_OF_ATTRIBUTES, 16);
            //Name and surname
            if (sharedPreferences.getBoolean(Constants.Eid.NAME_SURNAME_EID_SWITCH, false)) {
                number_of_hidden_attributes_int--;
            }
            //Birthdate
            if (sharedPreferences.getBoolean(Constants.Eid.BIRTHDATE_EID_SWITCH, false)) {
                number_of_hidden_attributes_int--;
            }
            //Nationality
            if (sharedPreferences.getBoolean(Constants.Eid.NATIONALITY_EID_SWITCH, false)) {
                number_of_hidden_attributes_int--;
            }
            //Permanent residence
            if (sharedPreferences.getBoolean(Constants.Eid.PERMANENT_RESIDENCE_EID_SWITCH, false)) {
                number_of_hidden_attributes_int--;
            }
            //Sex
            if (sharedPreferences.getBoolean(Constants.Eid.SEX_EID_SWITCH, false)) {
                number_of_hidden_attributes_int--;
            }
            String number_of_hidden_attributes_binary = Integer.toBinaryString(number_of_hidden_attributes_int);
            if (number_of_hidden_attributes_binary.length() < 4) {
                int prefix_length = 4 - number_of_hidden_attributes_binary.length();
                for (int i = 0; i < prefix_length; i++) {
                    number_of_hidden_attributes_binary = "0" + number_of_hidden_attributes_binary;
                }
            }
            String P2_hex = Integer.toHexString(Utils.binaryToDecimal((P2_binary + number_of_hidden_attributes_binary)));
            if (P2_hex.length() < 2) {
                P2_hex = "0" + P2_hex;
            }
            return P2_hex;
        }
        //TICKET
        else if (sharedPreferences.getBoolean(Constants.Ticket.TICKET_CRYPTO_CREDENCIAL, false)) {
            String P2_binary = "0000";
            int number_of_hidden_attributes_int = Integer.parseInt(Constants.Ticket.TICKET_NUMBER_OF_ATTRIBUTES, 16);
            //Name and surname
            if (sharedPreferences.getBoolean(Constants.Ticket.NAME_SURNAME_TICKET_SWITCH, false)) {
                number_of_hidden_attributes_int--;
            }
            //Card number
            if (sharedPreferences.getBoolean(Constants.Ticket.CARD_NUMBER_TICKET_SWITCH, false)) {
                number_of_hidden_attributes_int--;
            }
            //Type of ticket
            if (sharedPreferences.getBoolean(Constants.Ticket.TYPE_OF_TICKET_TICKET_SWITCH, false)) {
                number_of_hidden_attributes_int--;
            }
            String number_of_hidden_attributes_binary = Integer.toBinaryString(number_of_hidden_attributes_int);
            if (number_of_hidden_attributes_binary.length() < 4) {
                int prefix_length = 4 - number_of_hidden_attributes_binary.length();
                for (int i = 0; i < prefix_length; i++) {
                    number_of_hidden_attributes_binary = "0" + number_of_hidden_attributes_binary;
                }
            }
            String P2_hex = Integer.toHexString(Utils.binaryToDecimal((P2_binary + number_of_hidden_attributes_binary)));
            if (P2_hex.length() < 2) {
                P2_hex = "0" + P2_hex;
            }
            return P2_hex;
        }
        //EMPLOYEE CARD
        else if (sharedPreferences.getBoolean(Constants.EmployeeCard.EMPLOYEE_CARD_CRYPTO_CREDENCIAL, false)) {
            String P2_binary = "0000";
            int number_of_hidden_attributes_int = Integer.parseInt(Constants.EmployeeCard.EMPLOYEE_CARD_NUMBER_OF_ATTRIBUTES, 16);
            //Name and surname
            if (sharedPreferences.getBoolean(Constants.EmployeeCard.NAME_SURNAME_EMPLOYEE_CARD_SWITCH, false)) {
                number_of_hidden_attributes_int--;
            }
            //Employee ID
            if (sharedPreferences.getBoolean(Constants.EmployeeCard.EMPLOYEE_ID_EMPLOYEE_CARD_SWITCH, false)) {
                number_of_hidden_attributes_int--;
            }
            //Employer
            if (sharedPreferences.getBoolean(Constants.EmployeeCard.EMPLOYER_EMPLOYEE_CARD_SWITCH, false)) {
                number_of_hidden_attributes_int--;
            }
            //Employee position
            if (sharedPreferences.getBoolean(Constants.EmployeeCard.EMPLOYEE_POSITION_EMPLOYEE_CARD_SWITCH, false)) {
                number_of_hidden_attributes_int--;
            }
            String number_of_hidden_attributes_binary = Integer.toBinaryString(number_of_hidden_attributes_int);
            if (number_of_hidden_attributes_binary.length() < 4) {
                int prefix_length = 4 - number_of_hidden_attributes_binary.length();
                for (int i = 0; i < prefix_length; i++) {
                    number_of_hidden_attributes_binary = "0" + number_of_hidden_attributes_binary;
                }
            }
            String P2_hex = Integer.toHexString(Utils.binaryToDecimal((P2_binary + number_of_hidden_attributes_binary)));
            if (P2_hex.length() < 2) {
                P2_hex = "0" + P2_hex;
            }
            return P2_hex;
        }
        //USER DEFINED
        else if (sharedPreferences.getBoolean(Constants.UserDefined.USER_DEFINED_CRYPTO_CREDENCIAL, false)) {
            String P2_binary = "000";
            //Attribute 9
            if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_9_SWITCH, false)) {
                P2_binary = "1" + P2_binary;
            } else {
                P2_binary = "0" + P2_binary;
            }
            int number_of_hidden_attributes_int = sharedPreferences.getInt(Constants.UserDefined.USER_DEFINED_NUMBER_OF_HIDDEN_ATTRIBUTES, 0);
            String number_of_hidden_attributes_binary = Integer.toBinaryString(number_of_hidden_attributes_int);
            if (number_of_hidden_attributes_binary.length() < 4) {
                int prefix_length = 4 - number_of_hidden_attributes_binary.length();
                for (int i = 0; i < prefix_length; i++) {
                    number_of_hidden_attributes_binary = "0" + number_of_hidden_attributes_binary;
                }
            }
            String P2_hex = Integer.toHexString(Utils.binaryToDecimal((P2_binary + number_of_hidden_attributes_binary)));
            if (P2_hex.length() < 2) {
                P2_hex = "0" + P2_hex;
            }
            return P2_hex;
        } else {
            return ZERO_BYTE;
        }
    }

    private String setP1_CMD_TEST_BIT_CHECKER() {
        //Initialization of SharedPreferences
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
        //EID
        if (sharedPreferences.getBoolean(Constants.Eid.EID_CRYPTO_CREDENCIAL, false)) {
            String P1_binary = "";
            //Name and surname
            if (sharedPreferences.getBoolean(Constants.Eid.NAME_SURNAME_EID_SWITCH, false)) {
                P1_binary = "1";
            } else {
                P1_binary = "0";
            }
            //Birthdate
            if (sharedPreferences.getBoolean(Constants.Eid.BIRTHDATE_EID_SWITCH, false)) {
                P1_binary += "1";
            } else {
                P1_binary += "0";
            }
            //Nationality
            if (sharedPreferences.getBoolean(Constants.Eid.NATIONALITY_EID_SWITCH, false)) {
                P1_binary += "1";
            } else {
                P1_binary += "0";
            }
            //Permanent residence
            if (sharedPreferences.getBoolean(Constants.Eid.PERMANENT_RESIDENCE_EID_SWITCH, false)) {
                P1_binary += "1";
            } else {
                P1_binary += "0";
            }
            //Sex
            if (sharedPreferences.getBoolean(Constants.Eid.SEX_EID_SWITCH, false)) {
                P1_binary += "1";
            } else {
                P1_binary += "0";
            }
            //EID has 5 attributes in total -> Its necessary to add "000" suffix
            P1_binary += "000";
            String P1_hex = Integer.toHexString(Utils.binaryToDecimal(P1_binary));
            if (P1_hex.length() < 2) {
                P1_hex = "0" + P1_hex;
            }
            return P1_hex;

        }
        //TICKET
        else if (sharedPreferences.getBoolean(Constants.Ticket.TICKET_CRYPTO_CREDENCIAL, false)) {
            String P1_binary = "";
            //Name and surname
            if (sharedPreferences.getBoolean(Constants.Ticket.NAME_SURNAME_TICKET_SWITCH, false)) {
                P1_binary = "1";
            } else {
                P1_binary = "0";
            }
            //Card number
            if (sharedPreferences.getBoolean(Constants.Ticket.CARD_NUMBER_TICKET_SWITCH, false)) {
                P1_binary += "1";
            } else {
                P1_binary += "0";
            }
            //Type of ticket
            if (sharedPreferences.getBoolean(Constants.Ticket.TYPE_OF_TICKET_TICKET_SWITCH, false)) {
                P1_binary += "1";
            } else {
                P1_binary += "0";
            }
            //TICKET has 3 attributes in total -> Its necessary to add "00000" suffix
            P1_binary += "00000";
            String P1_hex = Integer.toHexString(Utils.binaryToDecimal(P1_binary));
            if (P1_hex.length() < 2) {
                P1_hex = "0" + P1_hex;
            }
            return P1_hex;
        }
        //EMPLOYEE CARD
        else if (sharedPreferences.getBoolean(Constants.EmployeeCard.EMPLOYEE_CARD_CRYPTO_CREDENCIAL, false)) {
            String P1_binary = "";
            //Name and surname
            if (sharedPreferences.getBoolean(Constants.EmployeeCard.NAME_SURNAME_EMPLOYEE_CARD_SWITCH, false)) {
                P1_binary = "1";
            } else {
                P1_binary = "0";
            }
            //Employee ID
            if (sharedPreferences.getBoolean(Constants.EmployeeCard.EMPLOYEE_ID_EMPLOYEE_CARD_SWITCH, false)) {
                P1_binary += "1";
            } else {
                P1_binary += "0";
            }
            //Employer
            if (sharedPreferences.getBoolean(Constants.EmployeeCard.EMPLOYER_EMPLOYEE_CARD_SWITCH, false)) {
                P1_binary += "1";
            } else {
                P1_binary += "0";
            }
            //Employer
            if (sharedPreferences.getBoolean(Constants.EmployeeCard.EMPLOYEE_POSITION_EMPLOYEE_CARD_SWITCH, false)) {
                P1_binary += "1";
            } else {
                P1_binary += "0";
            }
            //TICKET has 4 attributes in total -> Its necessary to add "0000" suffix
            P1_binary += "0000";
            String P1_hex = Integer.toHexString(Utils.binaryToDecimal(P1_binary));
            if (P1_hex.length() < 2) {
                P1_hex = "0" + P1_hex;
            }
            return P1_hex;
        }
        //USER DEFINED
        else if (sharedPreferences.getBoolean(Constants.UserDefined.USER_DEFINED_CRYPTO_CREDENCIAL, false)) {
            String P1_binary = "";
            //Attribute 1
            if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_1_SWITCH, false)) {
                P1_binary = "1";
            } else {
                P1_binary = "0";
            }
            //Attribute 2
            if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_2_SWITCH, false)) {
                P1_binary += "1";
            } else {
                P1_binary += "0";
            }
            //Attribute 3
            if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_3_SWITCH, false)) {
                P1_binary += "1";
            } else {
                P1_binary += "0";
            }
            //Attribute 4
            if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_4_SWITCH, false)) {
                P1_binary += "1";
            } else {
                P1_binary += "0";
            }
            //Attribute 5
            if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_5_SWITCH, false)) {
                P1_binary += "1";
            } else {
                P1_binary += "0";
            }
            //Attribute 6
            if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_6_SWITCH, false)) {
                P1_binary += "1";
            } else {
                P1_binary += "0";
            }
            //Attribute 7
            if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_7_SWITCH, false)) {
                P1_binary += "1";
            } else {
                P1_binary += "0";
            }
            //Attribute 8
            if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_8_SWITCH, false)) {
                P1_binary += "1";
            } else {
                P1_binary += "0";
            }
            String P1_hex = Integer.toHexString(Utils.binaryToDecimal(P1_binary));
            if (P1_hex.length() < 2) {
                P1_hex = "0" + P1_hex;
            }
            return P1_hex;
        } else {
            return ZERO_BYTE;
        }
    }

}

