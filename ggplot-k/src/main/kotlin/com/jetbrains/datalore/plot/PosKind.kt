package com.jetbrains.datalore.plot

enum class PosKind {
    IDENTITY,
    STACK,
    DODGE,
    FILL,
    NUDGE,
    JITTER,
    JITTER_DODGE {
        @Suppress("SpellCheckingInspection")
        override fun optionName() = "jitterdodge"
    };

    open fun optionName() = name.toLowerCase()
}