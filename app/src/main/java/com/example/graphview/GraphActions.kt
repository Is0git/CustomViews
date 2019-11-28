package com.example.graphview

interface GraphActions {

    fun addLines(lines: Lines)

    fun getMax(linesList: List<Lines>): Lines?

    fun getMin(linesList: List<Lines>): Lines?
}