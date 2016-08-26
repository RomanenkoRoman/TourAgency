package ua.nure.romanenko.SummaryTask4.db.entity;

/**
 * Discount
 * Created by Роман on 26.08.2016.
 */
public class Discount {
    private int step;
    private int max;

    public Discount(int max, int step) {
        this.max = max;
        this.step = step;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
