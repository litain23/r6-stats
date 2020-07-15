package me.r6_search.r6api;

import java.util.HashMap;
import java.util.Map;

public class Platform {
    public static final Map<String, String> platformTable = Map.of(
        "uplay", "OSBOR_PC_LNCH_A",
        "psn", "OSBOR_PS4_LNCH_A" ,
        "xbl", "OSBOR_XBOXONE_LNCH_A"
    );

    public static final Map<String, String> spaceTable = Map.of(
        "uplay", "5172a557-50b5-4665-b7db-e3f2e8c5041d",
        "psn", "05bfb3f7-6c21-4c42-be1f-97a33fb5cf66",
        "xbl", "98a601e5-ca91-4440-b1c5-753f601a2c90"
    );

    public static String platformToSpaceId(String platformName) {
        return spaceTable.get(platformName);
    }

    public static String platformToPlatformId(String platformName) {
        return platformTable.get(platformName);
    }
}
