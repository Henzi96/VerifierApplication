package com.example.nfcreader;

import android.content.Context;
import android.content.SharedPreferences;

import com.herumi.mcl.Fr;
import com.herumi.mcl.G1;
import com.herumi.mcl.G2;
import com.herumi.mcl.GT;
import com.herumi.mcl.Mcl;
import com.herumi.mcl.MclConstants;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Random;


public class CryptoCore {

    private static final Object SHA1_PADDING = "000000000000000000000000";
    //Context reference
    private Context context;
    //Generator g1
    String BN256_g1_hex = "1 2523648240000001BA344D80000000086121000000000013A700000000000012 0000000000000000000000000000000000000000000000000000000000000001";
    //Order of BN256 as a BigInteger
    BigInteger BN256_q = new BigInteger("2523648240000001ba344d8000000007ff9f800000000010a10000000000000d", 16);
    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    long startTime = 0;
    long stopTime = 0;

    CryptoCore(Context context) {
        this.context = context;
        Mcl.SystemInit(MclConstants.BN254);
    }

    //Loads the native library specified by the library name
    static {
        String lib = "mcljava";
        System.loadLibrary(lib);
    }

    public boolean verify() {
        startTime = System.nanoTime();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SystemParameters.VERIFIER_DATA, Context.MODE_PRIVATE);
        //t_verify
        G1 t_verify = compute_t_verify(sharedPreferences);
        //t_revoke
        G1 t_revoke = compute_t_revoke(sharedPreferences);
        //t_sig
        G1 t_sig = compute_t_sig(sharedPreferences);
        //t_sigI
        G1 t_sigI = compute_t_sigI(sharedPreferences);
        //t_sigII
        G1 t_sigII = compute_t_sigII(sharedPreferences);
        //e
        Fr e = compute_e(t_verify, t_revoke, t_sig, t_sigI, t_sigII, sharedPreferences);
        //e check
        boolean e_equals = check_e_equals(e, sharedPreferences);
        //First pairing check
        boolean first_pairing_equals = verify_first_pairing(sharedPreferences);
        //Second pairing check
        boolean second_pairing_equals = verify_second_pairing(sharedPreferences);
        stopTime = System.nanoTime();
        System.out.println("TIME crypto: " + (stopTime-startTime));
        return (e_equals && first_pairing_equals && second_pairing_equals);
    }

    private boolean verify_first_pairing(SharedPreferences sharedPreferences) {
        //g2 initialization
        G2 g2 = new G2();
        g2.setStr("1 12723517038133731887338407189719511622662176727675373276651903807414909099441 4168783608814932154536427934509895782246573715297911553964171371032945126671 13891744915211034074451795021214165905772212241412891944830863846330766296736 7937318970632701341203597196594272556916396164729705624521405069090520231616");
        //pk_RA initialization
        G2 pk_ra = new G2();
        pk_ra.deserialize(Utils.hexStringToByteArray(sharedPreferences.getString(Constants.SystemParameters.PK_RA, "00")));
        // e(sigma_plane_eI, g2)
        GT e1 = new GT();
        GT e2 = new GT();
        G1 sigma_plane_e_I = new G1();
        sigma_plane_e_I.setStr(databaseCurvePointToMcl(Constants.SystemParameters.SIGMA_PLANE_E_I, sharedPreferences), 16);
        G1 sigma_roof_e_I = new G1();
        sigma_roof_e_I.setStr(databaseCurvePointToMcl(Constants.SystemParameters.SIGMA_ROOF_E_I, sharedPreferences), 16);
        com.herumi.mcl.Mcl.pairing(e1, sigma_plane_e_I, g2);
        com.herumi.mcl.Mcl.pairing(e2, sigma_roof_e_I, pk_ra);
        return e1.equals(e2);
    }

    private boolean verify_second_pairing(SharedPreferences sharedPreferences) {
        //g2 initialization
        G2 g2 = new G2();
        g2.setStr("1 12723517038133731887338407189719511622662176727675373276651903807414909099441 4168783608814932154536427934509895782246573715297911553964171371032945126671 13891744915211034074451795021214165905772212241412891944830863846330766296736 7937318970632701341203597196594272556916396164729705624521405069090520231616");
        //pk_RA initialization
        G2 pk_ra = new G2();
        pk_ra.deserialize(Utils.hexStringToByteArray(sharedPreferences.getString(Constants.SystemParameters.PK_RA, "00")));
        // e(sigma_plane_eI, g2)
        GT e1 = new GT();
        GT e2 = new GT();
        G1 sigma_plane_e_II = new G1();
        sigma_plane_e_II.setStr(databaseCurvePointToMcl(Constants.SystemParameters.SIGMA_PLANE_E_II, sharedPreferences), 16);
        G1 sigma_roof_e_II = new G1();
        sigma_roof_e_II.setStr(databaseCurvePointToMcl(Constants.SystemParameters.SIGMA_ROOF_E_II, sharedPreferences), 16);
        com.herumi.mcl.Mcl.pairing(e1, sigma_plane_e_II, g2);
        com.herumi.mcl.Mcl.pairing(e2, sigma_roof_e_II, pk_ra);
        return e1.equals(e2);
    }

    private boolean check_e_equals(Fr e, SharedPreferences sharedPreferences) {
        Fr e_from_user = new Fr(sharedPreferences.getString(Constants.SystemParameters.E, "00"), 16);
        return e_from_user.equals(e);
    }

    private Fr compute_e(G1 t_verify, G1 t_revoke, G1 t_sig, G1 t_sigI, G1 t_sigII, SharedPreferences sharedPreferences) {
        //t_verify
        String t_verify_HEX = mclCurvePointToDatabase(t_verify);
        //t_revoke
        String t_revoke_HEX = mclCurvePointToDatabase(t_revoke);
        //t_sig
        String t_sig_HEX = mclCurvePointToDatabase(t_sig);
        //t_sigI
        String t_sig_I_HEX = mclCurvePointToDatabase(t_sigI);
        //t_sigII
        String t_sig_II_HEX = mclCurvePointToDatabase(t_sigII);
        //sigma_roof
        G1 sigma_roof = new G1();
        sigma_roof.setStr(databaseCurvePointToMcl(Constants.SystemParameters.SIGMA_ROOF, sharedPreferences), 16);
        String sigma_roof_HEX = mclCurvePointToDatabase(sigma_roof);
        //sigma_roof_eI
        G1 sigma_roof_eI = new G1();
        sigma_roof_eI.setStr(databaseCurvePointToMcl(Constants.SystemParameters.SIGMA_ROOF_E_I, sharedPreferences), 16);
        String sigma_roof_eI_HEX = mclCurvePointToDatabase(sigma_roof_eI);
        //sigma_plane_eI
        G1 sigma_plane_eI = new G1();
        sigma_plane_eI.setStr(databaseCurvePointToMcl(Constants.SystemParameters.SIGMA_PLANE_E_I, sharedPreferences), 16);
        String sigma_plane_eI_HEX = mclCurvePointToDatabase(sigma_plane_eI);
        //sigma_roof_eII
        G1 sigma_roof_eII = new G1();
        sigma_roof_eII.setStr(databaseCurvePointToMcl(Constants.SystemParameters.SIGMA_ROOF_E_II, sharedPreferences), 16);
        String sigma_roof_eII_HEX = mclCurvePointToDatabase(sigma_roof_eII);
        //sigma_plane_eII
        G1 sigma_plane_eII = new G1();
        sigma_plane_eII.setStr(databaseCurvePointToMcl(Constants.SystemParameters.SIGMA_PLANE_E_II, sharedPreferences), 16);
        String sigma_plane_eII_HEX = mclCurvePointToDatabase(sigma_plane_eII);
        //C
        G1 C = new G1();
        C.setStr(databaseCurvePointToMcl(Constants.SystemParameters.C, sharedPreferences), 16);
        String C_HEX = mclCurvePointToDatabase(C);
        //nonce
        String nonce_HEX = sharedPreferences.getString(Constants.SystemParameters.NONCE, "00");
        //hash
        String hash = SHA1(t_verify_HEX + t_revoke_HEX + t_sig_HEX + t_sig_I_HEX + t_sig_II_HEX + sigma_roof_HEX + sigma_roof_eI_HEX + sigma_roof_eII_HEX + sigma_plane_eI_HEX + sigma_plane_eII_HEX + C_HEX + nonce_HEX);
        BigInteger hash_mod_q_BigInt = new BigInteger(SHA1_PADDING + hash, 16);
        hash_mod_q_BigInt.mod(BN256_q);
        hash = hash_mod_q_BigInt.toString(16);
        Fr e = new Fr();
        e.setStr(hash, 16);
        return e;
    }


    private String mclCurvePointToDatabase(G1 curvePoint) {
        String curvePointMcl = curvePoint.toString();
        String x_decimal_string = "";
        String y_decimal_string = "";
        int indexHolder = 2;
        for (int i = 2; i < curvePointMcl.length(); i++) {
            if (!curvePointMcl.substring(i, i + 1).equals(" ")) {
                x_decimal_string += curvePointMcl.substring(i, i + 1);
                indexHolder++;
            } else {
                indexHolder++;
                break;
            }
        }
        for (int i = indexHolder; i < curvePointMcl.length(); i++) {
            y_decimal_string += curvePointMcl.substring(i, i + 1);
        }
        BigInteger x_decimal = new BigInteger(x_decimal_string);
        BigInteger y_decimal = new BigInteger(y_decimal_string);
        String x_hex = x_decimal.toString(16);
        String y_hex = y_decimal.toString(16);
        if (x_hex.length() < 64) {
            String prefix_x = "";
            int number_of_zeros = 64 - x_hex.length();
            for (int i = 0; i < number_of_zeros; i++) {
                prefix_x += "0";
            }
            x_hex = prefix_x + x_hex;
        }
        if (y_hex.length() < 64) {
            String prefix_y = "";
            int number_of_zeros = 64 - y_hex.length();
            for (int i = 0; i < number_of_zeros; i++) {
                prefix_y += "0";
            }
            y_hex = prefix_y + y_hex;
        }
        return "04" + x_hex.toUpperCase() + y_hex.toUpperCase();
    }

    private G1 compute_t_sigII(SharedPreferences sharedPreferences) {
        //tmp1 = s_v * g1
        G1 g1 = new G1();
        g1.setStr(BN256_g1_hex, 16);
        Fr s_v = new Fr(sharedPreferences.getString(Constants.SystemParameters.S_V, "00").substring(2, 66), 16);
        G1 tmp1 = new G1();
        com.herumi.mcl.Mcl.mul(tmp1, g1, s_v);
        //tmp2 = s_eII * sigma_roof_eII
        Fr s_eII = new Fr(sharedPreferences.getString(Constants.SystemParameters.S_E_II, "00"), 16);
        G1 sigma_roof_eII = new G1();
        sigma_roof_eII.setStr(databaseCurvePointToMcl(Constants.SystemParameters.SIGMA_ROOF_E_II, sharedPreferences), 16);
        G1 tmp2 = new G1();
        com.herumi.mcl.Mcl.mul(tmp2, sigma_roof_eII, s_eII);
        //tmp3 = neg (e)
        Fr neg_e = new Fr(sharedPreferences.getString(Constants.SystemParameters.E, "00"), 16);
        com.herumi.mcl.Mcl.neg(neg_e, neg_e);
        //tmp4 = neg_e * sigma_plane_eII
        G1 tmp4 = new G1();
        G1 sigma_plane_eII = new G1();
        sigma_plane_eII.setStr(databaseCurvePointToMcl(Constants.SystemParameters.SIGMA_PLANE_E_II, sharedPreferences), 16);
        com.herumi.mcl.Mcl.mul(tmp4, sigma_plane_eII, neg_e);
        //tmp5 = tmp1 + tmp2
        G1 tmp5 = new G1();
        com.herumi.mcl.Mcl.add(tmp5, tmp1, tmp2);
        //t_sigII = tmp5 + tmp4
        G1 t_sigII = new G1();
        com.herumi.mcl.Mcl.add(t_sigII, tmp5, tmp4);
        return t_sigII;
    }

    private G1 compute_t_sigI(SharedPreferences sharedPreferences) {
        //tmp1 = s_v * g1
        G1 g1 = new G1();
        g1.setStr(BN256_g1_hex, 16);
        Fr s_v = new Fr(sharedPreferences.getString(Constants.SystemParameters.S_V, "00").substring(2, 66), 16);
        G1 tmp1 = new G1();
        com.herumi.mcl.Mcl.mul(tmp1, g1, s_v);
        //tmp2 = s_eI * sigma_roof_eI
        G1 tmp2 = new G1();
        Fr s_eI = new Fr(sharedPreferences.getString(Constants.SystemParameters.S_E_I, "00"), 16);
        G1 sigma_roof_eI = new G1();
        sigma_roof_eI.setStr(databaseCurvePointToMcl(Constants.SystemParameters.SIGMA_ROOF_E_I, sharedPreferences), 16);
        com.herumi.mcl.Mcl.mul(tmp2, sigma_roof_eI, s_eI);
        //tmp3 = neg (e)
        Fr neg_e = new Fr(sharedPreferences.getString(Constants.SystemParameters.E, "00"), 16);
        com.herumi.mcl.Mcl.neg(neg_e, neg_e);
        //tmp4 = neg_e * sigma_plane_eI
        G1 tmp4 = new G1();
        G1 sigma_plane_eI = new G1();
        sigma_plane_eI.setStr(databaseCurvePointToMcl(Constants.SystemParameters.SIGMA_PLANE_E_I, sharedPreferences), 16);
        com.herumi.mcl.Mcl.mul(tmp4, sigma_plane_eI, neg_e);
        //tmp5 = tmp1 + tmp2
        G1 tmp5 = new G1();
        com.herumi.mcl.Mcl.add(tmp5, tmp1, tmp2);
        //t_sigI = tmp5 + tmp4
        G1 t_sigI = new G1();
        com.herumi.mcl.Mcl.add(t_sigI, tmp5, tmp4);
        return t_sigI;
    }


    private G1 compute_t_sig(SharedPreferences sharedPreferences) {
        //tmp1 = s_i * g1
        Fr s_i = new Fr(sharedPreferences.getString(Constants.SystemParameters.S_I, "00").substring(2, 66), 16);
        G1 g1 = new G1();
        g1.setStr(BN256_g1_hex, 16);
        G1 tmp1 = new G1();
        com.herumi.mcl.Mcl.mul(tmp1, g1, s_i);
        //tmp2 = s_eI * h_1
        G1 tmp2 = new G1();
        G1 h_1 = new G1();
        h_1.setStr(databaseCurvePointToMcl(Constants.SystemParameters.H_1, sharedPreferences), 16);
        Fr s_eI = new Fr(sharedPreferences.getString(Constants.SystemParameters.S_E_I, "00"), 16);
        com.herumi.mcl.Mcl.mul(tmp2, h_1, s_eI);
        //tmp3 = s_eII * h_2
        G1 h_2 = new G1();
        h_2.setStr(databaseCurvePointToMcl(Constants.SystemParameters.H_2, sharedPreferences), 16);
        Fr s_eII = new Fr(sharedPreferences.getString(Constants.SystemParameters.S_E_II, "00"), 16);
        G1 tmp3 = new G1();
        com.herumi.mcl.Mcl.mul(tmp3, h_2, s_eII);
        //tmp4 = tmp1 + tmp2
        G1 tmp4 = new G1();
        com.herumi.mcl.Mcl.add(tmp4, tmp1, tmp2);
        //t_sig = tmp4 + tmp3
        G1 t_sig = new G1();
        com.herumi.mcl.Mcl.add(t_sig, tmp4, tmp3);
        return t_sig;

    }

    private G1 compute_t_revoke(SharedPreferences sharedPreferences) {
        //tmp1  = SHA1(epoch)
        //Conversion of Epoch into Fr using SHA-1
        String epoch_hash = new BigInteger(SHA1_PADDING + SHA1(sharedPreferences.getString(Constants.SystemParameters.EPOCH, "00")), 16).mod(BN256_q).toString(16);
        Fr tmp1 = new Fr(epoch_hash, 16);
        //tmp2 = neg (tmp1)
        Fr tmp2 = new Fr();
        com.herumi.mcl.Mcl.neg(tmp2, tmp1);
        //tmp3 = tmp2 * C
        G1 tmp3 = new G1();
        G1 C = new G1();
        C.setStr(databaseCurvePointToMcl(Constants.SystemParameters.C, sharedPreferences), 16);
        com.herumi.mcl.Mcl.mul(tmp3, C, tmp2);
        //tmp4 = g1 + tmp3
        G1 tmp4 = new G1();
        G1 g1 = new G1();
        g1.setStr(BN256_g1_hex, 16);
        com.herumi.mcl.Mcl.add(tmp4, g1, tmp3);
        //tmp5 = neg (e)
        Fr tmp5 = new Fr();
        Fr e = new Fr(sharedPreferences.getString(Constants.SystemParameters.E, "00"), 16);
        com.herumi.mcl.Mcl.neg(tmp5, e);
        //tmp6 = tmp5 * tmp4
        G1 tmp6 = new G1();
        com.herumi.mcl.Mcl.mul(tmp6, tmp4, tmp5);
        //tmp7 = s_m_r * C
        Fr s_m_r = new Fr(sharedPreferences.getString(Constants.SystemParameters.S_M_R, "00"), 16);
        G1 tmp7 = new G1();
        com.herumi.mcl.Mcl.mul(tmp7, C, s_m_r);
        //tmp8 = s_i * C
        G1 tmp8 = new G1();
        Fr s_i = new Fr(sharedPreferences.getString(Constants.SystemParameters.S_I, "00").substring(2, 66), 16);
        com.herumi.mcl.Mcl.mul(tmp8, C, s_i);
        //tmp9 = tmp6 + tmp7
        G1 tmp9 = new G1();
        com.herumi.mcl.Mcl.add(tmp9, tmp6, tmp7);
        //t_revoke = tmp9 + tmp8
        G1 t_revoke = new G1();
        com.herumi.mcl.Mcl.add(t_revoke, tmp9, tmp8);
        return t_revoke;
    }

    private G1 compute_t_verify(SharedPreferences sharedPreferences) {
        //Initialization of values
        //Neg (e)
        Fr neg_e = new Fr(sharedPreferences.getString(Constants.SystemParameters.E, "00"), 16);
        com.herumi.mcl.Mcl.neg(neg_e, neg_e);
        //x_0
        Fr x_0 = new Fr(sharedPreferences.getString(Constants.SystemParameters.X_0, "00"), 16);
        //tmp2 = neg_e * x_0
        Fr tmp2 = new Fr();
        com.herumi.mcl.Mcl.mul(tmp2, neg_e, x_0);
        //tmp3 = tmp2 * sigma_roof
        G1 sigma_roof = new G1();
        sigma_roof.setStr(databaseCurvePointToMcl(Constants.SystemParameters.SIGMA_ROOF, sharedPreferences), 16);
        G1 tmp3 = new G1();
        com.herumi.mcl.Mcl.mul(tmp3, sigma_roof, tmp2);
        //tmp4 = s_v * g1
        G1 g1 = new G1();
        g1.setStr(BN256_g1_hex, 16);
        Fr s_v = new Fr(sharedPreferences.getString(Constants.SystemParameters.S_V, "00").substring(2, 66), 16);
        G1 tmp4 = new G1();
        com.herumi.mcl.Mcl.mul(tmp4, g1, s_v);
        //x_r
        Fr x_r = new Fr(sharedPreferences.getString(Constants.SystemParameters.X_R, "00"), 16);
        //tmp5 = x_r * s_m_r
        Fr s_m_r = new Fr(sharedPreferences.getString(Constants.SystemParameters.S_M_R, "00"), 16);
        Fr tmp5 = new Fr();
        com.herumi.mcl.Mcl.mul(tmp5, x_r, s_m_r);
        //tmp6 = tmp5 * sigma_roof
        G1 tmp6 = new G1();
        com.herumi.mcl.Mcl.mul(tmp6, sigma_roof, tmp5);
        //tmp7 = tmp3 + tmp4
        G1 tmp7 = new G1();
        com.herumi.mcl.Mcl.add(tmp7, tmp3, tmp4);
        //tmp8 = tmp7 + tmp6 (First part done)
        G1 tmp8 = new G1();
        com.herumi.mcl.Mcl.add(tmp8, tmp7, tmp6);
        //tmp9 = ∏ z ∉ D (s_m_z * x_z * sigma_roof). Second Part
        G1 tmp9 = new G1();
        tmp9.setStr("0");
        int total_number_of_attributes = Integer.parseInt(Objects.requireNonNull(sharedPreferences.getString(Constants.SystemParameters.NUMBER_OF_ATTRIBUTES, "00")), 16);
        for (int i = 1; i <= total_number_of_attributes; i++) {
            if (sharedPreferences.getString(Constants.SystemParameters.S_M + i, "").equals("")) {
                continue;
            } else {
                Fr multiplier = new Fr();
                Fr s_m_z = new Fr(sharedPreferences.getString(Constants.SystemParameters.S_M + i, ""), 16);
                Fr X_z = new Fr(sharedPreferences.getString(Constants.SystemParameters.X_ + i, ""), 16);
                com.herumi.mcl.Mcl.mul(multiplier, X_z, s_m_z);
                G1 tmp = new G1();
                com.herumi.mcl.Mcl.mul(tmp, sigma_roof, multiplier);
                com.herumi.mcl.Mcl.add(tmp9, tmp9, tmp);
            }
        }
        //tmp10 = ∏ z ∈ D (-e * x_z * m_z). Third Part
        G1 tmp10 = new G1();
        tmp10.setStr("0");
        for (int i = 1; i <= total_number_of_attributes; i++) {
            if (sharedPreferences.getString(Constants.SystemParameters.M_ + i, "").equals("")) {
                continue;
            } else {
                Fr multiplier = new Fr();
                Fr m = new Fr(sharedPreferences.getString(Constants.SystemParameters.M_ + i, ""), 16);
                Fr X = new Fr(sharedPreferences.getString(Constants.SystemParameters.X_ + i, ""), 16);
                com.herumi.mcl.Mcl.mul(multiplier, neg_e, X);
                com.herumi.mcl.Mcl.mul(multiplier, multiplier, m);
                G1 tmp = new G1();
                com.herumi.mcl.Mcl.mul(tmp, sigma_roof, multiplier);
                com.herumi.mcl.Mcl.add(tmp10, tmp10, tmp);
            }
        }
        //tmp11 = tmp8 + tmp9
        G1 tmp11 = new G1();
        com.herumi.mcl.Mcl.add(tmp11, tmp8, tmp9);
        //t_verify = tmp11 + tmp10
        G1 t_verify = new G1();
        com.herumi.mcl.Mcl.add(t_verify, tmp11, tmp10);
        return t_verify;
    }

    public String generateNonce() {
        char[] charArr = new char[] {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};//hex digits array
        Random rand = new Random();
        String result = "";
        for(int x = 0; x < 64; x++) {
            int resInt = rand.nextInt(charArr.length);//random array element
            result += charArr[resInt];
        }
        return result;
    }

    private String databaseCurvePointToMcl(String parameter_key, SharedPreferences sharedPreferences) {
        //Initialization of Shared Preferences
        String param = sharedPreferences.getString(parameter_key, "");
        if (param.length() != 130) {
            throw new NumberFormatException("Parameter is not set!");
        }
        //"04" is redundant
        return "1 " + param.substring(2, 66) + " " + param.substring(66, 130);
    }

    //This method returns output of SHA-1 hash function
    private String SHA1(String input) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(Utils.hexStringToByteArray(input));
        byte[] digest = md.digest();
        return Utils.byteArrayToHexString(digest).toUpperCase();
    }

}