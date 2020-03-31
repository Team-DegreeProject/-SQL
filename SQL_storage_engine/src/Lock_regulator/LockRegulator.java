package Lock_regulator;

import java.util.ArrayList;

public class LockRegulator {
    private LockTable locktable;

    public LockRegulator(){
        locktable = new LockTable();
    }

    public void AddShareLock(String tableName){
        ArrayList<LockTableUnit> searchResult = locktable.traverseList(tableName);
        if (searchResult.size() == 0 || !LockUtils.searchExclusive(searchResult)){
            locktable.addUnit(new LockTableUnit(tableName,Configuration.SHARELOCK,false));
        }
        else if(!LockUtils.searchExclusive(searchResult)){

        }
    }




}
