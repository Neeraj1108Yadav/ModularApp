package com.nano.modularapp

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

/**
 * Created By Neeral Yadav on 07/08/24
 */
object InputStreamFile {

    fun getFile(inputStream: InputStream):String{
        val reader = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
        val stringBuilder = StringBuilder()
        var line: String?

        while (reader.readLine().also { line = it } != null) {
            stringBuilder.append(line).append('\n')
        }

        return stringBuilder.toString()
    }
}