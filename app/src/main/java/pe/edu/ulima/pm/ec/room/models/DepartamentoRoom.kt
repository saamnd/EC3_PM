package pe.edu.ulima.pm.ec.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DepartamentoRoom(


    @PrimaryKey( )
    val departamento: String,


    @ColumnInfo(name = "cantidad")
    val cantidad : Int

)