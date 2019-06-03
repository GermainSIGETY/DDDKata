package fr.sauceDallas.getThingsDone.todos.domain.validators;

import org.apache.commons.lang3.StringUtils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Collections;
import java.util.List;

public class AssigneeValidator {
    public static List<ValidationError> validate(String assignee) {
        if (StringUtils.isBlank(assignee)) {
            return Collections.singletonList(ValidationError.EMPTY_FIELD);
        } else
            return validateFormat(assignee);
    }

    private static List<ValidationError> validateFormat(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            return Collections.singletonList(ValidationError.INVALID_EMAIL_FORMAT);
        }
        return Collections.EMPTY_LIST;
    }
}
