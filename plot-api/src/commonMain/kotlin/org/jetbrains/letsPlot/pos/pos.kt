/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("unused")

package org.jetbrains.letsPlot.pos

import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.PosKind
import org.jetbrains.letsPlot.intern.layer.PosOptions

/**
 * Pos options to pass as a value of `position` parameter of layer functions like:
 *
 * ```kotlin
 * val n = 100
 * val m = 5
 * val k = 2
 * val rand = java.util.Random(42)
 * val data = mapOf(
 *     "v" to List(n) { rand.nextInt(m) },
 *     "c" to List(n) { rand.nextInt(k) }
 * )
 * letsPlot(data) +
 *     geomBar(position=Pos.dodge) { x="v"; fill=asDiscrete("c") }
 * ```
 */
private object Pos {
//    val identity = PosOptions(PosKind.IDENTITY)
//    val stack = PosOptions(PosKind.STACK)
//    val dodge = PosOptions(PosKind.DODGE)
//    val fill = PosOptions(PosKind.FILL)
//    val nudge = PosOptions(PosKind.NUDGE)
//    val jitter = PosOptions(PosKind.JITTER)

//    @Suppress("SpellCheckingInspection")
//    val jitterdodge = PosOptions(PosKind.JITTER_DODGE)

    // ToDo: use constants form LP (when become available)
    // parameter names
    const val DODGE_WIDTH = "width"
    const val JITTER_WIDTH = "width"
    const val JITTER_HEIGHT = "height"
    const val NUDGE_WIDTH = "x"
    const val NUDGE_HEIGHT = "y"
    const val JD_DODGE_WIDTH = "dodge_width"
    const val JD_JITTER_WIDTH = "jitter_width"
    const val JD_JITTER_HEIGHT = "jitter_height"
}

val positionIdentity = PosOptions(PosKind.IDENTITY)
val positionStack = PosOptions(PosKind.STACK)
val positionFill = PosOptions(PosKind.FILL)


/**
 * Adjust position by dodging overlaps to the side.
 *
 * ## Examples
 *
 * - [error_bars.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/error_bars.ipynb)
 *
 * @param width Dodging width, when different to the width of the individual elements. This is useful when you want to align narrow geoms with wider geoms.
 * The value of width is relative and typically ranges between 0 and 1. Values that are greater than 1 lead to overlapping of the objects.
 */
fun positionDodge(width: Number? = null) =
    PosOptions(
        PosKind.DODGE,
        Options.of(Pos.DODGE_WIDTH to width)
    )

/**
 * Adjust position by assigning random noise to points. Better for discrete values.
 *
 * ## Examples
 *
 * - [scatter_plot.ipynb](https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/scatter_plot.ipynb)
 *
 * @param width Jittering width. The value of width is relative and typically ranges between 0 and 0.5.
 * Values that are greater than 0.5 lead to overlapping of the points.
 * @param height Jittering height. The value of height is relative and typically ranges between 0 and 0.5.
 * Values that are greater than 0.5 lead to overlapping of the points.
 */
fun positionJitter(width: Number? = null, height: Number? = null) =
    PosOptions(
        PosKind.JITTER,
        Options.of(
            Pos.JITTER_WIDTH to width,
            Pos.JITTER_HEIGHT to height
        )
    )

fun positionNudge(x: Number? = null, y: Number? = null) =
    PosOptions(
        PosKind.NUDGE,
        Options.of(
            Pos.NUDGE_WIDTH to x,
            Pos.NUDGE_HEIGHT to y
        )
    )

fun positionJitterDodge(
    dodgeWidth: Number? = null,
    jitterWidth: Number? = null,
    jitterHeight: Number? = null
) =
    PosOptions(
        PosKind.JITTER_DODGE,
        Options.of(
            Pos.JD_DODGE_WIDTH to dodgeWidth,
            Pos.JD_JITTER_WIDTH to jitterWidth,
            Pos.JD_JITTER_HEIGHT to jitterHeight
        )
    )
