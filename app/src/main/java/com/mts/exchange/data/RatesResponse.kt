package com.mts.exchange.data


import com.google.gson.annotations.SerializedName

data class RatesResponse(
    @SerializedName("AED")
    val aED: Double,
    @SerializedName("AFN")
    val aFN: Double,
    @SerializedName("ALL")
    val aLL: Double,
    @SerializedName("AMD")
    val aMD: Double,
    @SerializedName("ANG")
    val aNG: Double,
    @SerializedName("AOA")
    val aOA: Double,
    @SerializedName("ARS")
    val aRS: Double,
    @SerializedName("AUD")
    val aUD: Double,
    @SerializedName("AWG")
    val aWG: Double,
    @SerializedName("AZN")
    val aZN: Double,
    @SerializedName("BAM")
    val bAM: Double,
    @SerializedName("BBD")
    val bBD: Double,
    @SerializedName("BDT")
    val bDT: Double,
    @SerializedName("BGN")
    val bGN: Double,
    @SerializedName("BHD")
    val bHD: Double,
    @SerializedName("BIF")
    val bIF: Double,
    @SerializedName("BMD")
    val bMD: Double,
    @SerializedName("BND")
    val bND: Double,
    @SerializedName("BOB")
    val bOB: Double,
    @SerializedName("BRL")
    val bRL: Double,
    @SerializedName("BSD")
    val bSD: Double,
    @SerializedName("BTC")
    val bTC: Int,
    @SerializedName("BTN")
    val bTN: Double,
    @SerializedName("BWP")
    val bWP: Double
)

fun RatesResponse.getRatesNames(): List<String> {
    val fields = RatesResponse::class.java.declaredFields
    return fields.map { it.name.uppercase() }
}

fun RatesResponse.getRateByName(rateName: String): Double = when (rateName) {
    "AED" -> this.aED
    "AFN" -> this.aFN
    "ALL" -> this.aLL
    "AMD" -> this.aMD
    "ANG" -> this.aNG
    "AOA" -> this.aOA
    "ARS" -> this.aRS
    "AUD" -> this.aUD
    "AZN" -> this.aZN
    "BAM" -> this.bAM
    "BBD" -> this.bBD
    "BDT" -> this.bDT
    "BGN" -> this.bGN
    "BHD" -> this.bHD
    "BIF" -> this.bIF
    "BMD" -> this.bMD
    "BND" -> this.bND
    "BOB" -> this.bOB
    "BRL" -> this.bRL
    "BSD" -> this.bSD
    "BTN" -> this.bTN
    "BWP" -> this.bWP
    else -> 0.0

}