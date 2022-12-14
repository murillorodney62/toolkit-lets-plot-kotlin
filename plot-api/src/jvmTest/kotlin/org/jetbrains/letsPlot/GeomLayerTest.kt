/*
 * Copyright (c) 2021. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot

import org.jetbrains.letsPlot.Stat.density
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.intern.GeomKind
import org.jetbrains.letsPlot.intern.LayerAssert
import org.jetbrains.letsPlot.intern.StatKind
import org.junit.Test

class GeomLayerTest {

    @Test
    fun `geom with default stat`() {
        val l = geomPoint(color = "C") { fill = "F" }

        LayerAssert.assertThat(l)
            .aes("fill", "F")
            .parameter("color", "C")
            .geom()
            .kind(GeomKind.POINT)
            .noMapping().noParameters()
        LayerAssert.assertThat(l)
            .stat()
            .kind(StatKind.IDENTITY)
    }

    @Test
    fun `geom with overridden stat`() {
        val l = geomPoint(color = "C", stat = density(kernel = "gaussian")) { fill = "F" }
        LayerAssert.assertThat(l)
            .aes("fill", "F")
            .parameter("color", "C")
            .parameter("kernel", "gaussian")
            .geom()
            .kind(GeomKind.POINT)
            .noMapping().noParameters()
        LayerAssert.assertThat(l)
            .stat()
            .kind(StatKind.DENSITY)
            .parameter("kernel", "gaussian")
            .noMapping()
    }

    @Test
    fun `geom with overridden stat, mapping`() {
        val l =
            geomPoint(color = "C", stat = density(kernel = "gaussian") { x = "F1"; weight = "W" }) {
                x = "F"
            }

        LayerAssert.assertThat(l)
            .aes("weight", "W")               // from stat
            .parameter("color", "C")            // from layer
            .parameter("kernel", "gaussian")    // from stat

        LayerAssert.assertThat(l)
            .geom()
            .kind(GeomKind.POINT)
            .noMapping()

        LayerAssert.assertThat(l)
            .stat()
            .kind(StatKind.DENSITY)
            .aes("x", "F1")
            .parameter("kernel", "gaussian")
    }
}