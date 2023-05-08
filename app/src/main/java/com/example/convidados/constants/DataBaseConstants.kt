package com.example.convidados.constants

//constantes para tornar o código mais seguro
class DataBaseConstants private constructor() {

    object GUEST {
        const val TABLE_NAME = "Guest"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }
    }
}
