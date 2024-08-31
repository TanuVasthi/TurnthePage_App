package com.example.turnthepage.model

import android.os.Parcel
import android.os.Parcelable
import kotlin.collections.ArrayList

class OrderDetails():Parcelable {
    var userUid: String?=null
    var userName:String?=null
    var bookTitles: MutableList<String>?=null
    var bookImages: MutableList<String>?=null
    var bookPrice: MutableList<Int>?=null
    var bookQuantities: MutableList<Int>?=null
    var address: String?=null
    var totalPrice: String?=null
    var phoneNumber: String?=null
    var orderAccepted: Boolean=false
    var paymentReceived: Boolean=false
    var itemPushKey: String?=null
    var currentTime:Long=0

    constructor(parcel: Parcel) : this() {
        userUid = parcel.readString()
        userName = parcel.readString()
        address = parcel.readString()
        totalPrice = parcel.readString()
        phoneNumber = parcel.readString()
        orderAccepted = parcel.readByte() != 0.toByte()
        paymentReceived = parcel.readByte() != 0.toByte()
        itemPushKey = parcel.readString()
        currentTime = parcel.readLong()
    }

    constructor(
        userId: String,
        name: String,
        bookItemName: ArrayList<String>,
        bookItemPrice: ArrayList<Int>,
        bookItemImage: ArrayList<String>,
        bookItemQuantity: ArrayList<Int>,
        address: String,
        phone: String,
        time: Long,
        itemPushKey: String?,
        b: Boolean,
        b1: Boolean
    ) : this(){
        this.userUid=userId
        this.userName=name
        this.bookTitles=bookItemName
        this.bookPrice=bookItemPrice
        this.bookQuantities=bookItemQuantity
        this.address=address
        this.phoneNumber=phone
        this.currentTime=time
        this.itemPushKey=itemPushKey
        this.orderAccepted=orderAccepted
        this.paymentReceived=paymentReceived



    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userUid)
        parcel.writeString(userName)
        parcel.writeString(address)
        parcel.writeString(totalPrice)
        parcel.writeString(phoneNumber)
        parcel.writeByte(if (orderAccepted) 1 else 0)
        parcel.writeByte(if (paymentReceived) 1 else 0)
        parcel.writeString(itemPushKey)
        parcel.writeLong(currentTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderDetails> {
        override fun createFromParcel(parcel: Parcel): OrderDetails {
            return OrderDetails(parcel)
        }

        override fun newArray(size: Int): Array<OrderDetails?> {
            return arrayOfNulls(size)
        }
    }


}