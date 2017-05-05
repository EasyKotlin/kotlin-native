package org.jetbrains.kotlin.native.klib

import kotlin.system.exitProcess
import java.io.File

fun printUsage() {
    println("Usage: klib 'verb' 'library'")
    println("\tinfo\tgeneral information about the library")
    println("\tinstall\tinstall the library to your local cache")
    println("\tlist\tcontents of the library")
    println("\tpack\tmake the library packed")
    println("\tunpack\tmake thelibrary unpacked")
}

class Command(args: Array<String>){
    init {
        if (args.size != 2) {
            printUsage()
            exitProcess(0)
        }
    }
    val verb = args[0]
    val lib = args[1]
}

fun warn(text: String) {
    println("warning: $text")
}

fun error(text: String) {
    println("error: $text")
    exitProcess(1)
}

class Library(val name: String) {

    val file = File(name)

    init {
        if (!file.exists()) {
            error("Could not find '$name'.")
        }
        if (!name.endsWith(".klib")) {
            warn("Expected the library name to end with '.klib'. Proceeding anyways.")
        }
    }

    val isPacked: Boolean
        get() = file.isFile()


    fun info() {
    }

    fun install() {
    }

    fun list() {
    }

    fun pack() {
    }

    fun unpack() {
    }
}

fun main(args: Array<String>) {
    val command = Command(args)

    val library = Library(command.lib)

    when (command.verb) {
        "info"      -> library.info()
        "install"   -> library.install()
        "list"      -> library.list()
        "pack"      -> library.pack()
        "unpack"    -> library.unpack()
        else        -> error("Unknown command ${command.verb}.")
    }
}

