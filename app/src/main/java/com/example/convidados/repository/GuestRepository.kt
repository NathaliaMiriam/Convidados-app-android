package com.example.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.model.GuestModel

//é o repositório ... Aqui eu insiro e altero os dados, ou seja, manipulo os dados usando a conexão com o banco
class GuestRepository private constructor(context: Context) {

    //instancia o banco de dados
    private val guestDataBase = GuestDataBase(context)

    //singleton
    companion object {
        private lateinit var repository: GuestRepository

        //instancia o repositório
        fun getInstance(context: Context): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }


    //insere convidado no banco de dados
    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }


    //atualiza o banco de dados onde(where) o id seja = a 'guest.id'
    fun update(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase //abre a conexão com o banco
            val presence = if (guest.presence) 1 else 0

            //são os valores
            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            //são os critérios
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + "= ?" //interpola com os valores do args ... "onde o ID for = a um valor"
            val args = arrayOf(guest.id.toString()) //dentro passo os valores do tipo string ... passei 1, pois só tenho 1 ponto de interrogação e converti p string

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args) //tabela + vals e roda a aplicação ... acontece o update c os critérios alinhados
            true
        } catch (e: Exception) {
            false
        }
    }


    //deleta convidado do banco de dados pelo seu id
    fun delete(id: Int): Boolean {
        return try {
            val db = guestDataBase.writableDatabase //abre a conexão com o banco

            //são os critérios
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + "= ?" //interpola com os valores do args ... "onde o ID for = a um valor"
            val args = arrayOf(id.toString()) //dentro passo os valores do tipo string ... passei 1, pois só tenho 1 ponto de interrogação e converti p string

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args) //tabela + vals e roda a aplicação ... acontece o delete c os critérios alinhados
            true
        } catch (e: Exception) {
            false
        }
    }


    //faz a seleção de somente 1 convidado - buscado pelo id
    fun get(id: Int): GuestModel? { //retorna um GuestModel nulo

        var guest: GuestModel? = null //aceita o nulo

        try {
            val db = guestDataBase.readableDatabase //abre a conexão com o banco e lê os dados

            //array das colunas da tabela --> colocado ali na query()
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            //são os critérios --> filtro a ser aplicado
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + "= ?" //interpola com os valores do args ... "onde o ID for = a um valor"
            val args = arrayOf(id.toString()) //dentro passo os valores do tipo string ... passei 1, pois só tenho 1 ponto de interrogação e converti p string

            //abre e retorna o cursor - lista os dados
            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME, projection, selection, args, null, null, null)

            //interação com o cursor ... desconsiderar as linhas vermelhas, pois não há nada de errado no código, é um bug do Android Studio
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) { //o cursor passa linha a linha da tabela

                    ///busca/pega os valores NAME - PRESENCE da tabela:

                    //posição da coluna NAME (index da col.) --> getString() pois o NAME é um text
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))

                    //posição da coluna PRESENCE (index da col.) --> getInt() pois o PRESENCE retorna um inteiro
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))


                    //monta o objeto com os dados retornados - a cada passada de linha os dados são adicionados e o guest é preenchido
                    guest = GuestModel(id, name, presence == 1)
                }
            }

            //fecha o cursor
            cursor.close()

        } catch (e: Exception) {
            return guest
        }

        return guest
    }


    //faz a seleção - listagem dos dados dos convidados
    fun getAll(): List<GuestModel> { //retorna uma lista de GuestModel

        val list = mutableListOf<GuestModel>() //define o valor para que a lista seja retornada

        try {
            val db = guestDataBase.readableDatabase //abre a conexão com o banco e lê os dados

            //array das colunas da tabela --> colocado ali na query()
            val selection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            //abre e retorna o cursor - lista os dados
            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME, selection, null, null, null, null, null)

            //interação com o cursor ... desconsiderar as linhas vermelhas, pois não há nada de errado no código, é um bug do Android Studio
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) { //o cursor passa linha a linha da tabela

                    //pega os valores ID - NAME - PRESENCE da tabela:

                    //posição da coluna ID (index da col.) --> getInt() pois o ID é um inteiro
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))

                    //posição da coluna NAME (index da col.) --> getString() pois o NAME é um text
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))

                    //posição da coluna PRESENCE (index da col.) --> getInt() pois o PRESENCE retorna um inteiro
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))


                    //monta o objeto com os dados retornados - a cada passada de linha os dados são adicionados na lista
                    list.add(GuestModel(id, name, presence == 1))
                }
            }

            //fecha o cursor
            cursor.close()

        } catch (e: Exception) {
            return list //retorna a lista vazia, caso caia no catch
        }

        return list //retorna a lista preenchida, caso não caia no catch
    }


    //faz a seleção - listagem dos convidados presentes
    fun getPresent(): List<GuestModel> { //retorna uma lista de GuestModel

        val list = mutableListOf<GuestModel>() //define o valor para que a lista seja retornada

        try {
            val db = guestDataBase.readableDatabase //abre a conexão com o banco e lê os dados

            //abre e retorna o cursor - lista os presentes
            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1", null)

            //interação com o cursor ... desconsiderar as linhas vermelhas, pois não há nada de errado no código, é um bug do Android Studio
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) { //o cursor passa linha a linha da tabela

                    //pega os valores ID - NAME - PRESENCE da tabela:

                    //posição da coluna ID (index da col.) --> getInt() pois o ID é um inteiro
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))

                    //posição da coluna NAME (index da col.) --> getString() pois o NAME é um text
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))

                    //posição da coluna PRESENCE (index da col.) --> getInt() pois o PRESENCE retorna um inteiro
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))


                    //monta o objeto com os dados retornados - a cada passada de linha os dados são adicionados na lista
                    list.add(GuestModel(id, name, presence == 1))
                }
            }

            //fecha o cursor
            cursor.close()

        } catch (e: Exception) {
            return list //retorna a lista vazia, caso caia no catch
        }

        return list //retorna a lista preenchida, caso não caia no catch
    }


    //faz a seleção - listagem dos convidados ausentes
    fun getAbsent(): List<GuestModel> { //retorna uma lista de GuestModel

        val list = mutableListOf<GuestModel>() //define o valor para que a lista seja retornada

        try {
            val db = guestDataBase.readableDatabase //abre a conexão com o banco e lê os dados

            //abre e retorna o cursor - lista os ausentes
            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 0", null)

            //interação com o cursor ... desconsiderar as linhas vermelhas, pois não há nada de errado no código, é um bug do Android Studio
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) { //o cursor passa linha a linha da tabela

                    //pega os valores ID - NAME - PRESENCE da tabela:

                    //posição da coluna ID (index da col.) --> getInt() pois o ID é um inteiro
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))

                    //posição da coluna NAME (index da col.) --> getString() pois o NAME é um text
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))

                    //posição da coluna PRESENCE (index da col.) --> getInt() pois o PRESENCE retorna um inteiro
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))


                    //monta o objeto com os dados retornados - a cada passada de linha os dados são adicionados na lista
                    list.add(GuestModel(id, name, presence == 1))
                }
            }

            //fecha o cursor
            cursor.close()

        } catch (e: Exception) {
            return list //retorna a lista vazia, caso caia no catch
        }

        return list //retorna a lista preenchida, caso não caia no catch
    }
}
