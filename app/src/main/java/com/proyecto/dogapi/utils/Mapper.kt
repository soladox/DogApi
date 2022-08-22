package com.proyecto.dogapi.utils

abstract class Mapper <T1, T2>{

    abstract fun map(value: T1?): T2?
    abstract fun reverseMap(value: T2?): T1?

    fun map(values: List<T1?>): List<T2?>{
        val returnValues: ArrayList<T2?> = ArrayList<T2?>()

        for (value in values){
            returnValues.add(map(value))
        }
        return returnValues
    }

    fun reverseMap(values: List<T2?>): List<T1?>{
        val returnValues: ArrayList<T1?> = ArrayList<T1?>()

        for (value in values){
            returnValues.add(reverseMap(value))
        }
        return returnValues
    }

    fun map(values: Map<String, T1?>): Map<String, T2?>{
        val returnValues: MutableMap<String, T2?> = HashMap<String, T2?>()

        for ((key,value) in values){
            returnValues[key] = map(value)
        }
        return returnValues
    }

    fun reverseMap(values: Map<String, T2?>): Map<String,T1?>{
        val returnValues: MutableMap<String,T1?> = HashMap<String, T1?>()

        for ((key,value) in values){
            returnValues[key] = reverseMap(value)
        }
        return returnValues
    }
}