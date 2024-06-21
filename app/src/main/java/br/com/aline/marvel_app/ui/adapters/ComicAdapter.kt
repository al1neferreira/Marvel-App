package br.com.aline.marvel_app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import br.com.aline.marvel_app.R
import br.com.aline.marvel_app.data.model.character.CharacterModel
import br.com.aline.marvel_app.data.model.comic.ComicModel
import br.com.aline.marvel_app.databinding.ItemCharacterBinding
import br.com.aline.marvel_app.databinding.ItemComicBinding
import br.com.aline.marvel_app.util.limitDescription
import com.bumptech.glide.Glide

class ComicAdapter : RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {

    inner class ComicViewHolder(
        val binding: ItemComicBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<ComicModel>() {
        override fun areItemsTheSame(oldItem: ComicModel, newItem: ComicModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: ComicModel, newItem: ComicModel): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.title == newItem.title
                    && oldItem.description == newItem.description
                    && oldItem.thumbnailModel.path == newItem.thumbnailModel.path
                    && oldItem.thumbnailModel.extension == newItem.thumbnailModel.extension
        }
    }

    var comics: List<ComicModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    val differ = AsyncListDiffer(this, differCallBack)

    override fun getItemCount(): Int = comics.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(
            ItemComicBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = comics[position]
        holder.binding.apply {
            tvNameComic.text = comic.title
            tvDescriptionComic.text = comic.description

            Glide.with(holder.itemView.context)
                .load(comic.thumbnailModel.path + "." + comic.thumbnailModel.extension)
                .into(imgComic)
        }

    }
}