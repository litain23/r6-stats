package org.example.springboot.r6api;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperatorIndex {
    public static List<OperatorInfo> indexList = new ArrayList<>(Arrays.asList(
        new OperatorInfo("caveira", "3:8", "def"),
        new OperatorInfo("smoke", "2:1", "def"),
        new OperatorInfo("mute", "3:1", "def"),
        new OperatorInfo("sledge", "4:1", "atk"),
        new OperatorInfo("thatcher", "5:1", "atk"),
        new OperatorInfo("castle", "2:2", "def"),
        new OperatorInfo("ash", "3:2", "atk"),
        new OperatorInfo("pulse", "4:2", "def"),
        new OperatorInfo("thermite", "5:2", "atk"),
        new OperatorInfo("doc", "2:3", "def"),
        new OperatorInfo("rook", "3:3", "def"),
        new OperatorInfo("twitch", "4:3", "atk"),
        new OperatorInfo("montagne", "5:3", "atk"),
        new OperatorInfo("glaz", "2:4", "atk"),
        new OperatorInfo("fuze", "3:4", "atk"),
        new OperatorInfo("kapkan", "4:4", "def"),
        new OperatorInfo("tachanka", "5:4", "def"),
        new OperatorInfo("blitz", "2:5", "atk"),
        new OperatorInfo("iq", "3:5", "atk"),
        new OperatorInfo("jager", "4:5", "def"),
        new OperatorInfo("bandit", "5:5", "def"),
        new OperatorInfo("buck", "2:6", "atk"),
        new OperatorInfo("frost", "3:6", "def"),
        new OperatorInfo("blackbeard", "2:7", "atk"),
        new OperatorInfo("valkyrie", "3:7", "def"),
        new OperatorInfo("capitao", "2:8", "atk"),
        new OperatorInfo("hibana", "2:9", "atk"),
        new OperatorInfo("echo", "3:9", "def"),
        new OperatorInfo("jackal", "2:A", "atk"),
        new OperatorInfo("mira", "3:A", "def"),
        new OperatorInfo("ying", "2:B", "atk"),
        new OperatorInfo("lesion", "3:B", "def"),
        new OperatorInfo("ela", "2:C", "def"),
        new OperatorInfo("zofia", "3:C", "atk"),
        new OperatorInfo("dokkaebi", "2:D", "atk"),
        new OperatorInfo("vigil", "3:D", "def"),
        new OperatorInfo("lion", "3:E", "atk"),
        new OperatorInfo("finka", "4:E", "atk"),
        new OperatorInfo("maestro", "2:F", "def"),
        new OperatorInfo("alibi", "3:F", "def"),
        new OperatorInfo("maverick", "2:10", "atk"),
        new OperatorInfo("clash", "3:10", "def"),
        new OperatorInfo("nomad", "2:11", "atk"),
        new OperatorInfo("kaid", "3:11", "def"),
        new OperatorInfo("mozzie", "2:12", "def"),
        new OperatorInfo("gridlock", "3:12", "atk"),
        new OperatorInfo("nakk", "2:13", "atk"),
        new OperatorInfo("warden", "2:14", "def"),
        new OperatorInfo("goyo", "2:15", "def"),
        new OperatorInfo("amaru", "2:16", "atk"),
        new OperatorInfo("kali", "2:17", "atk"),
        new OperatorInfo("wamai", "3:17", "def"),
        new OperatorInfo("oryx", "2:18", "def"),
        new OperatorInfo("iana", "2:19", "atk")
    ));

    @Getter
    static class OperatorInfo {
        String name;
        String index;
        String category;

        public OperatorInfo(String name, String index, String category) {
            this.name = name;
            this.index = index;
            this.category = category;
        }
    }
}

