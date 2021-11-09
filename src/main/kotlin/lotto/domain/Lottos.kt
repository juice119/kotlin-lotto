package lotto.domain

@JvmInline
value class Lottos private constructor(
    val lottos: List<Lotto>
) {

    fun match(lotto: Lotto, bonusBall: BonusBall): LottoResult {
        val rankCounts = lottos.map { Rank.rankByMatchCount(lotto.countMatchNumber(it), it.hasLottoNumber(bonusBall.lottoNumber)) }
            .groupingBy { it }
            .eachCount()
        return LottoResult.of(rankCounts)
    }

    companion object {

        fun of(lottos: List<Lotto>): Lottos {
            return Lottos(lottos)
        }

        fun of(lottoGenerator: LottoGenerator): Lottos {
            return Lottos(lottoGenerator.generateLotto())
        }
    }
}