/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package frontendContextDemo.scripts

import frontendContextDemo.ScriptInJfxContext
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.scaleSizeArea
import kotlin.math.PI
import kotlin.math.sin

object Title {
    @JvmStatic
    fun main(args: Array<String>) {
        ScriptInJfxContext.eval("ggtitle()") {
            val dat = mapOf<String, Any>(
                "x" to (0..100).map { it * 2 * PI / 100 },
                "y" to (0..100).map { sin(it * 2 * PI / 100) }
            )

            run {
                val p = letsPlot(dat) +
                        geomPoint { x = "x"; y = "y" } +
                        ggtitle("Sine function", subtitle = "Using the ggtitle() function") +
                        scaleSizeArea(maxSize = 70, guide = "none")

                p.show()
            }

            run {
                // Use multiple lines
                val p = letsPlot(dat) +
                        geomPoint { x = "x"; y = "y" } +
                        ggtitle("The plot title:\nSine function",
                            subtitle = "The plot subtitle:\nUsing the ggtitle() function") +
                        scaleSizeArea(maxSize = 70, guide = "none")

                p.show()
            }
        }
    }
}