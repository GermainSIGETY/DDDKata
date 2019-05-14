package fr.sauceDallas.getThingsDone.todos.domain.validators;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TodoIdValidatorTest {

    @Test
    public void testBlankDesc() {
        assertThat(TodoIdValidator.validate(null)).contains(ValidationError.EMPTY_FIELD);
    }

    @Test
    public void testNoBlankDesc() {
        assertThat(TodoIdValidator.validate(-4545L)).contains(ValidationError.INVALID_NUMBER);
    }
}
