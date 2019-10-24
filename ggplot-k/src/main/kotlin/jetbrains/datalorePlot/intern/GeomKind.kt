package jetbrains.datalorePlot.intern

enum class GeomKind {
    BLANK,
    PATH,
    LINE,
    SMOOTH,
    BAR,
    HISTOGRAM,
    TILE,
    MAP,
    ERROR_BAR,
    POLYGON,
    AB_LINE,
    H_LINE,
    V_LINE,
    BOX_PLOT,
    LIVE_MAP,
    POINT,
    RIBBON,
    AREA,
    DENSITY,
    CONTOUR,
    CONTOURF,
    DENSITY2D,
    DENSITY2DF,
    JITTER,
    FREQPOLY,
    STEP,
    RECT,
    SEGMENT,
    TEXT,
    RASTER,
    IMAGE;

    fun optionName() = name.toLowerCase()
}