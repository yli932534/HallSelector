package com.app.hallselector.model;


import java.io.Serializable;
import java.util.Objects;
import java.util.Objects;

public class Building implements Serializable {


    private String name;
    private String area;
    private String phone_number;
    private String address;
    private String hall_director;
    private String hall_description;

    private String academic_year_housing;
    private String air_conditioning;
    private String corridor_bath;
    private String game_room;
    private String handicap_access;
    private String kitchen_facilities;
    private String laundry_facilities;
    private String lounge_space;
    private String microwave_refrigerator;
    private String on_site_parking;
    private String own_trash_removal;
    private String ResNet;
    private String study_areas;
    private String suite_room_bath;
    private String vending_machines;
    private String women_only;


    @Override
    public String toString() {
        return "Building{" +
                "name='" + name + '\'' +
                ", area='" + area + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", address='" + address + '\'' +
                ", hall_director='" + hall_director + '\'' +
                ", hall_description='" + hall_description + '\'' +
                ", academic_year_housing='" + academic_year_housing + '\'' +
                ", air_conditioning='" + air_conditioning + '\'' +
                ", corridor_bath='" + corridor_bath + '\'' +
                ", game_room='" + game_room + '\'' +
                ", handicap_access='" + handicap_access + '\'' +
                ", kitchen_facilities='" + kitchen_facilities + '\'' +
                ", laundry_facilities='" + laundry_facilities + '\'' +
                ", lounge_space='" + lounge_space + '\'' +
                ", microwave_refrigerator='" + microwave_refrigerator + '\'' +
                ", on_site_parking='" + on_site_parking + '\'' +
                ", own_trash_removal='" + own_trash_removal + '\'' +
                ", ResNet='" + ResNet + '\'' +
                ", study_areas='" + study_areas + '\'' +
                ", suite_room_bath='" + suite_room_bath + '\'' +
                ", vending_machines='" + vending_machines + '\'' +
                ", women_only='" + women_only + '\'' +
                '}';
    }

    //Constructor
    public Building(String name, String area, String phone_number, String address, String hall_director,
                    String hall_description, String academic_year_housing, String air_conditioning,
                    String corridor_bath, String game_room, String handicap_access, String kitchen_facilities,
                    String laundry_facilities, String lounge_space, String microwave_refrigerator,
                    String on_site_parking, String own_trash_removal, String resNet, String study_areas,
                    String suite_room_bath, String vending_machines, String women_only) {
        this.name = name;
        this.area = area;
        this.phone_number = phone_number;
        this.address = address;
        this.hall_director = hall_director;
        this.hall_description = hall_description;

        this.academic_year_housing = academic_year_housing;
        this.air_conditioning = air_conditioning;
        this.corridor_bath = corridor_bath;
        this.game_room = game_room;
        this.handicap_access = handicap_access;
        this.kitchen_facilities = kitchen_facilities;
        this.laundry_facilities = laundry_facilities;
        this.lounge_space = lounge_space;
        this.microwave_refrigerator = microwave_refrigerator;
        this.on_site_parking = on_site_parking;
        this.own_trash_removal = own_trash_removal;
        this.ResNet = resNet;
        this.study_areas = study_areas;
        this.suite_room_bath = suite_room_bath;
        this.vending_machines = vending_machines;
        this.women_only = women_only;
    }

