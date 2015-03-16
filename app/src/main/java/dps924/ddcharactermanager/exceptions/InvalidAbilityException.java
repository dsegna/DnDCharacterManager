package dps924.ddcharactermanager.exceptions;

public class InvalidAbilityException extends RuntimeException {
    public InvalidAbilityException(String key) {
        super("Error retrieving ability with key: " + key);
    }
}
