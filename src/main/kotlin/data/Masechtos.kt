enum class Maseches {
    BRACHOS,
    PEAH,
    DEMAI,
    KILAYIM,
    SHVIIS,
    TRUMOS,
    MAASROS,
    MAASER_SHENI,
    CHALLAH,
    ORLAH,
    BIKKURIM,
    SHABBOS,
    ERUVIN,
    PESACHIM,
    SHEKALIM,
    ROSH_HASHANAH,
    YOMA,
    SUKKAH,
    BEITZA,
    TAANIS,
    MEGILLAH,
    MOED_KATTAN,
    CHAGIGAH,
    YEVAMOS,
    KESUBOS,
    NEDARIM,
    NAZIR,
    SOTAH,
    GITTIN,
    KIDDUSHIN,
    BAVA_KAMMA,
    BAVA_METZIA,
    BAVA_BASRA,
    SANHEDRIN,
    MAKKOS,
    SHEVUOS,
    EDYOS,
    AVODAH_ZARAH,
    PIRKEI_AVOS,
    HORAYOS,
    ZEVACHIM,
    MENACHOS,
    CHULLIN,
    BECHOROS,
    ARACHIN,
    TEMURAH,
    KERISOS,
    MEILAH,
    TAMID,
    MIDDOS,
    KINNIM,
    KEILIM,
    OHALOS,
    NEGAIM,
    PARAH,
    TAHAROS,
    MIKVAOS,
    NIDDAH,
    MACHSHIRIN,
    ZAVIM,
    TEVUL_YOM,
    YADAYIM,
    UKTZIM;

    override fun toString() = this.name.split("_").joinToString(" ") { it[0] + it.substring(1).lowercase() }

}