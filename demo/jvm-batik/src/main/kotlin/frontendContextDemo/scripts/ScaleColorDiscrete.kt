/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInBatikContext
import jetbrains.letsPlot.geom.geom_tile
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.scale.scale_color_discrete
import jetbrains.letsPlot.scale.scale_fill_discrete

object ScaleColorDiscrete {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInBatikContext.eval("Color scale (discrete)") {
            discretePaletteWith(mapOf("x" to ('a'..'g')))
            discretePaletteWith(mapOf("x" to ('a'..'z')))
        }
    }

    private fun discretePaletteWith(data: Map<String, Any>) {
        val p = ggplot(data) + ggsize(600, 200) +
                geom_tile(width = 1.0) {
                    x = "x"
                    color = "x"
                    fill = "x"
                }

        (p + scale_color_discrete() + scale_fill_discrete()).show()
    }
}
