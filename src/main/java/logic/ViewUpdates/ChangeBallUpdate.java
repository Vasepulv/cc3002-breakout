package logic.ViewUpdates;

import logic.ViewUpdates.LevelChangesUpdate;
import logic.ViewUpdates.LevelChangesUpdateReceiver;

/**
 * This represents the update of when a ball is dropped, changing the view;
 *
 * @author Valentina Sepulveda
 * @version 1.0
 */
public class ChangeBallUpdate implements LevelChangesUpdate {
    private int sign;
    public ChangeBallUpdate(int sign){
        this.sign=sign;
    }

    /**
     * This method returns the sign of the action to take with the balls, by either increase it or reduce it.
     * @return sign
     */
    public int getSign(){
        return sign;
    }

    @Override
    public void accept(LevelChangesUpdateReceiver update) {
        update.changeBallUpdate(this);
    }
}
