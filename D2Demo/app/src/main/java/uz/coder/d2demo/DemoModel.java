package uz.coder.d2demo;

public class DemoModel {
    private String img;
    private String txt;
    private String price;

    public DemoModel(String img, String txt, String price) {
        this.img = img;
        this.txt = txt;
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
