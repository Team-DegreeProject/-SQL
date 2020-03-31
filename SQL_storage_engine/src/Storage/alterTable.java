package Storage;

import java.util.ArrayList;

public class alterTable {
    ArrayList<alterUnit> alterUnitsList;

    public alterTable(){
        alterUnitsList = new ArrayList<>();
    }

    public void addAlterUnit(alterUnit newUnit){
        alterUnitsList.add(newUnit);
    }
}
