package logic.ViewUpdates;

import logic.update.ScoreUpdate;

/**
 * An adapter of the ScoreUpdate class, so the view can use it.
 *
 * @author Valentina Sepulveda
 * @version 1.0
 */
public class ScoreChangeUpdate implements LevelChangesUpdate {

    private ScoreUpdate scoreUpdate;

    public ScoreChangeUpdate(ScoreUpdate scoreUpdate){
        this.scoreUpdate=scoreUpdate;
    }

    /**
     * This method returns the ScoreUpdate that the adapter is using
     * @return the ScoreUpdate of the ScoreChangeUpdate
     */
    public ScoreUpdate getScoreUpdate(){
        return scoreUpdate;
    }

    @Override
    public void accept(LevelChangesUpdateReceiver update) {
        update.scoreChangeUpdate(this);
    }
}
