package com.nistruct.sportstat.data.models

import java.sql.Blob
import java.util.*

data class User(
    var id:UUID,
    var name:String,
    var photo:Blob,
    var position:Position,
    var email:String,
    var phoneNumber:String,
    var team:Team,
    var sport:Sport,
    var isTrainer:Boolean
)