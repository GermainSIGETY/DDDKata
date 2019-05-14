package fr.sauceDallas.getThingsDone.todos.domain.validators;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

public class DescriptionValidator {
    public static List<ValidationError> validate(String title) {
        if (StringUtils.isBlank(title)) {
            return Collections.singletonList(ValidationError.EMPTY_FIELD);
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}
