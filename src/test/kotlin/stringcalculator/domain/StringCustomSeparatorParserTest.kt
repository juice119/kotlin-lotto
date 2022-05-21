package stringcalculator.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class StringCustomSeparatorParserTest : DescribeSpec({
    it("커스텀 구분자 문자열에서 구분자 문자(ParserSeparator)를 추출한다") {
        // given
        val successSeparatorString = ";"
        val stringCustomSeparator = "//${successSeparatorString}\n"

        // when
        val stringCustomSeparatorParser = StringCustomSeparatorParser(stringCustomSeparator)

        // then
        stringCustomSeparatorParser.parserSeparator.string shouldBe successSeparatorString
    }

    describe("validation") {
        it("커스텀 구분자 포맷만 있고 구분자 문자열은 없는 경우 IllegalArgumentException 발생") {
            // given
            val stringCustomSeparator = "//\n"

            // then
            shouldThrowExactly<IllegalArgumentException> {
                StringCustomSeparatorParser(stringCustomSeparator)
            }
        }

        it("커스텀 구분자 시작 부분이 없는 경우 IllegalArgumentException 발생") {
            // given
            val stringCustomSeparator = ";\n"

            // then
            shouldThrowExactly<IllegalArgumentException> {
                StringCustomSeparatorParser(stringCustomSeparator)
            }.shouldHaveMessage("시작을 구분하는 문자열(//)이 포함되어 있지 않습니다")
        }

        it("커스텀 구분자 끝이 없는 경우 에러가 IllegalArgumentException 발생") {
            // given
            val stringCustomSeparator = "//;"

            // then
            shouldThrowExactly<IllegalArgumentException> {
                StringCustomSeparatorParser(stringCustomSeparator)
            }.shouldHaveMessage("끝을 구분하는 문자열(\n)이 포함되어 있지 않습니다")
        }
    }
})
