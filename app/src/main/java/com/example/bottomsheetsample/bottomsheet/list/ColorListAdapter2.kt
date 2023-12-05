package com.example.bottomsheetsample.bottomsheet.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomsheetsample.databinding.ItemBlueBinding
import com.example.bottomsheetsample.databinding.ItemGreenBinding
import com.example.bottomsheetsample.databinding.ItemPinkBinding
import com.example.bottomsheetsample.databinding.ItemYellowBinding

/**
 * 데이터 모델 2개를 하나의 인터페이스로 묶어서 어댑터의 데이터 타입으로 쓰는 어댑터 예제
 */
class ColorListAdapter2 : ListAdapter<Color, ColorViewHolder2<*>>(ColorDiffUtil2()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder2<*> {
        return when (viewType) {
            ColorType.YELLOW -> {
                val binding = ItemYellowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                YellowViewHolder(binding)
            }
            ColorType.GREEN -> {
                val binding = ItemGreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GreenViewHolder(binding)
            }
            else -> throw IllegalArgumentException("잘못된 타입")
        }

    }

    override fun onBindViewHolder(holder: ColorViewHolder2<*>, position: Int) {
        when (holder) {
            is YellowViewHolder -> holder.bind(getItem(position) as YellowModel)
            is GreenViewHolder -> holder.bind(getItem(position) as GreenModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is YellowModel -> ColorType.YELLOW
            is GreenModel -> ColorType.GREEN
            else -> throw IllegalArgumentException("잘못된 타입")
        }
    }

    inner class YellowViewHolder(private val binding: ItemYellowBinding) : ColorViewHolder2<YellowModel>(binding.root) {
        override fun bind(data: YellowModel) {
            binding.tvContent.text = data.content
        }
    }

    inner class GreenViewHolder(private val binding: ItemGreenBinding) : ColorViewHolder2<GreenModel>(binding.root) {
        override fun bind(data: GreenModel) {
            binding.tvContent.text = data.content
        }
    }
}

abstract class ColorViewHolder2<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: T)
}

class ColorDiffUtil2 : DiffUtil.ItemCallback<Color>() {
    override fun areItemsTheSame(oldItem: Color, newItem: Color): Boolean {
        return when {
            (oldItem is YellowModel) and (newItem is YellowModel) -> {
                (oldItem as YellowModel).id == (newItem as YellowModel).id
            }
            (oldItem is GreenModel) and (newItem is GreenModel) -> {
                (oldItem as GreenModel).id == (newItem as GreenModel).id
            }
            else -> throw IllegalArgumentException("잘못된 타입")
        }
    }

    override fun areContentsTheSame(oldItem: Color, newItem: Color): Boolean {
        return when {
            (oldItem is YellowModel) and (newItem is YellowModel) -> {
                (oldItem as YellowModel).content == (newItem as YellowModel).content
            }
            (oldItem is GreenModel) and (newItem is GreenModel) -> {
                (oldItem as GreenModel).content == (newItem as GreenModel).content
            }
            else -> throw IllegalArgumentException("잘못된 타입")
        }
    }
}