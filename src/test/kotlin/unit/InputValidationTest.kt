package unit

import InputValidation
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

class InputValidationTest {

    val inputValidation = InputValidation()

    @Nested
    inner class IsValidInputLength {
        @Test
        fun `returns true when input of required length`() {
            val input = listOf("newaccount", "Coco", "Gauff")
            assertTrue(inputValidation.isValidInputLength(input, 3))
        }

        @Test
        fun `returns false when input of required length`() {
            val input = listOf("newaccount", "Coco", "Gauff", "Young")
            assertFalse(inputValidation.isValidInputLength(input, 3))
        }
    }

}