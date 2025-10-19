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
            val input = listOf("Coco", "Gauff")
            assertTrue(inputValidation.isValidInputLength(input, 2))
        }

        @Test
        fun `returns false when input of required length`() {
            val input = listOf("Coco", "Gauff", "Young")
            assertFalse(inputValidation.isValidInputLength(input, 2))
        }
    }

    @Nested
    inner class IsValidAmount {
        @Test
        fun `returns true when amount has 2 decimal places` () {
            val deposit = "10.00"
            assertTrue(inputValidation.isValidCurrencyFormat(deposit))
        }

        @Test
        fun `returns true when amount has no decimal places` () {
            val deposit = "10"
            assertTrue(inputValidation.isValidCurrencyFormat(deposit))
        }

        @Test
        fun `returns false when amount is not a number`() {
            val deposit = "10a"
            assertFalse(inputValidation.isValidCurrencyFormat(deposit))
        }
    }

}