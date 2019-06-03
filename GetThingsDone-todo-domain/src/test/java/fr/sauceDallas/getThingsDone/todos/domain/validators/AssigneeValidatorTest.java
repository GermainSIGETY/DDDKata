package fr.sauceDallas.getThingsDone.todos.domain.validators;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AssigneeValidatorTest {

    @Test
    public void testBlankTitle() {
        assertThat(AssigneeValidator.validate("   ")).contains(ValidationError.EMPTY_FIELD);
    }

    @Test
    public void testInvalidEmailFormat() {
        assertThat(AssigneeValidator.validate("toto ")).contains(ValidationError.INVALID_EMAIL_FORMAT);
    }

    @Test
    public void testvalidAssignee() {
        assertThat(AssigneeValidator.validate("toto@toto.com")).isEmpty();
    }

}
