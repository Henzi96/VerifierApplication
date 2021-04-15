package com.example.nfcreader;

public class Constants {

    public class SystemParameters {
        public static final String VERIFIER_DATA = "VerifierData";
        public static final String EPOCH = "epoch";
        public static final String E = "e";
        public static final String S_V = "s_v";
        public static final String S_I = "s_i";
        public static final String S_E_I = "s_eI";
        public static final String S_E_II = "s_eII";
        public static final String S_M_R = "s_m_r";
        public static final String S_M = "s_m";
        public static final String BYTES_250 = "FA";
        public static final String BYTES_140 = "8C";
        public static final String C = "c";
        public static final String SIGMA_ROOF = "sigma_roof";
        public static final String SIGMA_ROOF_E_I = "sigma_roof_eI";
        public static final String SIGMA_ROOF_E_II = "sigma_roof_eII";
        public static final String SIGMA_PLANE_E_I = "sigma_plane_eI";
        public static final String SIGMA_PLANE_E_II = "sigma_plane_eII";
        public static final String X_0 = "x_0";
        public static final String X_ = "x_";
        public static final String X_R = "x_r";
        public static final String NUMBER_OF_ATTRIBUTES = "number_of_attributes";
        public static final String PK_RA = "pk_ra";
        public static final String H_1 = "h_1";
        public static final String H_2 = "h_2";
        public static final String NONCE = "nonce";
        public static final String ATTRIBUTE = "attribute";
        public static final String EMPTY = "";
        public static final String M_ = "m_" ;
        public static final String NUMBER_OF_LOGS = "number_of_logs";
        public static final String LOG_STATE = "log_state_";
        public static final String LOG_DATE = "log_date_";
        public static final String INS_CMD_BIT_CHECKER_SIGN = "INS_CMD_BIT_CHECKER";
        public static final String INS_COMPUTE_PROOF_OF_KNOWLEDGE_SEQ_DISCLOSED_SIGN = "INS_COMPUTE_PROOF_OF_KNOWLEDGE_SEQ_DISCLOSED";
        public static final String INS_GET_PROOF_OF_KNOWLEDGE_SIGN = "INS_GET_PROOF_OF_KNOWLEDGE";
        public static final String SELECT_AID_SIGN = "SELECT_AID" ;
    }

    public class Eid {
        public final static String EID_NUMBER_OF_HIDDEN_ATTRIBUTES = "eid_num_of_hidden_attributes";
        public final static String EID_CRYPTO_CREDENCIAL = "eid_crypto_credencial";
        public final static String DISCLOSURE_IS_NOT_REQUIRED = "Disclosure is not required";
        public final static String NAME_SURNAME_EID_STATE = "name_surname_eid_state";
        public final static String NAME_SURNAME_EID_SWITCH = "name_surname_eid_switch";
        public final static String BIRTHDATE_EID_STATE = "birthdate_eid_state";
        public final static String BIRTHDATE_EID_SWITCH = "birthdate_eid_switch";
        public final static String NATIONALITY_EID_STATE = "nationality_eid_state";
        public final static String NATIONALITY_EID_SWITCH = "nationality_eid_switch";
        public final static String PERMANENT_RESIDENCE_EID_STATE = "permanent_residence_eid_state";
        public final static String PERMANENT_RESIDENCE_EID_SWITCH = "permanent_residence_eid_switch";
        public static final String SEX_EID_STATE = "sex_eid_state";
        public static final String SEX_EID_SWITCH = "sex_eid_switch";
        public static final String EID_NUMBER_OF_ATTRIBUTES = "05";
        public static final String NUMBER_OF_ATTRIBUTES_KEY = "number_of_attributes";
        public static final String QR_EID_NAME_SURNAME_BUTTON = "eid_name_surname_qr_scan";
        public static final String QR_EID_BIRTHDATE_BUTTON = "eid_birthdate_qr_scan";
        public static final String QR_EID_NATIONALITY_BUTTON = "eid_nationality_qr_scan";
        public static final String QR_EID_PERMANENT_RESIDENCE_BUTTON = "eid_permanent_residence_qr_scan";
        public static final String QR_EID_SEX_BUTTON = "eid_sex_qr_scan";
        public static final String M_1 = "m_1";
        public static final String M_2 = "m_2";
        public static final String M_3 = "m_3";
        public static final String M_4 = "m_4";
        public static final String M_5 = "m_5";
    }

