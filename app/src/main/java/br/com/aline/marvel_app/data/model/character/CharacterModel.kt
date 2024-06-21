package br.com.aline.marvel_app.data.model.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.aline.marvel_app.data.model.ThumbnailModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "characterModel")
data class CharacterModel(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnailModel: ThumbnailModel
) : Serializable