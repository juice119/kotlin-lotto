package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@Suppress("NonAsciiCharacters")
class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 23, 45])
    fun `1~45 사이의 로또 번호 생성`(value: Int) {
        // when
        val create = { LottoNumber.valueOf(value) }

        // then
        assertDoesNotThrow(create)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 46])
    fun `1~45 이외의 숫자로 로또 번호를 생성하면 예외 발생`(value: Int) {
        // when
        val create: () -> Unit = { LottoNumber.valueOf(value) }

        // then
        assertThrows<IllegalArgumentException>(create)
    }

    @Test
    fun `로또 번호는 오름차순으로 정렬된다`() {
        // given
        val numbers = listOf(
            LottoNumber.valueOf(10),
            LottoNumber.valueOf(5),
            LottoNumber.valueOf(6),
            LottoNumber.valueOf(9),
            LottoNumber.valueOf(2),
        )

        // when
        val result = numbers.sorted()

        // then
        val expected = listOf(
            LottoNumber.valueOf(2),
            LottoNumber.valueOf(5),
            LottoNumber.valueOf(6),
            LottoNumber.valueOf(9),
            LottoNumber.valueOf(10),
        )
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `문자열로부터 로또 번호를 만들 수 있다`() {
        // given
        val number = "35"

        // when
        val result = LottoNumber.valueOf(number)

        // then
        assertThat(result).isEqualTo(LottoNumber.valueOf(35))
    }

    @Test
    fun `정수가 아닌 문자열은 허용하지 않는다`() {
        // given
        val number = "ab"

        // when
        val create: () -> Unit = { LottoNumber.valueOf(number) }

        // then
        assertThrows<IllegalArgumentException>(create)
    }
}