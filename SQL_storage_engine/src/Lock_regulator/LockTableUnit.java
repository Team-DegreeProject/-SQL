package Lock_regulator;

public class LockTableUnit {
    String name;
    String lockType;
    Boolean wait;
    LockTableUnit nextUnit;


    public LockTableUnit(String n, String lt , Boolean w, LockTableUnit nu){
        name = n;
        lockType = lt;
        wait = w;
        nextUnit = nu;
    }

    public void setPointer(LockTableUnit ltu){
        nextUnit = ltu;
    }
}