    public class Ticket {
        public final static String TICKET_NUMBER_OF_HIDDEN_ATTRIBUTES = "ticket_num_of_hidden_attributes";
        public final static String TICKET_CRYPTO_CREDENCIAL = "ticket_crypto_credencial";
        public final static String DISCLOSURE_IS_NOT_REQUIRED = "Disclosure is not required";
        public final static String NAME_SURNAME_TICKET_STATE = "name_surname_ticket_state";
        public final static String NAME_SURNAME_TICKET_SWITCH = "name_surname_ticket_switch";
        public final static String CARD_NUMBER_TICKET_STATE = "card_number_ticket_state";
        public final static String CARD_NUMBER_TICKET_SWITCH = "card_number_ticket_switch";
        public final static String TYPE_OF_TICKET_TICKET_STATE = "type_of_ticket_ticket_state";
        public final static String TYPE_OF_TICKET_TICKET_SWITCH = "type_of_ticket_ticket_switch";
        public static final String TICKET_NUMBER_OF_ATTRIBUTES = "03";
        public static final String NUMBER_OF_ATTRIBUTES_KEY = "number_of_attributes";
        public static final String QR_TICKET_NAME_SURNAME_BUTTON = "ticket_name_surname_qr_scan";
        public static final String QR_TICKET_CARD_NUMBER_BUTTON = "ticket_card_number_qr_scan";
        public static final String QR_TICKET_TYPE_OF_TICKET_BUTTON = "ticket_type_of_ticket_qr_scan";
        public static final String M_1 = "m_1";
        public static final String M_2 = "m_2";
        public static final String M_3 = "m_3";
    }

    public class EmployeeCard {
        public final static String EMPLOYEE_CARD_NUMBER_OF_HIDDEN_ATTRIBUTES = "employee_card_num_of_hidden_attributes";
        public final static String EMPLOYEE_CARD_CRYPTO_CREDENCIAL = "employee_card_crypto_credencial";
        public final static String DISCLOSURE_IS_NOT_REQUIRED = "Disclosure is not required";
        public final static String NAME_SURNAME_EMPLOYEE_CARD_STATE = "name_surname_employee_card_state";
        public final static String NAME_SURNAME_EMPLOYEE_CARD_SWITCH = "name_surname_employee_card_switch";
        public final static String EMPLOYEE_ID_EMPLOYEE_CARD_STATE = "employee_id_employee_card_state";
        public final static String EMPLOYEE_ID_EMPLOYEE_CARD_SWITCH = "employee_id_employee_card_switch";
        public final static String EMPLOYER_EMPLOYEE_CARD_STATE = "employer_employee_card_state";
        public final static String EMPLOYER_EMPLOYEE_CARD_SWITCH = "employer_employee_card_switch";
        public final static String EMPLOYEE_POSITION_EMPLOYEE_CARD_STATE = "employee_position_employee_card_state";
        public final static String EMPLOYEE_POSITION_EMPLOYEE_CARD_SWITCH = "employee_position_employee_card_switch";
        public static final String EMPLOYEE_CARD_NUMBER_OF_ATTRIBUTES = "04";
        public static final String NUMBER_OF_ATTRIBUTES_KEY = "number_of_attributes";
        public static final String QR_EMPLOYEE_CARD_EMPLOYEE_ID_BUTTON = "employee_card_employee_id_qr_scan";
        public static final String QR_EMPLOYEE_CARD_EMPLOYER_BUTTON = "employee_card_employer_qr_scan";
        public static final String QR_EMPLOYEE_CARD_EMPLOYEE_POSITION_BUTTON = "employee_card_employee_position_qr_scan";
        public static final String QR_EMPLOYEE_CARD_NAME_SURNAME_BUTTON = "employee_card_name_surname_qr_scan";
        public static final String M_1 = "m_1";
        public static final String M_2 = "m_2";
        public static final String M_3 = "m_3";
        public static final String M_4 = "m_4";
    }

