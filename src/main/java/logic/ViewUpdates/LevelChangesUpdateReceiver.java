package logic.ViewUpdates;

/**
 * This interface is the receiver to the LevelChangesUpdates, this class is the one that the View will use.
 *
 * @author Valentina Sepulveda
 * @version 1.0
 */
public interface LevelChangesUpdateReceiver {

    /**
     * This is the update that changes the amount of balls
     * @param changeBallUpdate that changed the amount of balls.
     */
    void changeBallUpdate(ChangeBallUpdate changeBallUpdate);

    /**
     * This update shows the player that they have won the game
     */
    void winnerUpdate();

    /**
     * This methods shows the view, the score change update
     * @param scoreChangeUpdate the update that it is sent
     */
    void scoreChangeUpdate(ScoreChangeUpdate scoreChangeUpdate);

    /**
     * This method shows the view, that level change.
     * @param maxScoreAdapter the update that is sent
     */
    void levelChanges(MaxScoreAdapter maxScoreAdapter);

    /**
     * This method shows the view, that a goldenBrick has been destroyed.
     * @param goldBrickAdapter the update that is sent.
     */
    void goldBrickDestroyed(GoldBrickAdapter goldBrickAdapter);
}
