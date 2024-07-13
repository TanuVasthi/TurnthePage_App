/*package com.example.turnthepage.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.turnthepage.DetailsActivity
import com.example.turnthepage.databinding.MenuItemBinding

class MenuAdapter(private val menuItemsName:MutableList<String>,private val menuItemPrice:MutableList<String>,private val MenuImage:MutableList<Int>,private val requirecontext:Context):
private val itemClickListener:OnClickListener?=null
    //RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }
    override fun getItemCount(): Int = menuItemsName.size
    inner class MenuViewHolder(private val binding:MenuItemBinding):RecyclerView.ViewHolder(binding.root){
        init{
            binding.root.setOnClickListener {
                val position=adapterPosition
                if(position!=RecyclerView.NO_POSITION){
                    itemClickListener?.onItemClick(position)
                }
                //setonclicklistener to append details
                val intent = Intent(requirecontext,DetailsActivity::class.java)
                intent.putExtra("MenuItemName",menuItemsName.get(position))
                intent.putExtra("MenuItemImage",MenuImage.get(position))
                requirecontext.startActivity(intent)
            }
        }
        fun bind(position: Int){
            binding.apply{
                menuBookTitle.text=menuItemsName[position]
                menuPrice.text=menuItemPrice[position]
                menuImage.setImageResource(MenuImage[position])


            }
        }
    }

interface OnClickListener{
    fun onItemClick(position:Int)
}
}

 */
/*package com.example.turnthepage.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.turnthepage.DetailsActivity
import com.example.turnthepage.databinding.MenuItemBinding

class MenuAdapter(
    private val menuItems:List<MenuItem>,
    private val requireContext: Context,
    private val itemClickListener: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuItems.size

    inner class MenuViewHolder(private val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    openDetailsActivity(position)
                }
            }
        }

        private fun openDetailsActivity(position: Int) {
            val menuItem = menuItems[position]

            val intent = Intent(requireContext,DetailsActivity::class.java).apply{
                putExtra("MenuItemName",menuItem.bookTitle)
                putExtra("MenuItemPrice",menuItem.bookPrice)
                putExtra("MenuItemDescription",menuItem.bookDescription)
                putExtra("MenuItemImage",menuItem.bookImage)
            }
            requireContext.startActivity(intent)
        }

        fun bind(position: Int) {
            val menuItem=menuItems[position]
            binding.apply {
                menuBookTitle.text = menuItem.bookTitle
                menuPrice.text = menuItem.bookPrice
                val uri=Uri.parse(menuItem.bookImage)
                Glide.with(requireContext).load(uri).into(menuImage)
            }
        }
    }
}
*/
/*package com.example.turnthepage.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.turnthepage.DetailsActivity
import com.example.turnthepage.databinding.MenuItemBinding
import com.example.turnthepage.model.menuItem

class MenuAdapter(
    private val menuItems: MutableList<menuItem>,
    private val requireContext: Context,
    private val itemClickListener: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuItems.size

    inner class MenuViewHolder(private val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    openDetailsActivity(position)
                }
            }
        }

        private fun openDetailsActivity(position: Int) {
            val menuItem = menuItems[position]

            val intent = Intent(requireContext, DetailsActivity::class.java).apply {
                putExtra("MenuItemName", menuItem.bookTitle)
                putExtra("MenuItemPrice", menuItem.bookPrice)
                putExtra("MenuItemDescription", menuItem.bookDescription)
                putExtra("MenuItemImage", menuItem.bookImage)
            }
            requireContext.startActivity(intent)
        }

        fun bind(position: Int) {
            val menuItem = menuItems[position]
            binding.apply {
                menuBookTitle.text = menuItem.bookTitle
                menuPrice.text = menuItem.bookPrice
                val uri = Uri.parse(menuItem.bookImage)
                Glide.with(requireContext).load(uri).into(menuImage)
            }
        }
    }
}
*/
package com.example.turnthepage.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.turnthepage.DetailsActivity
import com.example.turnthepage.databinding.MenuItemBinding
import com.example.turnthepage.model.menuItem

class MenuAdapter(
    private val menuItems: MutableList<menuItem>,
    private val requireContext: Context,
    private val itemClickListener: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuItems.size

    inner class MenuViewHolder(private val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    openDetailsActivity(position)
                }
            }
        }

        private fun openDetailsActivity(position: Int) {
            val menuItem = menuItems[position]

            val intent = Intent(requireContext, DetailsActivity::class.java).apply {
                putExtra("MenuItemName", menuItem.bookTitle)
                putExtra("MenuItemPrice", menuItem.bookPrice)
                putExtra("MenuItemDescription", menuItem.bookDescription)
                putExtra("MenuItemImage", menuItem.bookImage)
            }
            requireContext.startActivity(intent)
        }

        fun bind(position: Int) {
            val menuItem = menuItems[position]
            binding.apply {
                menuBookTitle.text = menuItem.bookTitle
                menuPrice.text = menuItem.bookPrice.toString()
                val uri = Uri.parse(menuItem.bookImage)
                Glide.with(requireContext).load(uri).into(menuImage)
            }
        }
    }
}
