package Lock_regulator;

import java.util.ArrayList;

public class LockTable {
    private ArrayList<LockTableUnit> unitsList;

    public void addUnit(LockTableUnit newUnit){
        if(unitsList.size() != 0){
            unitsList.get(unitsList.size()-1).setPointer(newUnit);
        }
        unitsList.add(newUnit);

    }

    public void remoteTopUnit(){
        unitsList.remove(0);
    }

    public LockTableUnit getCurrentUnit(){
        return  unitsList.get(0);
    }
}
