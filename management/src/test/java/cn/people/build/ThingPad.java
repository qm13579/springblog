package cn.people.build;

/**
 * @author : FENGZHI
 * create at:  2020/3/12  下午11:38
 * @description: product
 */
public class ThingPad {
    private String board;
    private String display;
    private String os;

    private ThingPad(Builder builder) {
        this.board = builder.board;
        this.display = builder.display;
        this.os = builder.os;
    }

    public String getBoard() {
        return board;
    }

    public String getDisplay() {
        return display;
    }

    public String getOs() {
        return os;
    }

    public static class Builder{
        private String board;
        private String display;
        private String os;

        public Builder setBoard(String board) {
            this.board = board;
            return this;
        }

        public Builder setDisplay(String display) {
            this.display = display;
            return this;
        }

        public Builder setOs(String os) {
            this.os = os;
            return this;
        }
        public ThingPad build(){
            return new ThingPad(this);
        }
    }

    @Override
    public String toString() {
        return "ThingPad{" +
                "board='" + board + '\'' +
                ", display='" + display + '\'' +
                ", os='" + os + '\'' +
                '}';
    }
}
