package edu.eci.dosw.TechCup.exception;

public class TeamInvitationException extends RuntimeException {
    public static final String TEAM_INVITATION_NOT_FOUND = "Team invitation not found";
    public static final String INVITED_BY_NOT_FOUND = "The user who sends the invitation was not found";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String INVITED_BY_MUST_BE_CAPTAIN = "The user who sends the invitation is not a captain";
    public static final String INVITATION_MUST_BE_PENDING = "The invitation is not pending";
    public TeamInvitationException(String message) {
        super(message);
    }
}
