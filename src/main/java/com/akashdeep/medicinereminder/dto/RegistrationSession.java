package com.akashdeep.medicinereminder.dto;

public class RegistrationSession {

    private Long chatId;
    private RegistrationStep step;
    private String fullName;
    private String relationship;

    public RegistrationSession(Long chatId, RegistrationStep step) {
        this.chatId = chatId;
        this.step = step;
    }

    public Long getChatId() {
        return chatId;
    }

    public RegistrationStep getStep() {
        return step;
    }

    public void setStep(RegistrationStep step) {
        this.step = step;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}