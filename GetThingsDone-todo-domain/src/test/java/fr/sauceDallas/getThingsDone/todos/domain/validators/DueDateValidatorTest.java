package fr.sauceDallas.getThingsDone.todos.domain.validators;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DueDateValidatorTest {

    @Test
    public void testBlankDesc() {
        assertThat(DueDateValidator.validate(null)).contains(ValidationError.EMPTY_FIELD);
    }

    @Test
    public void testNoBlankDesc() {
        assertThat(DueDateValidator.validate(-4545L)).contains(ValidationError.INVALID_NUMBER);
    }
}
