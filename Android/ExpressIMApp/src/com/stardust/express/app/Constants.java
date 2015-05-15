package com.stardust.express.app;

/**
 * Created by Sylar on 15/5/13.
 */
public class Constants {

    public static String MAIN_URL = "/ExpressIM/web-interface/";

    public static String OPERATOR_LOGON_URL = MAIN_URL + "operatorlogon";

    public static String LEADER_LOGON_URL = MAIN_URL + "leaderlogon";

    public static String ARCHIVE_URL = MAIN_URL + "archive";

    public static interface SHARED_KEY {
        String uid = "uid";
        String name = "name";
        String account = "account";
    }
}
