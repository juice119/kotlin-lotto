package lotto.view

private const val NO_PAYMENT = 0
private const val ENTER_PAYMENT_FOR_LOTTO = "구입금액을 입력해주세요."
private const val ENTER_LAST_LUCKY_NUMBERS = "지난 주 당첨 번호를 입력해주세요."

object InputView {
    fun readPayment(): Int {
        println(ENTER_PAYMENT_FOR_LOTTO)
        return readLine()?.toIntOrNull() ?: NO_PAYMENT
    }

    fun readLuckyNumbers(): List<Int> {
        println("\n${ENTER_LAST_LUCKY_NUMBERS}")
        var numbers = readLine()
        while (numbers.isNullOrBlank()) {
            numbers = readLine()
        }
        return numbers.split(",").map { it.toInt() }
    }
}