    public class UserDefined {
        public final static String USER_DEFINED_NUMBER_OF_HIDDEN_ATTRIBUTES = "user_defined_num_of_hidden_attributes";
        public final static String USER_DEFINED_CRYPTO_CREDENCIAL = "user_defined_crypto_credencial";
        public final static String DISCLOSURE_IS_NOT_REQUIRED = "Disclosure is not required";
        public final static String ATTRIBUTE_1_STATE = "attribute_1_state";
        public final static String ATTRIBUTE_1_SWITCH = "attribute_1_switch";
        public final static String ATTRIBUTE_2_STATE = "attribute_2_state";
        public final static String ATTRIBUTE_2_SWITCH = "attribute_2_switch";
        public final static String ATTRIBUTE_3_STATE = "attribute_3_state";
        public final static String ATTRIBUTE_3_SWITCH = "attribute_3_switch";
        public final static String ATTRIBUTE_4_STATE = "attribute_4_state";
        public final static String ATTRIBUTE_4_SWITCH = "attribute_4_switch";
        public final static String ATTRIBUTE_5_STATE = "attribute_5_state";
        public final static String ATTRIBUTE_5_SWITCH = "attribute_5_switch";
        public final static String ATTRIBUTE_6_STATE = "attribute_6_state";
        public final static String ATTRIBUTE_6_SWITCH = "attribute_6_switch";
        public final static String ATTRIBUTE_7_STATE = "attribute_7_state";
        public final static String ATTRIBUTE_7_SWITCH = "attribute_7_switch";
        public final static String ATTRIBUTE_8_STATE = "attribute_8_state";
        public final static String ATTRIBUTE_8_SWITCH = "attribute_8_switch";
        public final static String ATTRIBUTE_9_STATE = "attribute_9_state";
        public final static String ATTRIBUTE_9_SWITCH = "attribute_9_switch";
        public static final String NUMBER_OF_ATTRIBUTES_KEY = "number_of_attributes";
        public static final String NUMBER_OF_ATTRIBUTES_EDIT_TEXT_KEY = "number_of_attributes_user_defined_key";
        public static final String NUMBER_OF_ATTRIBUTES_EDIT_TEXT_DEFAULT_VALUE = "0";
        public static final String QR_ATTRIBUTE_1_BUTTON = "user_defined_attribute1_qr_scan";
        public static final String QR_ATTRIBUTE_2_BUTTON = "user_defined_attribute2_qr_scan";
        public static final String QR_ATTRIBUTE_3_BUTTON = "user_defined_attribute3_qr_scan";
        public static final String QR_ATTRIBUTE_4_BUTTON = "user_defined_attribute4_qr_scan";
        public static final String QR_ATTRIBUTE_5_BUTTON = "user_defined_attribute5_qr_scan";
        public static final String QR_ATTRIBUTE_6_BUTTON = "user_defined_attribute6_qr_scan";
        public static final String QR_ATTRIBUTE_7_BUTTON = "user_defined_attribute7_qr_scan";
        public static final String QR_ATTRIBUTE_8_BUTTON = "user_defined_attribute8_qr_scan";
        public static final String QR_ATTRIBUTE_9_BUTTON = "user_defined_attribute9_qr_scan";
        public static final String M_1 = "m_1";
        public static final String M_2 = "m_2";
        public static final String M_3 = "m_3";
        public static final String M_4 = "m_4";
        public static final String M_5 = "m_5";
        public static final String M_6 = "m_6";
        public static final String M_7 = "m_7";
        public static final String M_8 = "m_8";
        public static final String M_9 = "m_9";
    }

    public class MainMenu {
        public final static String VERIFICATION_STATE = "verification_button_state";
    }
}