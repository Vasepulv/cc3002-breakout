package logic.ViewUpdates;

import logic.ViewUpdates.LevelChangesUpdate;
import logic.ViewUpdates.LevelChangesUpdateReceiver;

/**
 * This update shows the view that the player has won the game.
 *
 * @author Valentina Sepulveda
 * @version 1.0
 */
public class WinnerUpdate implements LevelChangesUpdate {
    public void accept(LevelChangesUpdateReceiver levelChangesUpdateReceiver){
        levelChangesUpdateReceiver.winnerUpdate();
    }
}
