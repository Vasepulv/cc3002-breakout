package logic.ViewUpdates;

/**
 * This interface has been added to make changes to the view, the game sends notification to the View, and then
 * the view makes those changes.
 *
 * @author Valentina Sepulveda
 */
public interface LevelChangesUpdate {

    /**
     * This is an double dispatch method to figure the type that receives the update.
     * @param update
     */
    void accept(LevelChangesUpdateReceiver update);
}
