package com.fintech.fraud.rules;

public class RuleResult {
    private int score;
    private String reason;
    private boolean triggered;

    public RuleResult(int score, String reason, boolean triggered) {
        this.score = score;
        this.reason = reason;
        this.triggered = triggered;
    }

    public int getScore() {
        return score;
    }

    public String getReason() {
        return reason;
    }

    public boolean isTriggered() {
        return triggered;
    }
}
