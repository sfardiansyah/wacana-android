package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.utilities

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class IndonesiaCurrency {

    companion object {
        fun valueOf(amount: BigDecimal): String? {
            val localeID = Locale("in", "ID")

            val formatRp = NumberFormat.getCurrencyInstance(localeID)
            formatRp.maximumFractionDigits = 0

            return formatRp.format(amount)
        }
    }
}