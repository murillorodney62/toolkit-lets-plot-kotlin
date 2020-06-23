/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

@file:Suppress("FunctionName")

package jetbrains.letsPlot.scale

import jetbrains.datalore.plot.base.Aes
import jetbrains.datalore.plot.config.Option
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.Scale

/**
 * Continuous scale for size (~radius)
 *
 * @param range A pair of numbers.
 *              The range of sizes that the input values are mapped to.
 * @param name  The name of the scale - used as the axis label or the legend title.
 *              If None, the default, the name of the scale
 *              is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A vector specifying the data range for the scale. and the default order of their display in guides.
 * @param expand A numeric vector of length two giving multiplicative and additive expansion constants.
 * @param naValue Missing values will be replaced with this value.
 * @param guide TBD
 * @param trans TBD
 */
fun scale_size(
    range: Pair<Number, Number>,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: List<Number>? = null,
    expand: Any? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = Scale(
    aesthetic = Aes.SIZE,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    expand = expand,
    naValue = naValue,
    guide = guide,
    trans = trans,
    otherOptions = Options(
        mapOf(
            Option.Scale.RANGE to range.toList()
        )
    )
)

/**
 * Continuous scale for size (~radius) that maps 0 value to 0 size
 *
 * @param maxSize The upper limit of size that the input values are mapped to.
 * @param name  The name of the scale - used as the axis label or the legend title.
 *              If None, the default, the name of the scale
 *              is taken from the first mapping used for that aesthetic.
 * @param breaks A numeric vector of positions (of ticks).
 * @param labels A vector of labels (on ticks).
 * @param limits A vector specifying the data range for the scale. and the default order of their display in guides.
 * @param expand A numeric vector of length two giving multiplicative and additive expansion constants.
 * @param naValue Missing values will be replaced with this value.
 * @param guide TBD
 * @param trans TBD
 */
fun scale_size_area(
    maxSize: Number,
    name: String? = null,
    breaks: List<Number>? = null,
    labels: List<String>? = null,
    limits: List<Number>? = null,
    expand: Any? = null,
    naValue: Any? = null,
    guide: Any? = null,
    trans: Any? = null
) = Scale(
    aesthetic = Aes.SIZE,
    name = name,
    breaks = breaks,
    labels = labels,
    limits = limits,
    expand = expand,
    naValue = naValue,
    guide = guide,
    trans = trans,
    otherOptions = Options(
        mapOf(
            Option.Scale.MAX_SIZE to maxSize,
            Option.Scale.SCALE_MAPPER_KIND to "size_area"
        )
    )
)
