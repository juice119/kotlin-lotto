package lotto

import lotto.domain.LottoPrizeManager
import lotto.domain.LottoPrizePolicy
import lotto.domain.Money
import lotto.domain.TicketSeller
import lotto.dto.UserMoneyInputDto
import lotto.dto.WinningNumbersInputDto
import lotto.util.KotlinRandomGenerate
import lotto.view.BuyLottoInputView
import lotto.view.BuyLottoOutputView

fun main() {
    val lottoTicketPrice = Money(1000)
    val ticketSeller = TicketSeller(lottoTicketPrice, KotlinRandomGenerate)

    println(BuyLottoInputView.showUserMoneyInputGuide())
    val userMoneyInputDto = UserMoneyInputDto(readln())
    val boughtLottoTickets = ticketSeller.buyPossibleLottoTicket(userMoneyInputDto.userMoney)

    println("${boughtLottoTickets.size}를 구매했습니다")
    boughtLottoTickets.forEach {
        println(it.lottoTicketNumbers.value.map { it.value }.sorted().joinToString(prefix = "[", postfix = "]"))
    }
    println()

    println(BuyLottoInputView.showWinningLottoNumbersInputGuide())
    val winningNumbersInputDto = WinningNumbersInputDto(readln())
    println()

    println(BuyLottoOutputView.showWinningStartLabel())
    val lottoPrizeManager = LottoPrizeManager()
    lottoPrizeManager.addUniquePolicy(LottoPrizePolicy(3, Money(5000)))
    lottoPrizeManager.addUniquePolicy(LottoPrizePolicy(4, Money(50000)))
    lottoPrizeManager.addUniquePolicy(LottoPrizePolicy(5, Money(1500000)))
    lottoPrizeManager.addUniquePolicy(LottoPrizePolicy(6, Money(2000000000)))

    val winningStats =
        lottoPrizeManager.getWinningStats(boughtLottoTickets, winningNumbersInputDto.winningLottoTicketNumbers)
    println(BuyLottoOutputView.showAllWinningStat(winningStats))
    println(BuyLottoOutputView.showEarningsRate(Money(boughtLottoTickets.size * lottoTicketPrice.value), winningStats))
}