    public Building(){

    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getArea(), getPhone_number(), getAddress(), getHall_director(), getHall_description(), getAcademic_year_housing(), getAir_conditioning(), getCorridor_bath(), getGame_room(), getHandicap_access(), getKitchen_facilities(), getLaundry_facilities(), getLounge_space(), getMicrowave_refrigerator(), getOn_site_parking(), getOwn_trash_removal(), getResNet(), getStudy_areas(), getSuite_room_bath(), getVending_machines(), getWomen_only());
    }

    public boolean meetBoolConditions(Building building){
        if (this.academic_year_housing != null && !this.academic_year_housing.equals(building.getAcademic_year_housing())) {
            return false;
        }
        if (this.air_conditioning != null && !this.air_conditioning.equals(building.getAir_conditioning())) {
            return false;
        }
        if (this.game_room != null && !this.game_room.equals(building.getGame_room())) {
            return false;
        }
        if (this.handicap_access != null && !this.handicap_access.equals(building.getHandicap_access())){
            return false;
        }
        if (this.kitchen_facilities != null && !this.kitchen_facilities.equals(building.getKitchen_facilities())){
            return false;
        }
        if (this.laundry_facilities != null && !this.laundry_facilities.equals(building.getLaundry_facilities())){
            return false;
        }
        if (this.lounge_space != null && !this.lounge_space.equals(building.getLounge_space())){
            return false;
        }
        if (this.microwave_refrigerator != null && !this.microwave_refrigerator.equals(building.getMicrowave_refrigerator())){
            return false;
        }
        if (this.on_site_parking != null && !this.on_site_parking.equals(building.getOn_site_parking())){
            return false;
        }
        if (this.own_trash_removal != null && !this.own_trash_removal.equals(building.getOwn_trash_removal())){
            return false;
        }
        if (this.ResNet != null && !this.ResNet.equals(building.getResNet())){
            return false;
        }
        if (this.study_areas != null && !this.study_areas.equals(building.getStudy_areas())){
            return false;
        }
        if (this.suite_room_bath != null && !this.suite_room_bath.equals(building.getSuite_room_bath())){
            return false;
        }
        if (this.vending_machines != null && !this.vending_machines.equals(building.getVending_machines())){
            return false;
        }
        if (this.women_only != null && !this.women_only.equals(building.getWomen_only())){
            return false;
        }

        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHall_director() {
        return hall_director;
    }

    public void setHall_director(String hall_director) {
        this.hall_director = hall_director;
    }

    public String getHall_description() {
        return hall_description;
    }

    public void setHall_description(String hall_description) {
        this.hall_description = hall_description;
    }

    public String getAcademic_year_housing() {
        return academic_year_housing;
    }

    public void setAcademic_year_housing(String academic_year_housing) {
        this.academic_year_housing = academic_year_housing;
    }

    public String getAir_conditioning() {
        return air_conditioning;
    }

    public void setAir_conditioning(String air_conditioning) {
        this.air_conditioning = air_conditioning;
    }

    public String getCorridor_bath() {
        return corridor_bath;
    }

    public void setCorridor_bath(String corridor_bath) {
        this.corridor_bath = corridor_bath;
    }

    public String getGame_room() {
        return game_room;
    }

    public void setGame_room(String game_room) {
        this.game_room = game_room;
    }

    public String getHandicap_access() {
        return handicap_access;
    }

    public void setHandicap_access(String handicap_access) {
        this.handicap_access = handicap_access;
    }

    public String getKitchen_facilities() {
        return kitchen_facilities;
    }

    public void setKitchen_facilities(String kitchen_facilities) {
        this.kitchen_facilities = kitchen_facilities;
    }

    public String getLaundry_facilities() {
        return laundry_facilities;
    }

    public void setLaundry_facilities(String laundry_facilities) {
        this.laundry_facilities = laundry_facilities;
    }

    public String getLounge_space() {
        return lounge_space;
    }

    public void setLounge_space(String lounge_space) {
        this.lounge_space = lounge_space;
    }

    public String getMicrowave_refrigerator() {
        return microwave_refrigerator;
    }

    public void setMicrowave_refrigerator(String microwave_refrigerator) {
        this.microwave_refrigerator = microwave_refrigerator;
    }

    public String getOn_site_parking() {
        return on_site_parking;
    }

    public void setOn_site_parking(String on_site_parking) {
        this.on_site_parking = on_site_parking;
    }

    public String getOwn_trash_removal() {
        return own_trash_removal;
    }

    public void setOwn_trash_removal(String own_trash_removal) {
        this.own_trash_removal = own_trash_removal;
    }

    public String getResNet() {
        return ResNet;
    }

    public void setResNet(String resNet) {
        ResNet = resNet;
    }

    public String getStudy_areas() {
        return study_areas;
    }

    public void setStudy_areas(String study_areas) {
        this.study_areas = study_areas;
    }

    public String getSuite_room_bath() {
        return suite_room_bath;
    }

    public void setSuite_room_bath(String suite_room_bath) {
        this.suite_room_bath = suite_room_bath;
    }

    public String getVending_machines() {
        return vending_machines;
    }

    public void setVending_machines(String vending_machines) {
        this.vending_machines = vending_machines;
    }

    public String getWomen_only() {
        return women_only;
    }

    public void setWomen_only(String women_only) {
        this.women_only = women_only;
    }
}
