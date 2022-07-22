/*
 * Copyright (c) 2022. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package org.jetbrains.letsPlot.toolkit.geotools

import org.geotools.data.simple.SimpleFeatureCollection
import org.geotools.geojson.geom.GeometryJSON
import org.jetbrains.letsPlot.spatial.SpatialDataset
import org.locationtech.jts.geom.Geometry
import org.opengis.feature.type.GeometryDescriptor

/**
 * Transforms SimpleFeatureCollection to SpatialDataset with feature geometries encoded in GEOJSON format.
 *
 * @param decimals the number of decimals to use when encoding floating point numbers.
 */
fun SimpleFeatureCollection.toSpatialDataset(decimals: Int = 10): SpatialDataset {
    val geojson = GeometryJSON(decimals)
    val (data, geometries) = getDataAndGeometries(this) {
        geojson.toString(it)
    }
    return SpatialDataset.withGEOJSON(data, geometries)
}

private fun getDataAndGeometries(
    featureCollection: SimpleFeatureCollection,
    geometryToString: (Geometry) -> String
): Pair<Map<String, List<Any?>>, List<String>> {
    val attributeDescriptors = featureCollection.schema.attributeDescriptors

    val dataAttributes = attributeDescriptors?.filter { it !is GeometryDescriptor }?.map { it!! } ?: emptyList()
    val geometryAttribute = attributeDescriptors?.find { it is GeometryDescriptor }
        ?: throw IllegalArgumentException("No geometry attribute")


    val crs = (geometryAttribute as GeometryDescriptor).coordinateReferenceSystem
    require(CRSUtil.isWGS84_2D(crs)) {
        "Geometry must use WGS84 coordinate reference system but was: ${crs.name} ."
    }

    val data = dataAttributes.map { it.localName to ArrayList<Any?>() }.toMap()
    val geometries = ArrayList<String>()

    featureCollection.features().use {
        while (it.hasNext()) {
            val feature = it.next()
            val featureGeometry = feature.getAttribute(geometryAttribute.name)
            require(featureGeometry is Geometry) {
                "Not a geometry: [${geometryAttribute.name}] = ${featureGeometry?.javaClass?.simpleName} (feature id: ${feature.id})"
            }
            require(featureGeometry.isValid) { "Invalid geometry, feature id: ${feature.id}" }

            for (dataAttribute in dataAttributes) {
                data[dataAttribute.localName]?.add(feature.getAttribute(dataAttribute.name))
            }

            geometries.add(geometryToString(featureGeometry))
        }
    }
    return Pair(data, geometries)
}