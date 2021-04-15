package com.example.nfcreader;

public class ApduCommandValues {

    public class CLA {
        public static final String PEAS_CLA = "07";
        ;
    }

    public class INS {
        public static final String SELECT = "A4";
        public static final String CMD_TEST_BIT_CHECKER = "09";
        public static final String INS_COMPUTE_PROOF_OF_KNOWLEDGE_SEQ_DISCLOSED = "0A";
        public static final String INS_GET_PROOF_OF_KNOWLEDGE = "07";
    }

    public class COMMANDS {
        public static final String SELECT_AID = "00A40400077675743231303100";
    }

    public class SW1_SW2 {
        public static final String SUCCESS = "9000";
        public static final String MORE_DATA = "91AF";
    }

    public class P1 {
        public static final String GET_PROOF_OF_KNOWLEDGE_P1_PI = "01";
        public static final String GET_PROOF_OF_KNOWLEDGE_P1_CRED = "02";
    }

    public class P2 {
        public static final String GET_PROOF_OF_KNOWLEDGE_P2_ONE_MESSAGE = "01";
        public static final String GET_PROOF_OF_KNOWLEDGE_P2_TWO_MESSAGES = "02";
    }
}

