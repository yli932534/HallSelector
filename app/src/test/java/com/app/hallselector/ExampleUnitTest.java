package com.app.hallselector;

import org.junit.Test;

import static org.junit.Assert.*;

import com.app.hallselector.model.Building;
import com.app.hallselector.model.Search_Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void building_meetBoolConditions_isCorrect_true() {

        Building building1 = new Building();
        building1.setArea("West");

        Building building2 = new Building();
        building2.setArea("West");
        building2.setAir_conditioning("True");
        assertTrue(building1.meetBoolConditions(building2));

    }

    @Test
    public void building_meetBoolConditions_isCorrect_false() {

        Building building1 = new Building();
        building1.setArea("West");

        Building building2 = new Building();
        building2.setArea("West");
        building2.setAir_conditioning("True");
        assertFalse(building2.meetBoolConditions(building1));

    }

    @Test
    public void searchRecord_getBuildingName_isCorrect(){
        Search_Record record = new Search_Record();
        Building building1 = new Building();
        building1.setName("Paterson");
        Building building2 = new Building();
        building2.setName("Baker");
        building2.setArea("South");
        List<Building> buildingList = new ArrayList<>();
        buildingList.add(building1);
        buildingList.add(building2);
        record.setBuildings(buildingList);

        List<String> expected = new ArrayList<>();
        expected.add("Paterson");
        expected.add("Baker");

        assertEquals(expected, record.getBuildingName());
    }
}