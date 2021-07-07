package model;

public class Plant {
    private String common;
    private String botanical;
    private Integer zone;
    private String light;
    private float price;
    private String availability;

    public Plant(String common, String botanical, Integer zone, String light, float price, String availability) {
        this.common = common;
        this.botanical = botanical;
        this.zone = zone;
        this.light = light;
        this.price = price;
        this.availability = availability;
    }

    public String getCommon() {
        return common;
    }

    public String getBotanical() {
        return botanical;
    }

    public Integer getZone() {
        return zone;
    }

    public String getLight() {
        return light;
    }

    public float getPrice() {
        return price;
    }

    public String getAvailability() {
        return availability;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "common='" + common + '\'' +
                ", botanical='" + botanical + '\'' +
                ", zone=" + zone +
                ", light='" + light + '\'' +
                ", price=" + price +
                ", availability='" + availability + '\'' +
                '}';
    }




    public void setCommon(String common) {
        this.common = common;
    }

    public void setBotanical(String botanical) {
        this.botanical = botanical;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
