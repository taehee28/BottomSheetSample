package com.example.bottomsheetsample.bottomsheet.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bottomsheetsample.R
import com.example.bottomsheetsample.databinding.ItemBlueBinding
import com.example.bottomsheetsample.databinding.ItemPinkBinding

object ColorType {
    const val BLUE = 1
    const val PINK = 2
    const val YELLOW = 3
    const val GREEN = 4
}

/**
 * 다른 타입의 데이터 모델 2개를 하나의 data class로 묶어서 어댑터의 데이터 타입으로 쓰는 어댑터 예제
 */
class ColorListAdapter : ListAdapter<ColorModel, ColorViewHolder>(ColorDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return if (viewType == ColorType.BLUE) {
            val binding: ItemBlueBinding = ItemBlueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            BlueViewHolder(binding)
        } else {
            val binding: ItemPinkBinding = ItemPinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            PinkViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    inner class BlueViewHolder(private val binding: ItemBlueBinding) : ColorViewHolder(binding.root) {
        override fun bind(data: ColorModel) {
            data.blueModel ?: return

            binding.tvContent.text = data.blueModel.content
        }
    }

    inner class PinkViewHolder(private val binding: ItemPinkBinding) : ColorViewHolder(binding.root) {
        override fun bind(data: ColorModel) {
            data.pinkModel ?: return

            binding.tvContent.text = data.pinkModel.content
        }
    }
}

abstract class ColorViewHolder(itemView: View) : ViewHolder(itemView) {
    abstract fun bind(data: ColorModel)
}

class ColorDiffUtil : DiffUtil.ItemCallback<ColorModel>() {
    override fun areItemsTheSame(oldItem: ColorModel, newItem: ColorModel): Boolean {
        return if (oldItem.type == ColorType.BLUE) {
            oldItem.blueModel?.id == newItem.blueModel?.id
        } else {
            oldItem.pinkModel?.id == newItem.pinkModel?.id
        }
    }

    override fun areContentsTheSame(oldItem: ColorModel, newItem: ColorModel): Boolean {
        return if (oldItem.type == ColorType.BLUE) {
            oldItem.blueModel?.content == newItem.blueModel?.content
        } else {
            oldItem.pinkModel?.content == newItem.pinkModel?.content
        }
    }
}