package com.example.convidados.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//com a implementação do ROOM aqui, a minha classe GuestModel passa a ser considerada uma entidade e isso significa que será salva no banco de dados
//então, o ROOM entende que GuestModel + os seus atributos vão ser persistidos no banco de dados... ele mesmo criará, pois já sabe a coluna e os tipos das colunas
//com isso, não preciso mais me preocupar com a criação da tabela, uma vez que, ele mesmo pode fazer por mim
@Entity(tableName = "Guest")
class GuestModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "presence")
    var presence: Boolean = false
}
