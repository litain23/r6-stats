package org.example.springboot.r6api;

import java.util.Map;

public class RequestParam {
    public static final String[] GENERAL_PVP_PARAM = {
            "generalpvp_timeplayed",
            "generalpvp_matchplayed",
            "generalpvp_killassists",
            "generalpvp_revive",
            "generalpvp_headshot",
            "generalpvp_penetrationkills",
            "generalpvp_meleekills",
            "generalpvp_matchwon",
            "generalpvp_matchlost",
            "generalpvp_kills",
            "generalpvp_death",
            "generalpvp_bullethit",
            "generalpvp_bulletfired",
    };

    public static final String[] CASUAL_PVP_PARAM = {
            "casualpvp_death:infinite",
            "casualpvp_kills:infinite",
            "casualpvp_matchlost:infinite",
            "casualpvp_matchplayed:infinite",
            "casualpvp_matchwon:infinite",
            "casualpvp_timeplayed:infinite"
    };

    public static final String[] RANK_PVP_PARAM = {
            "rankedpvp_death:infinite",
            "rankedpvp_kills:infinite",
            "rankedpvp_matchlost:infinite",
            "rankedpvp_matchplayed:infinite",
            "rankedpvp_matchwon:infinite",
            "rankedpvp_timeplayed:infinite"
    };

    public static final String[] OPERATORS_PARAM = {
            "operatorpvp_timeplayed",
            "operatorpvp_roundwon",
            "operatorpvp_roundlost",
            "operatorpvp_kills",
            "operatorpvp_death",
            "operatorpvp_headshot",
            "operatorpvp_meleekills",
            "operatorpvp_totalxp"
    };

    public enum RequestType {
        OPERATOR, RANK_PVP, CASUAL_PVP, GENERAL_PVP
    }

    public static Map<RequestType, String[]> map = Map.of(
            RequestType.OPERATOR, OPERATORS_PARAM,
            RequestType.CASUAL_PVP, CASUAL_PVP_PARAM,
            RequestType.RANK_PVP, RANK_PVP_PARAM,
            RequestType.GENERAL_PVP, GENERAL_PVP_PARAM
    );

}

