package model;

import java.sql.Date;
import java.util.List;

public class Catalog {

    String uuid;
    String date;
    String company;
    private List<Plant> plant;


    public List<Plant> getPlant() {
        return plant;
    }

    public String getUuid() {
        return uuid;
    }

    public String getDate() {
        return date;
    }

    public String getCompany() {
        return company;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "uuid='" + uuid + '\'' +
                ", date=" + date +
                ", company='" + company + '\'' +
                ", plant=" + plant +
                '}';
    }

    public void setPlant(List<Plant> plant) {
        this.plant = plant;
    }
}
