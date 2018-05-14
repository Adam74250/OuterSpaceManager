package macheda.com.outerspacemanager.outerspacemanager.classes;

/**
 * Created by adammacheda on 16/04/2018.
 */

public class CurrentUser extends User {
    private float gas;

    private int gasModifier;

    private float minerals;

    private int mineralsModifier;

    public float getGas() {
        return gas;
    }

    public void setGas(float gas) {
        this.gas = gas;
    }

    public int getGasModifier() {
        return gasModifier;
    }

    public void setGasModifier(int gasModifier) {
        this.gasModifier = gasModifier;
    }

    public float getMinerals() {
        return minerals;
    }

    public void setMinerals(float minerals) {
        this.minerals = minerals;
    }

    public int getMineralsModifier() {
        return mineralsModifier;
    }

    public void setMineralsModifier(int mineralsModifier) {
        this.mineralsModifier = mineralsModifier;
    }

    private CurrentUser(float gas, int gasModifier, float minerals, int mineralsModifier, int points, String username) {
        super(points, username);
        this.gas = gas;
        this.gasModifier = gasModifier;
        this.minerals = minerals;
        this.mineralsModifier = mineralsModifier;
    }
}