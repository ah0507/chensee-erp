package net.chensee.platform.erp.enums;

public enum YesOrNoEnum {

    YES("是"),  // 0
    NO("否");   // 1

    private String text;

    YesOrNoEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void ttt() {
        String name = YesOrNoEnum.YES.name();
        int ordinal = YesOrNoEnum.YES.ordinal();
    }

}
