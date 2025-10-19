package com.keepsafe.bankcardserver.utils

object HexConverter {

    fun String.hexStringToByteArray(): ByteArray {
        require(this.length % 2 == 0) { "Hex string must have an even length" }
        return this.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
    }
}