package fr.sauceDallas.getThingsDone.todos.domain.validators;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TitleValidatorTest {

    @Test
    public void testBlankTitle() {
       assertThat(TitleValidator.validate("   ")).contains(ValidationError.EMPTY_FIELD);
    }

    @Test
    public void testNoBlankTitle() {
        assertThat(TitleValidator.validate("  toto ")).isEmpty();
    }

}
