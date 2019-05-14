package fr.sauceDallas.getThingsDone.todos.domain.validators;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DescriptionValidatorTest {

    @Test
    public void testBlankDesc() {
        assertThat(DescriptionValidator.validate("   ")).contains(ValidationError.EMPTY_FIELD);
    }

    @Test
    public void testNoBlankDesc() {
        assertThat(DescriptionValidator.validate("  toto ")).isEmpty();
    }

}
