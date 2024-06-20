package br.com.aline.marvel_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.aline.marvel_app.R
import br.com.aline.marvel_app.domain.model.Character
import com.bumptech.glide.Glide

class CharacterListAdapter(
    private val context: Context,
    var itemList: ArrayList<Character>
) : RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {


    inner class CharacterListViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val characterName: TextView = view.findViewById(R.id.tv_characterName)
        val characterDesc: TextView = view.findViewById(R.id.tv_characterDesc)
        val thumbnail: ImageView = view.findViewById(R.id.img_character)
        val cardCharacter: LinearLayout = view.findViewById(R.id.charactersLinearLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_character, parent, false)
        return CharacterListViewHolder(view)
    }


    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val list = itemList[position]
        holder.characterName.text = list.name
        holder.characterDesc.text = list.description
        val imageUrl = "${list.thumbnail.replace("http", "https")}/portrait_xlarge.${list.thumbnailExt}"
        Glide.with(context).load(imageUrl).into(holder.thumbnail)
        holder.cardCharacter.setOnClickListener{
            Toast.makeText(context, "Visualizar", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    fun setData(characterList: ArrayList<Character>){
        this.itemList.addAll(characterList)
        notifyDataSetChanged()
    }
}

