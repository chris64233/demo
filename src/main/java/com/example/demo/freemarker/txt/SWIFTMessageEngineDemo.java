package com.example.demo.freemarker.txt;

public class SWIFTMessageEngineDemo {
    public static void main(String[] args) {
        // 包含 :22A:NEWT
        MT300Message mt300MessageWithNewt = new MT300Message(
                "REF123456", "EUR/USD", 1000.0, "BANKBEBBAXXX", "BANKUS33XXX", 1.2000, true
        );
        System.out.println(mt300MessageWithNewt.generateMessage());

        // 不包含 :22A:NEWT
        MT300Message mt300MessageWithoutNewt = new MT300Message(
                "REF654321", "USD/JPY", 5000.0, "BANKJPJPXXX", "BANKUS33XXX", 110.50, false
        );
        System.out.println(mt300MessageWithoutNewt.generateMessage());
    }
}
