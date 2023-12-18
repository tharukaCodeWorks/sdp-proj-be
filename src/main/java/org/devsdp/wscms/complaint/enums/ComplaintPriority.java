package org.devsdp.wscms.complaint.enums;


public enum ComplaintPriority {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("MEDIUM"),
    CRITICAL("MEDIUM");

    private final String weight;

    ComplaintPriority(String weight) {
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }
}