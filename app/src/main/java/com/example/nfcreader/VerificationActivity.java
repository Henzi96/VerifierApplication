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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        txt_verification_state = findViewById(R.id.txt_verification_state);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        Calendar calendar = Calendar.getInstance();
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
                String data = "1000000000000018".toUpperCase();
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
                apduCommand.setDATA("02EB0EFD35BDFCB96132BA9BB3864339FA34C0EBF18B6C75A4021DDC5DE2F8A6040FD842AFC0C05A0859013C105B51E0CFD11960D56B128ED1902588D60F846F3D1421478FFFFC016932E4B7F840EC2FBAAE345FB5D69FE2B9D1F69DF73C07ACCE");
                apduCommand.setLE("");
                final byte[] response2 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                if (Utils.byteArrayToHexString(response2).toUpperCase().equals("91AF")) {
                    apduCommand.setCLA("70");
                    apduCommand.setINS("02");
                    apduCommand.setP1("02");
                    apduCommand.setP2("06");
                    apduCommand.setLC("FA");
                    apduCommand.setDATA("0A0205A53FD9729845FCEA8B1299E2B0EDC492AB3FEA001DDCF39B06871E3244BDB60083FB78C9B7B18FF87085750B794037E954885934FB2229ADDCC5DA3AF7F29304057FF33DC2B982D9962275749491FC74536F3F06C42376AA4C5561C08E84437E1BA706E53D9B0F1B3FD592A2B61DF28EF8EB62AA54749EFEE101D5CB8F839C470406B0C79FBFC05C9448035B96A44555506F962D16873B5BCD4778F0FD36B3D78B1094DE5EA56593CCEC7E9E6E4E558D7FE5A05438EDF564CDAB25D2974AB1339F001C12FE621F6FF75A3B115364521C970CF8C853B9CD379D46E41E36C29553EB34000B6CA5E8F9CF28690A1EC4CBA20066B49DD0CC53");
                    apduCommand.setLE("");
                    final byte[] response3 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                    if (Utils.byteArrayToHexString(response3).toUpperCase().equals("91AF")) {
                        apduCommand.setCLA("70");
                        apduCommand.setINS("02");
                        apduCommand.setP1("03");
                        apduCommand.setP2("06");
                        apduCommand.setLC("FA");
                        apduCommand.setDATA("9AB480B0EB9E4BFA31E6A461001B2D87CC4836852ED28F08E8C2888254F84100FC45421E770956E1408E8D5B37001CECD66821C5C0E391CDB5E89A1283FB537AB707917E771957FD08F6CE62CE4E00135C31641DD175EB263A5935ACBBECCF2E710750126D59F436BC0033EE4215EC001FF2B2C1828F0C316139AFA9C923677577DECEEFDA436E0392AA6F43004B76BE0008EF5E289F2A7F70E625EA3F17B9D3E2D04F8C7C73A52FD9E7EC7F8FA46467130016B0FF830F8B4FC2BF1B5A9EC65AE7FC74077DF02841FF6F2230BF311E65B9BE0022C28C729E65FE67A7B81D7AA5906475AB735481A771EFECBC25312777EA24A00023D8F07CF2BD");
                        apduCommand.setLE("");
                        final byte[] response4 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                        if (Utils.byteArrayToHexString(response4).toUpperCase().equals("91AF")) {
                            apduCommand.setCLA("70");
                            apduCommand.setINS("02");
                            apduCommand.setP1("04");
                            apduCommand.setP2("06");
                            apduCommand.setLC("FA");
                            apduCommand.setDATA("228DD35F6207A62460B324534966BBABD0DCC1784E896530A15504229BF826CF5679DD76E8DA7FC15CFCE294BAA08D658C7941C86A214C085E418317A8779CBD33E96B2EF6AE965C955102836545829F299DD5C015CEE9F3F58A8F04243CBA4B2057C0C8F76606F56597B5A0294C6F3AB4F2FA051E14770626E8CFC32458BE01B019E1B10F7D7B759F4B206190AFD92B7239992736C61ACAACAD6AA9041553242BEA60957B3F0A1313014EF12AE233B84C3835A7EE0317067010D7F34E13EB015633B35D15D9DB517E2F485FD30BE0B056DA06F17F9E372F81ADDD7E2204223360FFC1024E09A326EFCAEB6301E364ECE4800971C881EB2C3C2F");
                            apduCommand.setLE("");
                            final byte[] response5 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                            if (Utils.byteArrayToHexString(response5).toUpperCase().equals("91AF")) {
                                apduCommand.setCLA("70");
                                apduCommand.setINS("02");
                                apduCommand.setP1("05");
                                apduCommand.setP2("06");
                                apduCommand.setLC("FA");
                                apduCommand.setDATA("964A25C40CE613D0C7CE086503CCCB8D91F9492EA3E8B079860128FDBBAA6F210EE50B49041A4D069D4BDE557A481E8E3A98ACA8F8EF58A89BB305B3A38A17F20F10DABAF30943B51DCD2338A56A3D2E8C1DB41F9CC7364FE05D0ED20A4C117F08265BD51904155E3AACE907A094F31153BD656C36B3D686009A1CA93EFA946DAC9C5B764DBE01550A0A6AAEFA2294BDF9CAB06DD5CD98A6DD608FB4F68C2ED7F5CDE54B44F40423237BF93DC921CEAE23C2B892949CCC57C97BA882C570AECE2647B9C5EB80F41FCA7C66E3ED64CF39B4CE2A80CEDFD606BF6A65DD28C844C02F818AAFAA04920418E3C0FAC36DAA1230F161EABE443C199792");
                                apduCommand.setLE("");
                                final byte[] response6 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                                if (Utils.byteArrayToHexString(response6).toUpperCase().equals("91AF")) {
                                    apduCommand.setCLA("70");
                                    apduCommand.setINS("02");
                                    apduCommand.setP1("06");
                                    apduCommand.setP2("06");
                                    apduCommand.setLC("B0");
                                    apduCommand.setDATA("EF29CAC345BF78D3421634396815071F0F830BBE78185D0B737B77E2F125AA38B0C34DF752E6BC4A228CEB97F1BB041016E41CA9E0CB59D5B640D3BFB4976C71378A47B4F9C1ED8AC4D32D1765238600EA8F5BD9670F89174381FD44F974D8C620AF89196DE67E03A5EF3DD23791B8040FCC1D32956D64553F49E09E425D17AD04862859AC241AD8DE868ED2B82832351577D2492F6A7338D17DDF73E6CF54EFA3CED995B12EBF2FF42E38747B0A5ED9");
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
                String data = "041470BB304307466D4816CE6EE189049BAF5EAD325DF8F00575CC151F20A7E0F90C0AE08438B2AD60762A361ECD3209F892E7030231A1ED154EAE997487E4DA7D04207F7F980C0F32EAE7BAFD5D8BCBB996C5B473D21C003903AEFF0374E6D58DC1105D147E27865280BD946A821B0D98015F26CF186F95C368BD72E773C0B6FC4C04092022BF3C5E476B80855A34E6D7A599DC56677C50458CC6E480AFCE0AE085A80DA99223375D00B4DA3E5ABFCA6285C165FAB82C5C9047AFF2F7858928C9941B040637C97870032862363ACC6806793BBB5AB49B0E7D166FEA5FCB26BFFD50642B1510D38DF9EB680902DB990DB76881C0DC455274CD75";
                apduCommand.setCLA("70");
                apduCommand.setINS("05");
                apduCommand.setP1("01");
                apduCommand.setP2("02");
                apduCommand.setLC(Utils.dataLengthCounter(data));
                apduCommand.setDATA(data);
                apduCommand.setLE("");
                final byte[] response2 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                if (Utils.byteArrayToHexString(response2).toUpperCase().equals("91AF")) {
                    data = "C7D05DB256CFD50F840B0405C139A45BD9A5B1A41964AE858301B6911D7F4E8307214B8CFE34E75253BB2E02BA6ABF5081CAC8D103C9E202EB372EC7265F195861F6F0BC94848754DA4FBE04012BD3F132C15046B61FF0348023D4F03354CEF5828442E7F23DF9BEA83A7604198E7F093840F235DAF67331197F71F1A486CC663868E8DCF850784EE88448B2040A198C2D4388C9DE9DED8937E13B3ADF817A249F8683A6430704212F6B93C6FD04E13EABCA3A24BA799CC488432E887E848870A72E760EB731D797FCA0C0419C";
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
                apduCommand.setP1("10");
                apduCommand.setP2("04");
                apduCommand.setLC("");
                apduCommand.setDATA("");
                apduCommand.setLE("");
                final byte[] response2 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                System.out.println(Utils.byteArrayToHexString(response2));
            }*/

                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("\nCard Response n.2: " + (Utils.byteArrayToHexString(response2)));
                    }
                });

            }*/
            //INS_COMPUTE_PROOF_OF_KNOWLEDGE_SEQ_DISCLOSED
/*            if (Utils.byteArrayToHexString(response).equals("9000")) {
                String data = "6D0BC4908C9733F7760234FBD2A9429D5956115317DEAFDEDBB82AD2BFC8016602171220";
                apduCommand.setCLA("70");
                apduCommand.setINS("0A");
                apduCommand.setP1("00");
                apduCommand.setP2("00");
                apduCommand.setLC(Utils.dataLengthCounter(data));
                apduCommand.setDATA(data);
                apduCommand.setLE("");
                final byte[] response2 = isoDep.transceive(Utils.hexStringToByteArray(apduCommand.toString()));
                System.out.println(Utils.byteArrayToHexString(response2));

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
        //C
        editor.putString(Constants.SystemParameters.C, allData.substring(indexHolder, indexHolder + 130));
        indexHolder += 130;
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
        editor.commit();
        if (cryptoCore.verify()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
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
        String EPOCH = sharedPreferences.getString(Constants.SystemParameters.EPOCH, "00000000");
        //Generating NONCE
        String NONCE = cryptoCore.generateNonce().toUpperCase();
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

