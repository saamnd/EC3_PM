package pe.edu.ulima.pm.swapp.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity
data class PersonaRoom(

    @ColumnInfo(name = "fecha_corte")
    val fecha_corte : String,
    @ColumnInfo(name = "departamento")
    val departamento : String,
    @ColumnInfo(name = "provincia")
    val provincia : String,
    @ColumnInfo(name = "distrito")
    val distrito : String,
    @ColumnInfo(name = "metododx")
    val metododx : String,
    @ColumnInfo(name= "id_persona")
    val id : Int
)