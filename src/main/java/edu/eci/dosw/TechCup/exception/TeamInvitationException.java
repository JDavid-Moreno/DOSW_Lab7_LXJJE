package edu.eci.dosw.TechCup.exception;

public class TeamInvitationException extends RuntimeException {
    public static final String TEAM_NOT_FOUND = "Team not found";
    public static final String INVITATION_NOT_FOUND = "Invitation not found";
    public static final String USER_NOT_FOUND = "User not found";
    public TeamInvitationException(String message) {
        super(message);
    }
}
